package com.hy.cxp.supermarket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hy.cxp.supermarket.dao.OrdersDao;
import com.hy.cxp.supermarket.entity.Orders;
import com.hy.cxp.supermarket.entity.OrdersDetail;
import com.hy.cxp.supermarket.entity.OrdersView;
import com.hy.cxp.supermarket.entity.Suppliers;
import com.hy.cxp.supermarket.util.BaseDao;
import com.hy.cxp.supermarket.util.Page;

/**
 * 一定要关闭连接，否则tomcat会因为资源无法释放而卡死
 * 
 */

public class OrdersDaoImpl extends BaseDao implements OrdersDao {
	// 声明对象
	private ResultSet rs;
	private PreparedStatement ps;
	private Connection con;

	@Override
	public List<OrdersView> list() {
		// 存放类型数据集合
		List<OrdersView> lists = new ArrayList<OrdersView>();

		// sql语句
		String sql = "select* from orders_view";

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
				OrdersView order = new OrdersView();
				order.setOid(rs.getInt(1));
				order.setGname(rs.getString(2));
				order.setSname(rs.getString(3));
				order.setCost(rs.getFloat(4));
				order.setOpaid(rs.getString(5));
				order.setDate(rs.getString(6));
				// 放在集合中
				lists.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}

	@Override
	public OrdersDetail findById(int id) {

		OrdersDetail order = new OrdersDetail();

		String sql = "select* from orders_detail where oid=?";
		// 1)获得连接
		con = this.getPoolConnection();
		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			// 设置参数
			ps.setInt(1, id);
			// 3)rs对象
			rs = ps.executeQuery();
			// 遍历数据
			if (rs.next()) {
				// 4)获得数据
				order.setOid(rs.getString(1));
				order.setGname(rs.getString(2));
				order.setGunit(rs.getString(3));
				order.setOnum(rs.getString(4));
				order.setCost(rs.getString(5));
				order.setSname(rs.getString(6));
				order.setOpaid(rs.getString(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}
		return order;
	}

	@Override
	public List<OrdersView> getPageInfo(Page pages) {
		List<OrdersView> lists = new ArrayList<OrdersView>();

		String sql = " select em.* from( select e.* ,rownum r from( select * from orders_view order by oid ) e where rownum<=(?))"
				+ " em where r>?";

		// 1)获得连接
		con = this.getPoolConnection();

		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			// 设置参数的值
			// 当前页面的第一行
			ps.setInt(1, pages.getCurrPageNo() * pages.getPageSize());
			// 当前页面的最后一行
			ps.setInt(2, (pages.getCurrPageNo() - 1) * pages.getPageSize());

			// 3)rs对象
			rs = ps.executeQuery();

			// 4)遍历数据
			while (rs.next()) {
				// 4)获得数据
				OrdersView order = new OrdersView();
				order.setOid(rs.getInt(1));
				order.setGname(rs.getString(2));
				order.setSname(rs.getString(3));
				order.setCost(rs.getFloat(4));
				String date = rs.getString(5).split(" ")[0];
				order.setOpaid(rs.getString(6));
				order.setDate(date);
				// 放在集合中
				lists.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}
		return lists;
	}

	@Override
	public int update(Orders order) {
		// 1)sql语句
		String sql = "update orders set gid=?,onum=?,opaid=?  where oid=?";

		// 2)参数值
		Object[] param = { order.getGid(), order.getOnum(), order.getOpaid(),
				order.getOid() };
		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public int del(int id) {
		// 1)sql语句
		String sql = "delete from orders where oid=?";

		// 2)参数值
		Object[] param = { id };

		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public int add(Orders order) {
		// 1)sql语句
		String sql = "insert into orders values(?,?,?,to_date(?,'yyyy-mm-dd'),?)";

		// 2)参数值
		Object[] param = { order.getOid(), order.getGid(), order.getOnum(),
				order.getDate(), order.getOpaid() };
		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public int findGid(String gname, int sid) {
		int gid = 0;
		String sql = "select g.gid from goods g,suppliers s where g.sid=s.sid and g.gname=? and s.sid=? ";
		// 1)获得连接
		con = this.getPoolConnection();
		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			// 设置参数
			ps.setString(1, gname);
			ps.setInt(2, sid);
			// 3)rs对象
			rs = ps.executeQuery();
			// 遍历数据
			if (rs.next()) {
				System.out.println(rs.getInt(1));
				gid = rs.getInt(1);
				// 4)获得数据
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return gid;
	}

	@Override
	public List<Suppliers> findSupByGname(int gid) {
		List<Suppliers> lists = new ArrayList<Suppliers>();
		String sql = "select s.sid,s.sname from goods g,suppliers s where g.sid=s.sid and g.gname=(select  gname from goods where gid=(select gid from orders where oid=?))";
		// 1)获得连接
		con = this.getPoolConnection();
		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			// 设置参数
			ps.setInt(1, gid);
			// 3)rs对象
			rs = ps.executeQuery();
			// 遍历数据
			while (rs.next()) {
				// 4)获得数据
				Suppliers supplier = new Suppliers();
				supplier.setSid(rs.getInt(1));
				supplier.setSname(rs.getString(2));
				// 5.将数据添加进集合里
				lists.add(supplier);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}

	@Override
	public List<Suppliers> findSupByGid(int gid) {
		List<Suppliers> lists = new ArrayList<Suppliers>();
		String sql = "select s.sid,s.sname from goods g,suppliers s where g.sid=s.sid and g.gname=(select  gname from goods where gid=?)";
		// 1)获得连接
		con = this.getPoolConnection();
		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			// 设置参数
			ps.setInt(1, gid);
			// 3)rs对象
			rs = ps.executeQuery();
			// 遍历数据
			while (rs.next()) {
				// 4)获得数据
				Suppliers supplier = new Suppliers();
				supplier.setSid(rs.getInt(1));
				supplier.setSname(rs.getString(2));
				// 5.将数据添加进集合里
				lists.add(supplier);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}

	@Override
	public List<Suppliers> findSupByGname(String gname) {
		List<Suppliers> lists = new ArrayList<Suppliers>();
		String sql = "select s.sid,s.sname from goods g,suppliers s where g.sid=s.sid and g.gname=?";
		// 1)获得连接
		con = this.getPoolConnection();
		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			// 设置参数
			ps.setString(1, gname);
			// 3)rs对象
			rs = ps.executeQuery();
			// 遍历数据
			while (rs.next()) {
				// 4)获得数据
				Suppliers supplier = new Suppliers();
				supplier.setSid(rs.getInt(1));
				supplier.setSname(rs.getString(2));
				// 5.将数据添加进集合里
				lists.add(supplier);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}

	@Override
	public List<OrdersView> search(String gname, String sname, String opaid) {
		// 存放类型数据集合
		List<OrdersView> lists = new ArrayList<OrdersView>();

		// sql语句
		String sql = "select* from orders_view where gname like ? and sname like ? and paid like ? ";

		// 1)获得连接
		con = this.getPoolConnection();

		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,"%"+ gname+"%");
			ps.setString(2, "%"+ sname+"%");
			ps.setString(3, "%"+ opaid+"%");
			// 3)rs对象
			rs = ps.executeQuery();
			// 遍历数据
			while (rs.next()) {
				// 4)获得数据
				OrdersView order = new OrdersView();
				order.setOid(rs.getInt(1));
				order.setGname(rs.getString(2));
				order.setSname(rs.getString(3));
				order.setCost(rs.getFloat(4));
				order.setOpaid(rs.getString(6));
				String date = rs.getString(5).split(" ")[0];
				order.setDate(date);
				// 放在集合中
				lists.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}

}
