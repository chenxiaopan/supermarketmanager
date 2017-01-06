package com.hy.cxp.supermarket.dao;

import java.util.List;

import com.hy.cxp.supermarket.entity.Goods;
import com.hy.cxp.supermarket.entity.GoodsView;
import com.hy.cxp.supermarket.util.Page;

public interface GoodsDao {
	List<GoodsView> list();//查询所有商品信息
	Goods findById(int id);//根据id查找商品
	List<GoodsView> findByName(String name);//根据名称查找
	List<Goods> getPageInfo(Page pages);
	int update(Goods good);//更新商品信息
	int del(int id);//删除商品信息
	int add(Goods good);//添加商品信息
	int[] findSid();//查找供应商编号
	
}
