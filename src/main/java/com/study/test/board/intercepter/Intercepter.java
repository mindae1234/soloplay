package com.study.test.board.intercepter;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.study.test.board.customInfo.CustomInfo;

public class Intercepter extends HandlerInterceptorAdapter {

	protected Log log = LogFactory.getLog(Intercepter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession	session = request.getSession();
		CustomInfo info = (CustomInfo) session.getAttribute("CustomInfo");
		String cp = request.getContextPath();
		System.out.println("===================       START       ===================");		
		String requestUrl = request.getRequestURL().toString();	
		System.out.println(" Request URI \t:  " + requestUrl);
		//exclude-mapping 대신, 요청된 url
		
		if (info != null) {			
			System.out.println(info.toString());
		}
		
		

					

		//하단의 Url 체크를 통해, login 페이지는 예외처리를 해줘야 무한 리디렉션에서 벗어날 수 있다

		if(requestUrl.contains("/bbs/login") || requestUrl.contains("/bbs/join") || 
				requestUrl.contains("/bbs/eathAuthRegist") ||requestUrl.contains("/bbs/eathAuthRegistCheck")
				||requestUrl.contains("/bbs/eathAuthCheck")||requestUrl.contains("/resources/js/common.js")
				||requestUrl.contains("/resources/")||requestUrl.contains("/bbs/idChk")){
			
			
		return true;

		}
		
		if (info == null) {
			System.out.println("가능?");
				response.sendRedirect(cp+"/bbs/login");		
				System.out.println("DDDD");
				return false;
			}	
		
		return super.preHandle(request, response, handler);
	   }
	

	   @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	         ModelAndView modelAndView) throws Exception {

	      log.debug("===================        END        ===================\n");

	   }

	}

	

