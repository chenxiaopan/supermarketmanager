package com.hy.cxp.supermarket.biz;

import java.util.List;

import com.hy.cxp.supermarket.entity.Orders;
import com.hy.cxp.supermarket.entity.OrdersDetail;
import com.hy.cxp.supermarket.entity.OrdersView;
import com.hy.cxp.supermarket.entity.Suppliers;
import com.hy.cxp.supermarket.util.Page;

public interface OrdersBiz {
	List<OrdersView> list();//查询所有订单信息
	OrdersDetail findById(int id);//根据id查找订单
	List<OrdersView> getPageInfo(Page pages);
	int update(Orders order);//更新订单信息
	int del(int id);//删除订单信息
	int add(Orders order);//添加订单信息
	int findGid(String gname,int sid);//根据商品名称和供应商名称查找商品id
	List<Suppliers> findSupByGname(int gid);//根据订单的oid找到商品的gid相应的商品名后查询提供此种商品的所有供应商
	List<Suppliers> findSupByGid(int gid);//根据商品的gid相应的商品名查询提供此种商品的所有供应商
	List<Suppliers> findSupByGname(String gname);//根据商品名查询提供此种商品的所有供应商
	List<OrdersView> search(String gname,String sname,String opaid);//根据商品名称，供应商名称和是否支付搜索相应的订单
}
