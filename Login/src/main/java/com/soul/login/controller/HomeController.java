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
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/Members/login";
	}
	
	@RequestMapping(value = "/memberPage/boardList", method = RequestMethod.GET)
	public String boardList() {
		return "/memberPage/boardList";
	}
	
	@RequestMapping(value = "/memberPage/game", method = RequestMethod.GET)
	public String game() {
		return "/memberPage/game";
	}
	
	@RequestMapping(value = "/memberPage/payment", method = RequestMethod.GET)
	public String payment() {
		return "/memberPage/payment";
	}
	
}
