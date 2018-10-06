package com.soul.login.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.soul.login.service.MemberService;
import com.soul.login.vo.MemberVo;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	MemberService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object obj = request.getSession().getAttribute("login");
		if(obj == null) {
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				String sessionId = loginCookie.getValue();
				MemberVo vo = service.checkUserWithSessionKey(sessionId);
				
				if(vo != null) {
					request.getSession().setAttribute("login", vo);
					return true;
				}
			}
			response.sendRedirect("/login");
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
