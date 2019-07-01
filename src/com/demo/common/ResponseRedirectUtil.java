package com.demo.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseRedirectUtil {

	public static int AJAX = 1;
	public static int addressBar = 0;

	public static int requestType(HttpServletRequest req) {
		if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) {
			// AJAX请求
			return AJAX;
		}
		return addressBar;
	}

	public static void ajaxRedirectJson(HttpServletResponse resp, String url) throws IOException {
		String jsonObject = "{\"code\":\"E-StatusCode-301\",\"errorText\":\"" + url + "\"}";
		String contentType = "application/json; charset=UTF-8";
		resp.setContentType(contentType);
		PrintWriter out = resp.getWriter();
		out.print(jsonObject);
		out.flush();
		out.close();
	}

	public static void ifAjaxRedirect(HttpServletRequest req, HttpServletResponse resp, String url) throws IOException {
		if (requestType(req) == AJAX) {
			ajaxRedirectJson(resp, url);
			return;
		}
		resp.sendRedirect(url);
	}

}
