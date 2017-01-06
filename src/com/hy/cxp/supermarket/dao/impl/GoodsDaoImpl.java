package com.hy.cxp.supermarket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hy.cxp.supermarket.dao.GoodsDao;
import com.hy.cxp.supermarket.entity.Goods;
import com.hy.cxp.supermarket.entity.GoodsView;
import com.hy.cxp.supermarket.util.BaseDao;
import com.hy.cxp.supermarket.util.Page;

public class GoodsDaoImpl extends BaseDao implements GoodsDao {
	// 声明对象
	private ResultSet rs;
	private PreparedStatement ps;
	private Connection con;
	
	
	@Override
	public List<GoodsView> list() {
		// 存放类型数据集合
		List<GoodsView> lists = new ArrayList<GoodsView>();

		// sql语句
		String sql = "select * from goods_view";

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
				GoodsView good = new GoodsView();
				good.setGid(rs.getInt(1));
				good.setGname(rs.getString(2));
				good.setGprice(rs.getFloat(3));
				good.setPhone(rs.getString(4));
				good.setGunit(rs.getString(5));
				// 放在集合中
				lists.add(good);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}

	@Override
	public Goods findById(int id) {
		Goods good = new Goods();
	    String sql="select* from goods where gid=?";
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
				good.setGid(rs.getInt(1));
				good.setGname(rs.getString(2));
				good.setGprice(rs.getFloat(3));
				good.setGunit(rs.getString(4));
				good.setSid(rs.getInt(5));
				good.setGstock(rs.getInt(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return good;
	}

	@Override
	public List<Goods> getPageInfo(Page pages) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Goods good) {
		// 1)sql语句
		String sql = "update goods set gprice=?,gunit=?,sid=?,gstock=? where gid=? and gname=? ";

		// 2)参数值
		Object[] param = {good.getGprice(),good.getGunit(),good.getSid(),good.getGstock(),good.getGid(),good.getGname()};

		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public int del(int id) {
		// 1)sql语句
		String sql = "delete from goods where gid=?";

		// 2)参数值
		Object[] param = {id};

		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public int add(Goods good) {
		// 1)sql语句
		String sql = "insert into goods values(?,?,?,?,?,?)";

		// 2)参数值
		Object[] param = {good.getGid(),good.getGname(),good.getGprice(),good.getGunit(),good.getSid(),good.getGstock()};

		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public int[] findSid() {
		// 存放类型数据集合
		int[] lists = new int[100];

		// sql语句
		String sql = "select sid from suppliers order by sid";

		// 1)获得连接
		con = this.getPoolConnection();

		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			// 3)rs对象
			rs = ps.executeQuery();
			// 遍历数据
			int i=0;
			while (rs.next()) {
				// 4)获得数据
				lists[i]=rs.getInt(1);
				i++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}

	@Override
	public List<GoodsView> findByName(String name) {
		List<GoodsView> lists = new ArrayList<GoodsView>();
	    String sql="select * from goods_view where gname like ?";
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
				GoodsView good = new GoodsView();
				good.setGid(rs.getInt(1));
				good.setGname(rs.getString(2));
				good.setGprice(rs.getFloat(3));
				good.setPhone(rs.getString(4));
				good.setGunit(rs.getString(5));
				// 放在集合中
				lists.add(good);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}
	}


