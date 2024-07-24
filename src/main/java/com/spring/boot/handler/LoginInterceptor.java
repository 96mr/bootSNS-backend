package com.spring.boot.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//String requestURI = request.getRequestURI();
		HttpSession session = request.getSession();
		if(session == null) {
			log.info("session null");
			response.sendRedirect(request.getContextPath() + "/api/login");
			return false;
		}
		return true;
	}

}
