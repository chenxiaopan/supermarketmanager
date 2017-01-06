package com.hy.cxp.supermarket.entity;
/**
 * 实体类，封装商品提供商信息的JavaBean 
 * 实现序列化接口以便其实例可以安全的保存在HttpSession中
 * 
 * @author cxp
 * @date 2016-11-15
 * 
 */
public class Suppliers {
/*
	sid number primary key,--供应商编号
	sname nvarchar2(30),--供应商名称
	scontacts nvarchar2(20),--联系人
	stel varchar2(20),--联系电话
	sadd nvarchar2(50),--联系地址
	sfax varchar2(20),--传真
	sdesc nvarchar2(50)--描述
	sdate date --创建提供商的日期
	*/
	
	private int sid;
	private String sname;
	private String scontacts;
	private String stel;
	private String sadd;
	private String sfax;
	private String sdesc;
	private String date;

	public Suppliers() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Suppliers(int sid, String sname, String scontacts, String stel,
			String sadd, String sfax, String sdesc) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.scontacts = scontacts;
		this.stel = stel;
		this.sadd = sadd;
		this.sfax = sfax;
		this.sdesc = sdesc;
	}
	public Suppliers(int sid, String sname, String scontacts, String stel,
			String sadd, String sfax, String sdesc, String date) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.scontacts = scontacts;
		this.stel = stel;
		this.sadd = sadd;
		this.sfax = sfax;
		this.sdesc = sdesc;
		this.date = date;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getScontacts() {
		return scontacts;
	}
	public void setScontacts(String scontacts) {
		this.scontacts = scontacts;
	}
	public String getStel() {
		return stel;
	}
	public void setStel(String stel) {
		this.stel = stel;
	}
	public String getSadd() {
		return sadd;
	}
	public void setSadd(String sadd) {
		this.sadd = sadd;
	}
	public String getSfax() {
		return sfax;
	}
	public void setSfax(String sfax) {
		this.sfax = sfax;
	}
	public String getSdesc() {
		return sdesc;
	}
	public void setSdesc(String sdesc) {
		this.sdesc = sdesc;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
