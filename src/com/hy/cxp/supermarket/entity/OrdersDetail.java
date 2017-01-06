package com.hy.cxp.supermarket.entity;

public class OrdersDetail {

	
/*   
 *  <p><strong>订单编号：</strong><span>${order.oid}</span></p>
    <p><strong>商品名称：</strong><span>${order }</span></p>
    <label for>商品单价：</label>
    <p><strong>商品单位：</strong><span>北极</span></p>
    <p><strong>商品数量：</strong><span>22</span></p>
    <p><strong>总金额：</strong><span>22</span></p>
    <p><strong>供应商：</strong><span>描述</span></p>
    <p><strong>是否付款：</strong><span>未付款</span></p>
    */
	
	private String oid;
	private String gname;
	private String gprice;
	private String gunit;
	private String onum;
	private String cost;
	private String sname;
	private String opaid;
	
  
	public OrdersDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrdersDetail(String oid, String gname, String gprice, String gunit,
			String onum, String cost, String sname, String opaid) {
		super();
		this.oid = oid;
		this.gname = gname;
		this.gprice = gprice;
		this.gunit = gunit;
		this.onum = onum;
		this.cost = cost;
		this.sname = sname;
		this.opaid = opaid;
	}
	
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGprice() {
		return gprice;
	}
	public void setGprice(String gprice) {
		this.gprice = gprice;
	}
	public String getGunit() {
		return gunit;
	}
	public void setGunit(String gunit) {
		this.gunit = gunit;
	}
	public String getOnum() {
		return onum;
	}
	public void setOnum(String onum) {
		this.onum = onum;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getOpaid() {
		return opaid;
	}
	public void setOpaid(String opaid) {
		this.opaid = opaid;
	}
	
	
}
