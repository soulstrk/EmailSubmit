package com.soul.login.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.soul.login.dao.MemberDao;
import com.soul.login.util.MailHandler;
import com.soul.login.util.TempKey;
import com.soul.login.vo.MemberVo;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	@Autowired
	private JavaMailSender mailSender;
	
	public void create(MemberVo vo) throws MessagingException, UnsupportedEncodingException {
		dao.memberInsert(vo);
		String authKey = new TempKey().getKey(50, false);
		dao.authKeyInsert(vo.getEmail(), authKey);
		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[이메일 인증]");
		sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
		.append("<a href='http://localhost:9090/login/emailConfirm?key=")
		.append(authKey)
		.append("&email=")
		.append(vo.getEmail())
		.append("' target='_blank'>이메일 인증 확인!</a>")
		.toString());
		sendMail.setFrom("soulstrk1234@gmail.com", "재용");
		sendMail.setTo(vo.getEmail());
		sendMail.send();
	}
	
	public int emailConfirm(String email) {
		int n = dao.memAuth(email);
		return n;
	}
	
	
}
