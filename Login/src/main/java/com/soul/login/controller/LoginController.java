package com.soul.login.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
	
	@RequestMapping("/naverlogin1")
	public void naverlogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String clientId = "cXnG4ZMsAU0UT9_rvvIe";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "lwPvvFgpsq";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI = URLEncoder.encode("http://localhost:9090/login/", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    System.out.println("apiURL="+apiURL);
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	        /* out.println(res.toString()); */
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject)parser.parse(res.toString());
	        String test = (String)obj.get("access_token");
	        //personalInfo 가져옴
	        System.out.println("token"+test);
	        String header = "Bearer " + test; // Bearer 다음에 공백 추가
	        System.out.println("bbbbbbbbbbbbbbbbbb");
	        try {
	            apiURL = "https://openapi.naver.com/v1/nid/me";
	            URL url1 = new URL(apiURL);
	            HttpURLConnection conn = (HttpURLConnection)url1.openConnection();
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Authorization", header);
	            responseCode = conn.getResponseCode();
	            BufferedReader br1;
	            if(responseCode==200) { // 정상 호출
	                br1 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            } else {  // 에러 발생
	                br1 = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	            }
	            String inputLine1;
	            StringBuffer response1 = new StringBuffer();
	            while ((inputLine1 = br1.readLine()) != null) {
	                response1.append(inputLine1);
	            }
	            JSONParser parser1 = new JSONParser();
	            JSONObject obj1 = (JSONObject)parser1.parse(response1.toString());
	            br1.close();
	            PrintWriter pw = response.getWriter();
	            System.out.println(obj1.toString());
	            pw.println(obj1.toString());
	            pw.close();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	}
	
}
