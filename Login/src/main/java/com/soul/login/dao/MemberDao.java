package com.soul.login.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soul.login.vo.MemberVo;

@Repository
public class MemberDao {
	@Autowired
	private SqlSession session;
	private static String NAMESPACE = "login.Mappers.LoginMapper.";
	
	public int memberInsert(MemberVo vo) {
		return session.insert(NAMESPACE+"memberInsert", vo);
	}
	
	public int authKeyInsert(String email, String authKey) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("email", email);
		map.put("authKey", authKey);
				
		return session.insert(NAMESPACE+"authKeyInsert", map);
	}
	
	public int memAuth(String email) {
		return session.update(NAMESPACE+"memAuth", email);
	}
	
	public MemberVo loginCheck(String id, String pwd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		return session.selectOne(NAMESPACE+"loginCheck", map);
	}
	
	public void keepLogin(String id, String sessionId, Date next) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("sessionId", sessionId);
		map.put("next", next);
		session.update(NAMESPACE+"keepLogin", map);
	}
	
	public MemberVo checkUserWithSessionKey(String sessionId) {
		return session.selectOne(NAMESPACE+"checkUserWithSessionKey", sessionId);
	}
}
