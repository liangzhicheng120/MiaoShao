package com.xinrui.miaosha.exception;

/**
 * 
 * @ClassName: RepeatkillException
 * @Description: �ظ���ɱ�쳣,�������쳣
 * @author ��־��
 * @date 2016��5��18�� ����5:38:52
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
