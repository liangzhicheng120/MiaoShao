package com.xinrui.miaosha.exception;

/**
 * 
 * @ClassName: seckillCloseException
 * @Description: ��ɱ�ر��쳣
 * @author ��־��
 * @date 2016��5��18�� ����5:39:58
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
