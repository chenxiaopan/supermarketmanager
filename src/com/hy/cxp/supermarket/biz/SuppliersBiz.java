package com.hy.cxp.supermarket.biz;

import java.util.List;

import com.hy.cxp.supermarket.entity.Suppliers;
import com.hy.cxp.supermarket.util.Page;

public interface SuppliersBiz {
	List<Suppliers> list();//查询所有用户
	Suppliers findById(int id);//根据id查找用户
	Suppliers findByIdForUpdate(int id);//根据id查找用户
	List<Suppliers> findByName(String name);//根据名称查找
	List<Suppliers> getPageInfo(Page pages);
	int update(Suppliers suppliers);//更新用户信息
	int del(int id);//删除用户
	int add(Suppliers suppliers);//添加
}
