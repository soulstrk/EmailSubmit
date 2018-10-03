package com.soul.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	/*
	 *   단순 페이지 이동
	 *   -> join.jsp (회원가입)
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "/Members/join";
	}
}
