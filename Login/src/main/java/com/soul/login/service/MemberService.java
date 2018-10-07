package com.soul.login.service;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.soul.login.dao.MemberDao;
import com.soul.login.util.mail.MailService;
import com.soul.login.util.mail.TempKey;
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
		MailService ms = new MailService();
		ms.drawSendMail(mailSender, authKey, vo.getEmail());
	}
	
	public int emailConfirm(String email) {
		int n = dao.memAuth(email);
		return n;
	}
	
	public MemberVo loginCheck(String id, String pwd) {
		MemberVo vo = dao.loginCheck(id, pwd);
		return vo;
	}

	public void keepLogin(String id, String sessionId, Date next) {
        dao.keepLogin(id, sessionId, next);
    }
 
    public MemberVo checkUserWithSessionKey(String sessionId) {
        return dao.checkUserWithSessionKey(sessionId);
    }
    
    public int checkCertification(String id) {
    	return dao.checkCertification(id);
    }

}
