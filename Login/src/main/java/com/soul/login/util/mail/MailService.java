package com.soul.login.util.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSender;

public class MailService {

	public void drawSendMail(JavaMailSender mailSender,String authKey, String email) throws MessagingException, UnsupportedEncodingException {
		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[이메일 인증]");
		sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
		.append("<a href='http://localhost:9090/login/emailConfirm?key=")
		.append(authKey)
		.append("&email=")
		.append(email)
		.append("' target='_blank'>이메일 인증 확인!</a>")
		.toString());
		sendMail.setFrom("soulstrk1234@gmail.com", "재용");
		sendMail.setTo(email);
		sendMail.send();
		
	}
}
