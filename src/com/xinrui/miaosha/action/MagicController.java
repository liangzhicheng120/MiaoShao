package com.xinrui.miaosha.action;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: MagicController
 * @Description: ���ɻ÷�������
 * @author ��־��
 * @date 2016��5��28�� ����1:10:47
 * 
 */
@Controller
@RequestMapping( value = "/magic" )
public class MagicController
{

	private final Logger logger = LoggerFactory.getLogger( this.getClass() );

	@RequestMapping( value = "" )
	public String magic( HttpServletRequest request )
	{
		writeLogger( request );
		return "magic/magic";
	}

	@RequestMapping( value = "/getMagic/{number}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" } )
	@ResponseBody
	public int[][] getMagic( @PathVariable( "number" ) String number, HttpServletRequest request,
			HttpServletResponse response )
	{
		writeLogger( request );
		logger.info( "number={}", number );

		response.addHeader( "Access-Control-Allow-Origin", "*" );

		if ( StringUtils.isNotEmpty( number ) )
		{
			Pattern pattern = Pattern.compile( "[0-9]{1,9}" );
			// �ж�Ϊ����
			if ( pattern.matcher( number ).matches() )
			{
				int n = Integer.valueOf( number );
				// �ж�Ϊ����
				if ( n % 2 != 0 )
				{
					int[][] magic = magicOdd( n );
					return magic;
				}
			}

		}
		return null;
	}

	/**
	 * ���ɻ÷�
	 * 
	 * @param n����
	 * @return �����׻÷�
	 */
	private int[][] magicOdd( int n )
	{
		int[][] square = new int[n + 1][n + 1];
		int i = 0;
		int j = ( n + 1 ) / 2;
		for ( int key = 1; key <= n * n; key++ )
		{
			if ( ( key % n ) == 1 )
				i++;
			else
			{
				i--;
				j++;
			}
			if ( i == 0 )
				i = n;
			if ( j > n )
				j = 1;
			square[i][j] = key;
		}
		int[][] matrix = new int[n][n];
		for ( int k = 0; k < matrix.length; k++ )
		{
			for ( int l = 0; l < matrix[0].length; l++ )
			{
				matrix[k][l] = square[k + 1][l + 1];
			}
		}
		return matrix;
	}

	/**
	 * ������־��Ϣ
	 * 
	 * @param request
	 */
	private void writeLogger( HttpServletRequest request )
	{
		String url = request.getRequestURI();
		String addr = request.getLocalAddr();
		String host = request.getRemoteHost();
		int remoteport = request.getRemotePort();
		String param = request.getQueryString();
		logger.info( "�ͻ��˷�������ʱ������URL={}", url );
		logger.info( "WEB��������IP��ַ={}", addr );
		logger.info( "��������Ŀͻ���������������={}", host );
		logger.info( "�ͻ�����ʹ�õ�����˿ں�={}", remoteport );
		logger.info( "�ύ�Ĳ���={}", param );
		logger.info( "=============================", "" );
	}

}
