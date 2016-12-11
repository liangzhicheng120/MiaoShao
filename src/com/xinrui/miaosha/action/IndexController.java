package com.xinrui.miaosha.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mchange.v2.holders.VolatileIntHolder;
import com.xinrui.miaosha.bean.Seckill;
import com.xinrui.miaosha.service.SeckillService;

@Controller
public class IndexController
{
	@Resource
	private SeckillService seckillService;

	@RequestMapping( value = "/" )
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView( "index/index" );
		return mav;
	}

	@RequestMapping( value = "getById", method = RequestMethod.GET )
	@ResponseBody
	public Seckill getById( String seckillId )
	{
		if ( StringUtils.isNotEmpty( seckillId ) )
		{
			return seckillService.getById( Long.valueOf( seckillId ) );
		}
		return null;
	}

	@RequestMapping( value =  "getList", method = RequestMethod.GET )
	@ResponseBody
	public List<Seckill> getList()
	{
		List<Seckill> seckills = seckillService.getSeckillsList();
		return seckills; 
	}

	@RequestMapping( value = "getData", method = RequestMethod.GET )
	public ResponseEntity<List<Seckill>> getDataJson()
	{
		List<Seckill> seckills = seckillService.getSeckillsList();
		return new ResponseEntity<List<Seckill>>( seckills, HttpStatus.OK );
	}
}
