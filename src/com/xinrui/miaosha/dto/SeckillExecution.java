package com.xinrui.miaosha.dto;

import com.xinrui.miaosha.bean.Successkilled;
import com.xinrui.miaosha.util.SeckillStatEnum;

/**
 * 
 * @ClassName: SeckillExecution
 * @Description: ��װ��ɱִ�к���
 * @author ��־��
 * @date 2016��5��18�� ����5:25:47
 *
 */
public class SeckillExecution
{
	private long seckillId;

	private int state; // ��ɱִ�н��״̬

	private String stateInfo; // ״̬��ʾ

	private Successkilled successkilled; // ��ɱ�ɹ�����

	public String toString()
	{
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", successkilled=" + successkilled + "]";
	}

	public SeckillExecution( long seckillId, SeckillStatEnum statEnum )
	{
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
	}

	public SeckillExecution( long seckillId, SeckillStatEnum statEnum, Successkilled successkilled )
	{
		this.seckillId = seckillId;
		this.state = statEnum.getState();
		this.stateInfo = statEnum.getStateInfo();
		this.successkilled = successkilled;
	}

	public long getSeckillId()
	{
		return seckillId;
	}

	public void setSeckillId( long seckillId )
	{
		this.seckillId = seckillId;
	}

	public int getState()
	{
		return state;
	}

	public void setState( int state )
	{
		this.state = state;
	}

	public String getStateInfo()
	{
		return stateInfo;
	}

	public void setStateInfo( String stateInfo )
	{
		this.stateInfo = stateInfo;
	}

	public Successkilled getSuccesskilled()
	{
		return successkilled;
	}

	public void setSuccesskilled( Successkilled successkilled )
	{
		this.successkilled = successkilled;
	}

}
