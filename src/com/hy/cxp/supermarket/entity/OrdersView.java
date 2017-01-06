package com.hy.cxp.supermarket.entity;

public class OrdersView {
	/*
	 * oid number primary key,--订单编号 gname , --商品名称 sname --供应商 cost --订单金额
	 * opaid number --0 未付款 1 已付款 odate --订单生成时间
	 */

	private int oid;
	private String gname;
	private String sname;
	private float cost;
	private String opaid;
	private String date;

	public OrdersView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersView(int oid, String gname, String sname, float cost,
			String opaid, String date) {
		super();
		this.oid = oid;
		this.gname = gname;
		this.sname = sname;
		this.cost = cost;
		this.opaid = opaid;
		this.date = date;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOpaid() {
		return opaid;
	}

	public void setOpaid(String opaid) {
		this.opaid = opaid;
	}

}
