package com.hy.cxp.supermarket.entity;

public class GoodsView {
	/**
	 * gid number primary key,--商品编号 gname nvarchar2(20),--商品名称 gprice
	 * number(10,2),--单价 gunit nvarchar2(10),--单位
	 */
	private int gid;// 用户编号
	private String gname;// 产品名称
	private float gprice;// 产品单价
	private String phone;// 供应商联系电话
	private String gunit;// 产品单位

	public GoodsView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GoodsView(int gid, String gname, float gprice, String phone,
			String gunit) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.gprice = gprice;
		this.phone = phone;
		this.gunit = gunit;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public float getGprice() {
		return gprice;
	}

	public void setGprice(float gprice) {
		this.gprice = gprice;
	}

	public String getGunit() {
		return gunit;
	}

	public void setGunit(String gunit) {
		this.gunit = gunit;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
