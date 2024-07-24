package com.spring.boot.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException { 
		log.info("로그인 성공");
		
		String defaultRedirectUrl = "/home";

        String redirectUrl = request.getHeader("Referer");
        if (redirectUrl == null || redirectUrl.contains("/login") || redirectUrl.isEmpty()) {
            redirectUrl = defaultRedirectUrl;
        }

        // 리디렉션
        response.sendRedirect(redirectUrl);
	}

}
