package com.hy.cxp.supermarket.entity;

public class Orders {
	
	
/*	
	oid number primary key,--订单编号
	gid number references goods(gid), --商品编号
	onum number not null, --商品数量
	odate date, --订单生成时间
	opaid number  --0 未付款 1 已付款
	*/
	
	private int oid;
	private int gid;
	private int onum;
	private String date;
	private int opaid;
	
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Orders(int oid, int gid, int onum, String date, int opaid) {
		super();
		this.oid = oid;
		this.gid = gid;
		this.onum = onum;
		this.date = date;
		this.opaid = opaid;
	}


	public int getOid() {
		return oid;
	}


	public void setOid(int oid) {
		this.oid = oid;
	}


	public int getGid() {
		return gid;
	}


	public void setGid(int gid) {
		this.gid = gid;
	}


	public int getOnum() {
		return onum;
	}


	public void setOnum(int onum) {
		this.onum = onum;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getOpaid() {
		return opaid;
	}


	public void setOpaid(int opaid) {
		this.opaid = opaid;
	}
	
}
