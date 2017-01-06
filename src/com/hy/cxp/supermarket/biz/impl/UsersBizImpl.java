package com.hy.cxp.supermarket.biz.impl;

import java.util.List;

import com.hy.cxp.supermarket.biz.UsersBiz;
import com.hy.cxp.supermarket.dao.impl.UsersDaoImpl;
import com.hy.cxp.supermarket.entity.Users;
import com.hy.cxp.supermarket.entity.UsersView;
import com.hy.cxp.supermarket.util.Page;

public class UsersBizImpl implements UsersBiz {
	
 private UsersDaoImpl dao=new UsersDaoImpl();
	@Override
	public int login(Users user) {
	return dao.login(user);
	}

	@Override
	public int modifypwd(Users user,String newPwd) {
	return dao.modifypwd(user, newPwd);
	}

	@Override
	public List<UsersView> list() {
	return dao.list();
	}

	@Override
	public Users findById(int id) {
	return dao.findById(id);
	}

	@Override
	public List<UsersView> getPageInfo(Page pages) {
	return dao.getPageInfo(pages);
	}

	@Override
	public int update(Users user) {
	return dao.update(user);
	}

	@Override
	public int del(int id) {
	return dao.del(id);
	}

	@Override
	public int add(Users user) {
	return dao.add(user);
	}

	@Override
	public List<UsersView> findByName(String name) {
	return dao.findByName(name);
	}

}
