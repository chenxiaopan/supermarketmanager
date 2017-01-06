package com.hy.cxp.supermarket.entity;
/**
 * 
 * 显示用户信息
 * 
 * @author cxp
 * @date 2016-11-15
 * 
 */
public class UsersView {
	
	/**
	 * userid number not null primary key, --用户编号 
	 * uname nvarchar2(20), --用户名
	 * usex nvarchar2(2),--性别
	 * ubirth date, --生日 
	 * utel nvarchar2(20),--电话 
	 * utype number(2) --1 管理员 2 经理 3普通用户
	 */
	
	private int userid;
	private String uname;
	private String usex;
	private String ubirth;
	private String utel;
	private String utype;
	
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
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	
	
	
}
