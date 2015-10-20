package com.java1234.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


/**
 * 数据返回工具类
 * @author 
 *
 */
public class ResponseUtil {

	/**
	 * 向页面写数据
	 * @param response
	 * @param o
	 * @throws Exception
	 */
	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
