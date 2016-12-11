package com.xinrui.miaosha.exception;

/**
 * 
 * @ClassName: seckillCloseException
 * @Description: 秒杀关闭异常
 * @author 梁志成
 * @date 2016年5月18日 下午5:39:58
 *
 */
public class SeckillCloseException extends SeckillException
{

	private static final long serialVersionUID = 1L;

	public SeckillCloseException( String message, Throwable cause )
	{
		super( message, cause );
	}

	public SeckillCloseException( String message )
	{
		super( message );
	}

}
