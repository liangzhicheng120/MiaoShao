package com.xinrui.miaosha.test.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinrui.miaosha.bean.Seckill;
import com.xinrui.miaosha.dao.SeckillDao;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( "classpath:spring/spring-*.xml" )
public class SeckillDaoTest
{
	private static Logger logger = Logger.getLogger( SeckillDaoTest.class );

	@Resource
	private SeckillDao seckillDao;

	@Test
	public void testReduceNumber()
	{
		Date killTime = new Date();
		@SuppressWarnings( "unused" )
		int updateCount = seckillDao.reduceNumber( 1000L, killTime );
	}

	@Test
	public void testQueryById()
	{
		long id = 1000;
		Seckill seckill = seckillDao.queryById( id );
		logger.info( seckill.getName() );
		logger.info( seckill );
	}

	@Test
	public void testQueryAll()
	{
		List<Seckill> seckills = seckillDao.queryAll( 0, 100 );
		for ( Seckill seckill : seckills )
		{
			logger.info( seckill );
		}
	}

}
