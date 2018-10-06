package com.soul.login.controller;

import java.sql.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.soul.login.service.MemberService;
import com.soul.login.vo.MemberVo;

@Controller
public class LoginController {
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/login2", method=RequestMethod.POST)
	public String login(String id, String pwd, Model model, HttpSession session,String auto, HttpServletResponse response) {
		if(session.getAttribute("login") != null) {
			session.removeAttribute("login");
		}
		MemberVo vo = service.loginCheck(id, pwd);
		if(vo != null) {
			session.setAttribute("login", vo);
			if(auto != null) {
				Cookie cookie = new Cookie("loginCookie", session.getId());
				cookie.setPath("/");
				int amount = 60*60*24;
				cookie.setMaxAge(amount); // 단위는 초 하룻동안 유효기간 설정
				response.addCookie(cookie); //쿠기 적용
				
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000*amount));
				service.keepLogin(id, session.getId(), sessionLimit);
			}
			model.addAttribute("loginMsg", "로그인 성공");
			return "index";
		}else {
			model.addAttribute("loginMsg", "로그인 실패");
			return "index";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		MemberVo vo = (MemberVo)session.getAttribute("login");
		session.invalidate();
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if ( loginCookie != null) {
			loginCookie.setPath("/");
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
			
			//사용자 테이블에서도 유효기간을 현재시간으로 다시 세팅해주어야한다.
			service.keepLogin(vo.getId(), session.getId(), new Date(System.currentTimeMillis()));
		}
		
		return "index";
	}
}
