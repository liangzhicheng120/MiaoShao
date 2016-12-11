package com.xinrui.miaosha.test.dao;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinrui.miaosha.bean.Successkilled;
import com.xinrui.miaosha.dao.SuccesskilledDao;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( "classpath:spring/spring-*.xml" )
public class SuccesskilledDaoTest
{
	private static Logger logger = Logger.getLogger( SeckillDaoTest.class );

	@Resource
	private SuccesskilledDao successkilledDao;

	@Test
	public void testInsertSuccessKilled()
	{
		long id = 1001L;
		long phone = 1560249234L;
		int insertCount = successkilledDao.insertSuccessKilled( id, phone );
		logger.info( "insertCount=" + insertCount );
	}

	@Test
	public void testQueryByIdWithSeckill()
	{
		long id = 1001L;
		long phone = 1560249234L;
		Successkilled successkilled = successkilledDao.queryByIdWithSeckill( id, phone );
		logger.info( successkilled );
		logger.info( successkilled.getSeckill() );
	}

}
