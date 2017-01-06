package com.hy.cxp.supermarket.biz.impl;

import java.util.List;

import com.hy.cxp.supermarket.biz.GoodsBiz;
import com.hy.cxp.supermarket.dao.GoodsDao;
import com.hy.cxp.supermarket.dao.impl.GoodsDaoImpl;
import com.hy.cxp.supermarket.entity.Goods;
import com.hy.cxp.supermarket.entity.GoodsView;
import com.hy.cxp.supermarket.util.Page;

public class GoodsBizImpl implements GoodsBiz {
GoodsDao dao=new GoodsDaoImpl();
	@Override
	public List<GoodsView> list() {
	return dao.list();
	}

	@Override
	public Goods findById(int id) {
	return dao.findById(id);
	}

	@Override
	public List<Goods> getPageInfo(Page pages) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Goods good) {
      return dao.update(good);
	}

	@Override
	public int del(int id) {
	return dao.del(id);
	}

	@Override
	public int add(Goods good) {
		return dao.add(good);
	}

	@Override
	public int[] findSid() {
	return dao.findSid();
	}

	@Override
	public List<GoodsView> findByName(String name) {
		return dao.findByName(name);
	}



}
