package com.xinrui.miaosha.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.xinrui.miaosha.bean.Seckill;
import com.xinrui.miaosha.bean.Successkilled;
import com.xinrui.miaosha.dao.SeckillDao;
import com.xinrui.miaosha.dao.SuccesskilledDao;
import com.xinrui.miaosha.dto.Exposer;
import com.xinrui.miaosha.dto.SeckillExecution;
import com.xinrui.miaosha.exception.RepeatkillException;
import com.xinrui.miaosha.exception.SeckillCloseException;
import com.xinrui.miaosha.exception.SeckillException;
import com.xinrui.miaosha.service.SeckillService;
import com.xinrui.miaosha.util.SeckillStatEnum;

/**
 * 
 * @ClassName: SeckillSerciceImpl
 * @Description:秒杀业务实现类(spring只有在抛出运行期异常RuntimeException时才会回滚,使用时小心使用try-catch)
 * @author 梁志成 
 * @date 2016年5月18日 下午5:47:15
 *
 */
@Service
public class SeckillSerciceImpl implements SeckillService
{
	private Logger logger = LoggerFactory.getLogger( this.getClass() );

	@Resource
	private SeckillDao seckillDao;

	@Resource
	private SuccesskilledDao successkilledDao;

	// 加密盐值
	private final String SALT = "AJSDLKASDKA%^%&^*&(SKJDF123DFKLSJDF*&)";

	/**
	 * 获取秒杀商品列表
	 */
	public List<Seckill> getSeckillsList()
	{
		return this.seckillDao.queryAll( 0, 4 );
	}

	/**
	 * 根据id查询商品列表
	 */
	public Seckill getById( long seckillId )
	{
		return this.seckillDao.queryById( seckillId );
	}

	/**
	 * 执行秒杀操作
	 */
	@Transactional
	public Exposer exportSeckillUrl( long seckillId )
	{
		Seckill seckill = this.seckillDao.queryById( seckillId );

		// 商品不存在,不执行秒杀操作
		if ( seckill == null )
		{
			return new Exposer( false, seckillId );
		}

		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		// 商品秒杀已过期,不执行秒杀操作
		if ( nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime() )
		{
			return new Exposer( false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime() );
		}

		// 执行秒杀操作
		String md5 = getMd5( seckillId );

		return new Exposer( true, md5, seckillId );
	}

	/**
	 * 加密组件
	 * 
	 * @param seckillId
	 * @return
	 */
	private String getMd5( long seckillId )
	{
		String base = seckillId + "/" + SALT;
		String md5 = DigestUtils.md5DigestAsHex( base.getBytes() );
		return md5;
	}

	/**
	 * 执行秒杀操作并捕获异常信息
	 * 使用注解控制事务的好处：
	 * 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法执行时间尽可能短，不要穿插其他网络操作：RPC/HTTP请求或者剥离到事务方法之外
	 * 3.不是所有的方法都需要事务如只有一条修改操作或只读操作都不需要事务控制
	 */
	@Transactional
	public SeckillExecution executeSeckill( long seckillId, long userPhone, String md5 ) throws RepeatkillException,
			SeckillCloseException, SeckillException
	{
		if ( md5 == null || !md5.equals( getMd5( seckillId ) ) )
		{
			throw new SeckillException( "seckill data rewrite" );
		}

		// 执行秒杀逻辑:减库存+记录购买
		Date nowTime = new Date();

		try
		{
			int updateCount = this.seckillDao.reduceNumber( seckillId, nowTime );

			if ( updateCount <= 0 )
			{
				// 没有记录更新,秒杀结束
				throw new SeckillCloseException( "seckill is closed" );
			}
			else
			{
				// 减库存成功,记录购买行为
				int insertCount = this.successkilledDao.insertSuccessKilled( seckillId, userPhone );

				// 唯一seckillId,userPhone
				if ( insertCount <= 0 )
				{
					throw new RepeatkillException( "seckill repeate" );
				}
				else
				{
					// 秒杀成功
					Successkilled sesuccesskilled = this.successkilledDao.queryByIdWithSeckill( seckillId, userPhone );
					return new SeckillExecution( seckillId, SeckillStatEnum.SUCCESS, sesuccesskilled );
				}
			}
		}
		catch ( SeckillCloseException e1 )
		{
			throw e1;
		}
		catch ( RepeatkillException e2 )
		{
			throw e2;
		}
		catch ( Exception e )
		{
			logger.error( e.getMessage(), e );
			// 所有编译期异常 转化为运行期异常
			throw new SeckillException( "seckill inner error:" + e.getMessage() );
		}
	}

}
