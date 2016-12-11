package com.xinrui.miaosha.exception;

/**
 * 
 * @ClassName: SeckillException
 * @Description: 秒杀相关业务异常
 * @author 梁志成
 * @date 2016年5月18日 下午5:41:56
 *
 */
public class SeckillException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public SeckillException( String message, Throwable cause )
	{
		super( message, cause );
	}

	public SeckillException( String message )
	{
		super( message );
	}

}
