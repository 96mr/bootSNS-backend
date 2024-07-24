package com.spring.boot.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.spring.boot.handler.LoginAuthenticationEntryPoint;
import com.spring.boot.handler.LoginFailureHandler;
import com.spring.boot.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	//service, passwordEncoder 자동 생성
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	//spring security를 적용하지 않는 리소스
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/resources/**");
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.cors()  // CORS 설정 활성화
            .and()
		//	.addFilter(new CorsConfig().corsFilter())
			.authorizeHttpRequests()
			//	.antMatchers("/login", "/api/login", "/api/checkSession"
			//				,"/**/profile","/**/board/**").permitAll() // 로그인 페이지 및 API 접근 허용
			//	.anyRequest().authenticated()
				.antMatchers("/api/setting/**","/api/board"
							,"/api/home","/api/**/like"
							,"/api/**/follow").authenticated()
				.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginProcessingUrl("/api/login")
				.usernameParameter("id")
				.passwordParameter("password")
				.successHandler(new LoginSuccessHandler())
				.failureHandler(new LoginFailureHandler())
			.and()
				.logout()
				.logoutUrl("/api/logout")
				.logoutSuccessUrl("/login")
			.and()
			.exceptionHandling()
                .authenticationEntryPoint(new LoginAuthenticationEntryPoint()) // 커스텀 인증 엔트리 포인트
			.and()
				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
				.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/login?expired=true");
		return http.build();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    configuration.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}
}