package com.hy.cxp.supermarket.dao;

import java.util.List;

import com.hy.cxp.supermarket.entity.Users;
import com.hy.cxp.supermarket.entity.UsersView;
import com.hy.cxp.supermarket.util.Page;

public interface UsersDao {
int login(Users user);//用户登录
int modifypwd(Users user,String newPwd);//修改密码
List<UsersView> list();//查询所有用户
Users findById(int id);//根据id查找用户
List<UsersView> findByName(String name);//根据用户名查找用户
List<UsersView> getPageInfo(Page pages);
int update(Users user);//更新用户信息
int del(int id);//删除用户
int add(Users user);//添加
}
