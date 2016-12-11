package com.xinrui.miaosha.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xinrui.miaosha.bean.Seckill;

/**
 * 
 * @ClassName: SeckillDao
 * @Description: ��ɱ��Ʒ��
 * @author ��־��
 * @date 2016��5��17�� ����11:27:26
 *
 */
@Repository
public interface SeckillDao
{
	/**
	 * �����
	 * 
	 * @param seckillId
	 * @param killTime
	 * @return
	 */
	int reduceNumber( @Param( "seckillId" ) long seckillId, @Param( "killTime" ) Date killTime );

	/**
	 * ����id��ѯ��ɱ����
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill queryById( long seckillId );

	/**
	 * ����ƫ������ѯ��ɱ����
	 * 
	 * @param offet
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll( @Param( "offset" ) int offet, @Param( "limit" ) int limit );

}
