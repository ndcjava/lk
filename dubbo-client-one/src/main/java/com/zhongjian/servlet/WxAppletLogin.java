package com.zhongjian.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet(value = "/v1/notify/ali", asyncSupported = true)
public class WxAppletLogin extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(WxAppletLogin.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			Map<String, String[]> requestParams = request.getParameterMap();
			HashMap<String, String> paramsMap = new HashMap<>();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = iter.next();
				String[] values = requestParams.get(name);
				paramsMap.put(name, values[0]);
			}
			String code = paramsMap.get("code");
			String headPic = paramsMap.get("headpic");
			String nickName = paramsMap.get("nickname");
			String shareId = paramsMap.get("shareid");
			if(shareId != null) {
			}else {
				
			}
	}

}