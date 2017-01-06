package com.hy.cxp.supermarket.entity;

import java.io.Serializable;

/**
 * 实体类，封装商品信息的JavaBean 
 * 实现序列化接口以便其实例可以安全的保存在HttpSession中
 * 
 * @author cxp
 * @date 2016-11-15
 * 
 */
public class Goods implements Serializable {
	private static final long serialVersionUID = 1L;

/**
    gid number primary key,--商品编号
	gname nvarchar2(20),--商品名称
	gprice number(10,2),--单价
	gunit nvarchar2(10),--单位
	sid number references suppliers(sid),--供应商编号
	gstock number--库存
*/
	private int gid;//用户编号 
	private String gname;// 产品名称
	private float gprice;// 产品单价
	private String gunit;// 产品单位
	private int sid;//供应商编号
	private int gstock;//库存

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

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getGstock() {
		return gstock;
	}

	public void setGstock(int gstock) {
		this.gstock = gstock;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 构造函数
	 */
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Goods(int gid, String gname, float gprice, String gunit, int sid,
			int gstock) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.gprice = gprice;
		this.gunit = gunit;
		this.sid = sid;
		this.gstock = gstock;
	}


}