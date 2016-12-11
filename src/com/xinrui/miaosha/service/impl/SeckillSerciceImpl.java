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
 * @Description:��ɱҵ��ʵ����(springֻ�����׳��������쳣RuntimeExceptionʱ�Ż�ع�,ʹ��ʱС��ʹ��try-catch)
 * @author ��־�� 
 * @date 2016��5��18�� ����5:47:15
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

	// ������ֵ
	private final String SALT = "AJSDLKASDKA%^%&^*&(SKJDF123DFKLSJDF*&)";

	/**
	 * ��ȡ��ɱ��Ʒ�б�
	 */
	public List<Seckill> getSeckillsList()
	{
		return this.seckillDao.queryAll( 0, 4 );
	}

	/**
	 * ����id��ѯ��Ʒ�б�
	 */
	public Seckill getById( long seckillId )
	{
		return this.seckillDao.queryById( seckillId );
	}

	/**
	 * ִ����ɱ����
	 */
	@Transactional
	public Exposer exportSeckillUrl( long seckillId )
	{
		Seckill seckill = this.seckillDao.queryById( seckillId );

		// ��Ʒ������,��ִ����ɱ����
		if ( seckill == null )
		{
			return new Exposer( false, seckillId );
		}

		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		// ��Ʒ��ɱ�ѹ���,��ִ����ɱ����
		if ( nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime() )
		{
			return new Exposer( false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime() );
		}

		// ִ����ɱ����
		String md5 = getMd5( seckillId );

		return new Exposer( true, md5, seckillId );
	}

	/**
	 * �������
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
	 * ִ����ɱ�����������쳣��Ϣ
	 * ʹ��ע���������ĺô���
	 * 1.�����ŶӴ��һ��Լ������ȷ��ע���񷽷��ı�̷��
	 * 2.��֤���񷽷�ִ��ʱ�価���̣ܶ���Ҫ�����������������RPC/HTTP������߰��뵽���񷽷�֮��
	 * 3.�������еķ�������Ҫ������ֻ��һ���޸Ĳ�����ֻ������������Ҫ�������
	 */
	@Transactional
	public SeckillExecution executeSeckill( long seckillId, long userPhone, String md5 ) throws RepeatkillException,
			SeckillCloseException, SeckillException
	{
		if ( md5 == null || !md5.equals( getMd5( seckillId ) ) )
		{
			throw new SeckillException( "seckill data rewrite" );
		}

		// ִ����ɱ�߼�:�����+��¼����
		Date nowTime = new Date();

		try
		{
			int updateCount = this.seckillDao.reduceNumber( seckillId, nowTime );

			if ( updateCount <= 0 )
			{
				// û�м�¼����,��ɱ����
				throw new SeckillCloseException( "seckill is closed" );
			}
			else
			{
				// �����ɹ�,��¼������Ϊ
				int insertCount = this.successkilledDao.insertSuccessKilled( seckillId, userPhone );

				// ΨһseckillId,userPhone
				if ( insertCount <= 0 )
				{
					throw new RepeatkillException( "seckill repeate" );
				}
				else
				{
					// ��ɱ�ɹ�
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
			// ���б������쳣 ת��Ϊ�������쳣
			throw new SeckillException( "seckill inner error:" + e.getMessage() );
		}
	}

}
