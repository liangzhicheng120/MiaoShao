package com.xinrui.miaosha.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xinrui.miaosha.bean.Seckill;

/**
 * 
 * @ClassName: SeckillDao
 * @Description: 秒杀商品表
 * @author 梁志成
 * @date 2016年5月17日 下午11:27:26
 *
 */
@Repository
public interface SeckillDao
{
	/**
	 * 减库存
	 * 
	 * @param seckillId
	 * @param killTime
	 * @return
	 */
	int reduceNumber( @Param( "seckillId" ) long seckillId, @Param( "killTime" ) Date killTime );

	/**
	 * 根据id查询秒杀对象
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill queryById( long seckillId );

	/**
	 * 根据偏移量查询秒杀对象
	 * 
	 * @param offet
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll( @Param( "offset" ) int offet, @Param( "limit" ) int limit );

}
