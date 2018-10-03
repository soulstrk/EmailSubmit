package com.soul.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soul.login.service.MemberService;
import com.soul.login.vo.MemberVo;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/insertMember", method=RequestMethod.POST)
	public String insertMember(MemberVo vo, Model model) {
		try {
			service.create(vo);
			model.addAttribute("msg","success");
		}catch (Exception e) {
			model.addAttribute("msg","fail");
			System.out.println(e.getMessage());
		}
		return "/Members/result";
	}
	
	/*
	 * 클라이언트 본인에게 온 이메일을 인증시에 수행하는 매핑
	 */
	 @RequestMapping(value="/emailConfirm", method=RequestMethod.GET)
	    public String emailConfirm(String key,String email, Model model){
	        int n = service.emailConfirm(email);
	        if(n>0) {
	        	model.addAttribute("check",true);
	        }else {
	        	model.addAttribute("check",false);
	        }
	         
	        return "/Members/emailConfirm";
	    }

}
