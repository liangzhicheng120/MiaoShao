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
 * @Description: 秒杀业务接口
 * @author 梁志成
 * @date 2016年5月18日 下午5:09:31
 *
 */
public interface SeckillService
{
	/**
	 * 查询所有秒杀记录
	 * 
	 * @return
	 */
	List<Seckill> getSeckillsList();

	/**
	 * 查询单个秒杀记录
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill getById( long seckillId );

	/**
	 * 秒杀开启时输出秒杀接口地址,否则输出系统时间和秒杀时间
	 * 
	 * @param seckillId
	 */
	Exposer exportSeckillUrl( long seckillId );

	/**
	 * 执行秒杀操作
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * (判断用户的url是否被串改)
	 */
	SeckillExecution executeSeckill( long seckillId, long userPhone, String md5 ) throws RepeatkillException,
			SeckillCloseException, SeckillException;
}
