package com.xinrui.miaosha.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xinrui.miaosha.bean.Seckill;
import com.xinrui.miaosha.dto.Exposer;
import com.xinrui.miaosha.dto.SeckillExecution;
import com.xinrui.miaosha.dto.SeckillResult;
import com.xinrui.miaosha.exception.RepeatkillException;
import com.xinrui.miaosha.exception.SeckillCloseException;
import com.xinrui.miaosha.service.SeckillService;
import com.xinrui.miaosha.util.SeckillStatEnum;

@Controller
@RequestMapping( value = "seckill" )
public class SeckillController
{
	private final Logger logger = LoggerFactory.getLogger( this.getClass() );

	@Resource
	private SeckillService seckillService;

	/**
	 * �����б�ҳ
	 * 
	 * @return
	 */
	@RequestMapping( value = "/list", method = RequestMethod.GET )
	public ModelAndView list()
	{
		ModelAndView mav = new ModelAndView( "seckill/list" );
		List<Seckill> list = seckillService.getSeckillsList();
		mav.addObject( "list", list );
		return mav;
	}

	/**
	 * �����б�����ҳ
	 * 
	 * @param seckillId
	 * @return
	 */
	@RequestMapping( value = "/{seckillId}/detail", method = RequestMethod.GET )
	public ModelAndView detail( @PathVariable( "seckillId" ) Long seckillId )
	{
		ModelAndView mav = new ModelAndView( "seckill/detail" );
		// �û�δע��
		if ( seckillId == null )
		{
			return new ModelAndView( "redirect:seckill/list" );
		}
		Seckill seckill = seckillService.getById( seckillId );
		// ����������
		if ( seckill == null )
		{
			return new ModelAndView( "forward:seckill/list" );
		}
		mav.addObject( "seckill", seckill );
		return mav;
	}

	/**
	 * ��¶�û���ַ,��֤�û��Ϸ���Ϣ,����json��ʽ����
	 * 
	 * @param seckillId
	 * @return
	 */
	@RequestMapping( value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" } )
	@ResponseBody
	public SeckillResult<Exposer> exposer( @PathVariable( "seckillId" ) Long seckillId )
	{

		try
		{
			Exposer exposer = seckillService.exportSeckillUrl( seckillId );
			return new SeckillResult<Exposer>( true, exposer );
		}
		catch ( Exception e )
		{
			logger.error( e.getMessage(), e );
			return new SeckillResult<Exposer>( false, e.getMessage() );
		}

	}

	/**
	 * ִ����ɱ
	 * 
	 * @param seckillId
	 * @param md5
	 * @param phone
	 * @return
	 */
	@RequestMapping( value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" } )
	@ResponseBody
	public SeckillResult<SeckillExecution> execute( @PathVariable( "seckillId" ) Long seckillId,
			@PathVariable( "md5" ) String md5,
			@CookieValue( value = "killPhone", required = false ) Long phone )
	{
		// �û�δע��
		if ( phone == null )
		{
			return new SeckillResult<SeckillExecution>( false, "δע��" );
		}

		try
		{
			SeckillExecution execution = seckillService.executeSeckill( seckillId, phone, md5 );
			return new SeckillResult<SeckillExecution>( true, execution );
		}
		catch ( RepeatkillException e )
		{ // �ظ���ɱ����
			SeckillExecution execution = new SeckillExecution( seckillId,
					SeckillStatEnum.REPEAT_KILL );
			return new SeckillResult<SeckillExecution>( true, execution );

		}
		catch ( SeckillCloseException e )
		{ // ��ɱ��������
			SeckillExecution execution = new SeckillExecution( seckillId, SeckillStatEnum.END );
			return new SeckillResult<SeckillExecution>( true, execution );
		}
		catch ( Exception e )
		{ // �����쳣����
			logger.error( e.getMessage(), e );
			SeckillExecution execution = new SeckillExecution( seckillId,
					SeckillStatEnum.INNER_ERROR );
			return new SeckillResult<SeckillExecution>( true, execution );

		}

	}

	/**
	 * ��ȡϵͳ��ǰʱ��
	 * 
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" } )
	@RequestMapping( value = "/time/now", method = RequestMethod.GET )
	@ResponseBody
	public SeckillResult<Long> time()
	{
		Date now = new Date();
		return new SeckillResult( true, now.getTime() );
	}
}
