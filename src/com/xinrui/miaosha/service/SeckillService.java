package com.xinrui.miaosha.service;

import java.util.List;

import com.xinrui.miaosha.bean.Seckill;
import com.xinrui.miaosha.dto.Exposer;
import com.xinrui.miaosha.dto.SeckillExecution;
import com.xinrui.miaosha.exception.RepeatkillException;
import com.xinrui.miaosha.exception.SeckillCloseException;
import com.xinrui.miaosha.exception.SeckillException;

/**
 * 
 * @ClassName: SeckillService
 * @Description: ��ɱҵ��ӿ�
 * @author ��־��
 * @date 2016��5��18�� ����5:09:31
 *
 */
public interface SeckillService
{
	/**
	 * ��ѯ������ɱ��¼
	 * 
	 * @return
	 */
	List<Seckill> getSeckillsList();

	/**
	 * ��ѯ������ɱ��¼
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill getById( long seckillId );

	/**
	 * ��ɱ����ʱ�����ɱ�ӿڵ�ַ,�������ϵͳʱ�����ɱʱ��
	 * 
	 * @param seckillId
	 */
	Exposer exportSeckillUrl( long seckillId );

	/**
	 * ִ����ɱ����
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * (�ж��û���url�Ƿ񱻴���)
	 */
	SeckillExecution executeSeckill( long seckillId, long userPhone, String md5 ) throws RepeatkillException,
			SeckillCloseException, SeckillException;
}
