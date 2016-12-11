package com.xinrui.miaosha.exception;

/**
 * 
 * @ClassName: RepeatkillException
 * @Description: 重复秒杀异常,运行期异常
 * @author 梁志成
 * @date 2016年5月18日 下午5:38:52
 *
 */
public class RepeatkillException extends SeckillException
{

	private static final long serialVersionUID = 1L;

	public RepeatkillException( String message, Throwable cause )
	{
		super( message, cause );
	}

	public RepeatkillException( String message )
	{
		super( message );
	}

}
