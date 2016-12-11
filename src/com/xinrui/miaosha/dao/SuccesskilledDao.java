package com.xinrui.miaosha.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xinrui.miaosha.bean.Successkilled;

/**
 * 
 * @ClassName: SuccesskilledDao
 * @Description: ��ɱ�ɹ���
 * @author ��־��
 * @date 2016��5��17�� ����11:36:14
 *
 */
@Repository
public interface SuccesskilledDao
{
	/**
	 * ���빺����ϸ,�ɹ����ظ�
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @return Ӱ������>0,��ʾ�и���
	 */
	int insertSuccessKilled( @Param( "seckillId" ) long seckillId, @Param( "userPhone" ) long userPhone );

	/**
	 * ����id��ѯSuccesskilled����Я����ɱ��Ʒ����ʵ��
	 * 
	 * @param seckillId
	 * @return
	 */
	Successkilled queryByIdWithSeckill( @Param( "seckillId" ) long seckillId, @Param( "userPhone" ) long userPhone );
}
