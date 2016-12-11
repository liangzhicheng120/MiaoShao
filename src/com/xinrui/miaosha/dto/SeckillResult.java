package com.xinrui.miaosha.dto;

/**
 * 
 * @ClassName: SeckillResult
 * @Description: 封住json结果,success true:输出data;success false 输error
 * @author 梁志成
 * @date 2016年5月21日 下午6:16:55
 * 
 * @param <T>
 */
public class SeckillResult<T>
{
	private boolean success;

	private T data;

	private String error;

	public SeckillResult( boolean success, String error )
	{
		this.success = success;
		this.error = error;
	}

	public SeckillResult( boolean success, T data )
	{
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess( boolean success )
	{
		this.success = success;
	}

	public T getData()
	{
		return data;
	}

	public void setData( T data )
	{
		this.data = data;
	}

	public String getError()
	{
		return error;
	}

	public void setError( String error )
	{
		this.error = error;
	}

}
