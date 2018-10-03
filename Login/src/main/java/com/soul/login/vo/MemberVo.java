package com.soul.login.vo;

public class MemberVo {
	private String email;
	private String id;
	private String pwd;
	private int authKey;
	
	public MemberVo() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getAuthKey() {
		return authKey;
	}

	public void setAuthKey(int authKey) {
		this.authKey = authKey;
	}

	public MemberVo(String email, String id, String pwd, int authKey) {
		super();
		this.email = email;
		this.id = id;
		this.pwd = pwd;
		this.authKey = authKey;
	}

	@Override
	public String toString() {
		return "MemberVo [email=" + email + ", id=" + id + ", pwd=" + pwd + ", authKey=" + authKey + "]";
	}
	
	
}
