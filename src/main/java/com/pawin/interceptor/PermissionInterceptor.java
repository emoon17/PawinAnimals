package com.pawin.interceptor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//권한 검사 시 사용 하기 위해 interceptor 만들었다.
@Component // 스프링 빈
public class PermissionInterceptor implements HandlerInterceptor{
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws IOException {
		// 요청 url을 가져온다.
		String uri = request.getRequestURI(); // /post/post_list_view
		logger.info("[#####preHandle: uri:{}", uri); 
		
		// 로직 
		// 세션이 있는지 확인 => 있으면 로그인이 되어있는 것.
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// 비 로그인 && /post로 온 경우 => 로그인 페이지로 redirect => return false
		if (userId == null && uri.startsWith("/post")) {
			response.sendRedirect("/user/signIn_view");
			return false; // 컨트롤러 수행 안함.
		}
		
		// 로그인 && /user로 온 경우 => 글 목록 페이지로 redirect => return false
		if (userId != null && uri.startsWith("/user")) {
			response.sendRedirect("/main/main_view");
			return false; // 컨트롤러 수행 안함.
		}
		
		return true; // 컨트롤러 수행 함.
	}
	
	// 응답 내릴 때 아직 view( jsp)로 해석이 되지 않은 상태
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView mav) { 
		
		logger.info("[$$$$ posthandle]"); 
	}
	
	// view가 해석까지 다 되서 html이 된 상태 ?
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) {
		
		logger.info("[@@@@@ afterCompletion]");
	}
}
