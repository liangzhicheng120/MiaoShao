package com.xinrui.miaosha.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xinrui.miaosha.bean.Successkilled;

/**
 * 
 * @ClassName: SuccesskilledDao
 * @Description: 秒杀成功表
 * @author 梁志成
 * @date 2016年5月17日 下午11:36:14
 *
 */
@Repository
public interface SuccesskilledDao
{
	/**
	 * 插入购买明细,可过滤重复
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @return 影响行数>0,表示有更新
	 */
	int insertSuccessKilled( @Param( "seckillId" ) long seckillId, @Param( "userPhone" ) long userPhone );

	/**
	 * 根据id查询Successkilled对象并携带秒杀产品对象实体
	 * 
	 * @param seckillId
	 * @return
	 */
	Successkilled queryByIdWithSeckill( @Param( "seckillId" ) long seckillId, @Param( "userPhone" ) long userPhone );
}
