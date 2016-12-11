package com.xinrui.miaosha.util;

/**
 * 
 * @ClassName: SeckillStatEnum
 * @Description: 异常数据字典
 * @author 梁志成
 * @date 2016年5月18日 下午11:56:30
 *
 */
public enum SeckillStatEnum
{
	SUCCESS( 1, "秒杀成功" ), END( 0, "秒杀失败" ), REPEAT_KILL( -1, "重复秒杀" ), INNER_ERROR( -2, "系统异常" ), DATA_REWRITE( -3,
			"数据串改" );

	private int state;

	private String stateInfo;

	SeckillStatEnum( int state, String stateInfo )
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState()
	{
		return state;
	}

	public String getStateInfo()
	{
		return stateInfo;
	}

	public static SeckillStatEnum stateOf( int index )
	{
		for ( SeckillStatEnum state : values() )
		{
			if ( state.getState() == index )
			{
				return state;
			}
		}
		return null;
	}
}
