package com.xinrui.miaosha.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinrui.miaosha.bean.Seckill;
import com.xinrui.miaosha.dto.Exposer;
import com.xinrui.miaosha.dto.SeckillExecution;
import com.xinrui.miaosha.exception.RepeatkillException;
import com.xinrui.miaosha.exception.SeckillCloseException;
import com.xinrui.miaosha.service.SeckillService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( "classpath:spring/spring-*.xml" )
public class SeckillServiceTest
{
	private final Logger logger = LoggerFactory.getLogger( this.getClass() );

	@Resource
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() throws Exception
	{
		List<Seckill> list = seckillService.getSeckillsList();
		logger.info( "list={}", list );
	}

	@Test
	public void testGetById()
	{
		long id = 1000;
		Seckill seckill = seckillService.getById( id );
		logger.info( "seckill={}", seckill );

	}

	@Test
	public void testSeclillLogic()
	{
		long id = 1002L;
		Exposer exposer = seckillService.exportSeckillUrl( id );
		if ( exposer.isExposed() )
		{
			logger.info( "exposer={}", exposer );
			long phone = 13532423423L;
			String md5 = exposer.getMd5();
			try
			{
				SeckillExecution execution = seckillService.executeSeckill( id, phone, md5 );
				logger.info( "result={}", execution );
			}
			catch ( RepeatkillException e )
			{
				logger.error( e.getMessage() );
			}
			catch ( SeckillCloseException e )
			{
				logger.info( e.getMessage() );
			}
		}
		else
		{
			logger.warn( "exposer={}", exposer );
		}

	}

}
