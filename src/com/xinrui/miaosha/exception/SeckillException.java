package com.xinrui.miaosha.exception;

/**
 * 
 * @ClassName: SeckillException
 * @Description: ��ɱ���ҵ���쳣
 * @author ��־��
 * @date 2016��5��18�� ����5:41:56
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
