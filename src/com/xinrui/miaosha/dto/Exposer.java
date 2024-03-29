package com.xinrui.miaosha.dto;

/**
 * 
 * @ClassName: Exposer
 * @Description: 暴露系统秒杀时间DTO
 * @author 梁志成
 * @date 2016年5月18日 下午5:15:56
 *
 */
public class Exposer
{
	private boolean exposed; // 是否开启秒杀

	private String md5; // 加密

	private long seckillId; // id

	private long now; // 系统当前时间

	private long start;// 秒杀开启时间

	private long end; // 秒杀结束时间

	public String toString()
	{
		return "Exposer [exposed=" + exposed + ", md5=" + md5 + ", seckillId=" + seckillId + ", now=" + now
				+ ", start=" + start + ", end=" + end + "]";
	}

	public Exposer( boolean exposed, String md5, long seckillId )
	{
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	public Exposer( boolean exposed, long seckillId, long now, long start, long end )
	{
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	public Exposer( boolean exposed, long seckillId )
	{
		this.exposed = exposed;
		this.seckillId = seckillId;
	}

	public boolean isExposed()
	{
		return exposed;
	}

	public void setExposed( boolean exposed )
	{
		this.exposed = exposed;
	}

	public String getMd5()
	{
		return md5;
	}

	public void setMd5( String md5 )
	{
		this.md5 = md5;
	}

	public long getSeckillId()
	{
		return seckillId;
	}

	public void setSeckillId( long seckillId )
	{
		this.seckillId = seckillId;
	}

	public long getNow()
	{
		return now;
	}

	public void setNow( long now )
	{
		this.now = now;
	}

	public long getStart()
	{
		return start;
	}

	public void setStart( long start )
	{
		this.start = start;
	}

	public long getEnd()
	{
		return end;
	}

	public void setEnd( long end )
	{
		this.end = end;
	}

}
