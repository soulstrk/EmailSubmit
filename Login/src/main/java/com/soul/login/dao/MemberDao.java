package com.soul.login.dao;

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
}
