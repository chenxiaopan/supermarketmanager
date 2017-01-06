package com.hy.cxp.supermarket.dao;

import java.util.List;

import com.hy.cxp.supermarket.entity.Orders;
import com.hy.cxp.supermarket.entity.OrdersDetail;
import com.hy.cxp.supermarket.entity.OrdersView;
import com.hy.cxp.supermarket.entity.Suppliers;
import com.hy.cxp.supermarket.util.Page;

public interface OrdersDao {
	List<OrdersView> list();//查询所有商品信息
	OrdersDetail findById(int id);//根据id查找商品
	List<OrdersView> getPageInfo(Page pages);
	int update(Orders order);//更新商品信息
	int del(int id);//删除商品信息
	int add(Orders order);//添加商品信息
	int findGid(String gname,int sid);//根据商品名称和供应商名称查找商品id
	List<Suppliers> findSupByGname(int gid);//根据商品的gid查询相应的商品名并查询提供此种商品的所有供应商
	List<Suppliers> findSupByGid(int gid);//根据商品的gid相应的商品名查询提供此种商品的所有供应商
	List<Suppliers> findSupByGname(String gname);//根据商品名查询提供此种商品的所有供应商
	List<OrdersView> search(String gname,String sname,String opaid);//根据商品名称，供应商和是否支付搜索相应的订单
}
