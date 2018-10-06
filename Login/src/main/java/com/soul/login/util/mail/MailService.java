package com.soul.login.util.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSender;

public class MailService {

	public void drawSendMail(JavaMailSender mailSender,String authKey, String email) throws MessagingException, UnsupportedEncodingException {
		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[�̸��� ����]");
		sendMail.setText(new StringBuffer().append("<h1>��������</h1>")
		.append("<a href='http://localhost:9090/login/emailConfirm?key=")
		.append(authKey)
		.append("&email=")
		.append(email)
		.append("' target='_blank'>�̸��� ���� Ȯ��!</a>")
		.toString());
		sendMail.setFrom("soulstrk1234@gmail.com", "���");
		sendMail.setTo(email);
		sendMail.send();
		
	}
}
