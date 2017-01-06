package com.hy.cxp.supermarket.entity;
/**
 * 
 * 实体类，封装用户信息
 * 
 * @author cxp
 * @date 2016-11-15
 * 
 */
public class Users {
	
	/**
	 * userid number not null primary key, --用户编号 
	 * uname nvarchar2(20), --用户名
	 * upwd nvarchar2(20),--密码 
	 * usex nvarchar2(2),--性别
	 * ubirth date, --生日 
	 * utel nvarchar2(20),--电话 
	 * uadd nvarchar2(50),--地址 
	 * utype number(2) --1 管理员 2 经理 3普通用户
	 */
	private int userid;
	private String uname;
	private String upwd;
	private String usex;
	private String ubirth;
	private String utel;
	private String uadd;
	private int utype;
	
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Users(String uname, String upwd) {
		super();
		this.uname = uname;
		this.upwd = upwd;
	}


	public Users(int userid, String uname, String upwd, String usex,
			String ubirth, String utel, String uadd, int utype) {
		super();
		this.userid = userid;
		this.uname = uname;
		this.upwd = upwd;
		this.usex = usex;
		this.ubirth = ubirth;
		this.utel = utel;
		this.uadd = uadd;
		this.utype = utype;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUbirth() {
		return ubirth;
	}
	public void setUbirth(String ubirth) {
		this.ubirth = ubirth;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUadd() {
		return uadd;
	}
	public void setUadd(String uadd) {
		this.uadd = uadd;
	}
	public int getUtype() {
		return utype;
	}
	public void setUtype(int utype) {
		this.utype = utype;
	}

	
}
