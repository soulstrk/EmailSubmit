package com.soul.login.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value = "/personalInfo")
	public void personalInfo(HttpServletRequest request) throws Exception {
	        String token = "AAAAOHDkIAgT8GFWMDLs6fEXGtTYZdCkihgmwqi/wXumGoyWBSXrhm07rUkaRgLu7/LrL1fHJbVgH6lQqrEyB9j94sE=";// 네이버 로그인 접근 토큰; 여기에 복사한 토큰값을 넣어줍니다.
	        String header = "Bearer " + token; // Bearer 다음에 공백 추가
	        try {
	            String apiURL = "https://openapi.naver.com/v1/nid/me";
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("GET");
	            con.setRequestProperty("Authorization", header);
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            System.out.println(response.toString());
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	}

}
