package com.hy.cxp.supermarket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hy.cxp.supermarket.dao.UsersDao;
import com.hy.cxp.supermarket.entity.Users;
import com.hy.cxp.supermarket.entity.UsersView;
import com.hy.cxp.supermarket.util.BaseDao;
import com.hy.cxp.supermarket.util.Page;

public class UsersDaoImpl extends BaseDao implements UsersDao {

	// 声明对象
	private ResultSet rs;
	private PreparedStatement ps;
	private Connection con;
	
	@Override
	public int login(Users user) {
		String sql = "select count(1) from users where uname=? and upwd=?";

		// 1)获得连接
		con = this.getPoolConnection();

		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUname());
			ps.setString(2, user.getUpwd());

			// 3)rs对象
			rs = ps.executeQuery();

			rs.next();

			// 4)获得数据
			return rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return 0;
	}

	@Override
	public int modifypwd(Users user,String newPwd) {
		String sql = "update users set upwd=? where  uname=? and upwd=?";
		// 1.获得连接
		con = getPoolConnection();

		// 2)参数值
		Object[] param = {newPwd, user.getUname(),user.getUpwd() };

		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public List<UsersView> list() {
		// 存放类型数据集合
		List<UsersView> lists = new ArrayList<UsersView>();

		// sql语句
		String sql = "select * from users_view order by userid";

		// 1)获得连接
		con = this.getPoolConnection();

		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			// 3)rs对象
			rs = ps.executeQuery();
			// 遍历数据
			while (rs.next()) {
				// 4)获得数据
				UsersView user = new UsersView();
				user.setUserid(rs.getInt(1));
				user.setUname(rs.getString(2));
				user.setUsex(rs.getString(3));
				user.setUbirth(rs.getString(4));
				user.setUtel(rs.getString(5));
				user.setUtype(rs.getString(6));
				// 放在集合中
				lists.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}

	@Override
	public Users findById(int id) {
		Users user = new Users();
    String sql="select userid,uname,usex,to_char(ubirth,'YYYY-MM-DD'),utel,uadd,utype from users where userid=?";
	// 1)获得连接
	con = this.getPoolConnection();
	// 2)ps对象
	try {
		ps = con.prepareStatement(sql);
		//设置参数
		ps.setInt(1, id);
		// 3)rs对象
		rs = ps.executeQuery();
		// 遍历数据
		if(rs.next()) {
			// 4)获得数据
			user.setUserid(rs.getInt(1));
			user.setUname(rs.getString(2));
			user.setUsex(rs.getString(3));
			user.setUbirth(rs.getString(4));
			user.setUtel(rs.getString(5));
			user.setUadd(rs.getString(6));
			user.setUtype(rs.getInt(7));
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		this.closeAll(rs, ps, con); // 关闭连接
	}

	return user;
}
	

	
	/**
	 * 分页
	 */
	@Override
	public List<UsersView> getPageInfo(Page pages) {
		List<UsersView> lists = new ArrayList<UsersView>();

		String sql = " select em.* from( select e.* ,rownum r from( select * from users_view order by userid ) e where rownum<=(?))"
				+ " em where r>?";

		// 1)获得连接
		con = this.getPoolConnection();

		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			// 设置参数的值
			//当前页面的第一行
			ps.setInt(1, pages.getCurrPageNo() * pages.getPageSize());
			//当前页面的最后一行
			ps.setInt(2, (pages.getCurrPageNo() - 1) * pages.getPageSize());

			// 3)rs对象
			rs = ps.executeQuery();

			// 4)遍历数据
			while (rs.next()) {
				// 4)获得数据
				UsersView user = new UsersView();
				user.setUserid(rs.getInt(1));
				user.setUname(rs.getString(2));
				user.setUsex(rs.getString(3));
				user.setUbirth(rs.getString(4));
				user.setUtel(rs.getString(5));
				user.setUtype(rs.getString(6));
				// 放在集合中
				lists.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}
	
	@Override
	/**
	 * 更新用户信息
	 */
	public int update(Users user) {
		// 1)sql语句
		String sql = "update users set usex=?,ubirth=to_date(?,'yyyy-mm-dd'),utel=?,uadd=?,utype=? where userid=?";

		// 2)参数值
		Object[] param = { user.getUsex(), user.getUbirth(),
			 user.getUtel(), user.getUadd(),user.getUtype(),user.getUserid() };

		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public int del(int id) {
		// 1)sql语句
		String sql = "delete from users where userid=?";

		// 2)参数值
		Object[] param = {id};

		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public int add(Users user) {
		// 1)sql语句
		String sql = "insert into users values(?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?)";

		// 2)参数值
		//截取日期，去掉时分秒
		String birth=user.getUbirth().split(" ")[0];
		Object[] param = {user.getUserid(),user.getUname(),user.getUpwd(), user.getUsex(), birth,
			 user.getUtel(), user.getUadd(),user.getUtype()};

		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public List<UsersView> findByName(String name) {
		List<UsersView> lists = new ArrayList<UsersView>();
	    String sql="select* from users_view where uname like ?";
		// 1)获得连接
		con = this.getPoolConnection();
		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			//设置参数
			ps.setString(1,"%"+name+"%");
			// 3)rs对象
			rs = ps.executeQuery();
			// 遍历数据
			while(rs.next()) {
				// 4)获得数据
				UsersView user = new UsersView();
				user.setUserid(rs.getInt(1));
				user.setUname(rs.getString(2));
				user.setUsex(rs.getString(3));
				user.setUbirth(rs.getString(4));
				user.setUtel(rs.getString(5));
				user.setUtype(rs.getString(6));
				lists.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}
	
}
