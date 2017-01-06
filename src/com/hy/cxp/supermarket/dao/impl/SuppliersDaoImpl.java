package com.hy.cxp.supermarket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hy.cxp.supermarket.dao.SuppliersDao;
import com.hy.cxp.supermarket.entity.Suppliers;
import com.hy.cxp.supermarket.util.BaseDao;
import com.hy.cxp.supermarket.util.Page;

public class SuppliersDaoImpl extends BaseDao implements SuppliersDao {
	// 声明对象
	private ResultSet rs;
	private PreparedStatement ps;
	private Connection con;
	
	@Override
	public List<Suppliers> list() {
		// 存放类型数据集合
				List<Suppliers> lists = new ArrayList<Suppliers>();

				// sql语句
				String sql = "select sid,sname,scontacts,stel,sfax,sdate from suppliers order by sid";

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
						Suppliers suppliers = new Suppliers();
						suppliers.setSid(rs.getInt(1));
						suppliers.setSname(rs.getString(2));
						suppliers.setScontacts(rs.getString(3));
						suppliers.setStel(rs.getString(4));
						suppliers.setSfax(rs.getString(5));
						suppliers.setDate(rs.getString(6));
						// 放在集合中
						lists.add(suppliers);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.closeAll(rs, ps, con); // 关闭连接
				}

				return lists;
	}

	@Override
	public Suppliers findById(int id) {
		Suppliers supplier = new Suppliers();
	    String sql="select sid,sname,scontacts,stel,sfax,sdesc from suppliers where sid=?";
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
				supplier.setSid(rs.getInt(1));
				supplier.setSname(rs.getString(2));
				supplier.setScontacts(rs.getString(3));
				supplier.setStel(rs.getString(4));
				supplier.setSfax(rs.getString(5));
				supplier.setSdesc(rs.getString(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return supplier;
	}
	@Override
	public Suppliers findByIdForUpdate(int id) {
		Suppliers supplier = new Suppliers();
		String sql="select sid,sname,scontacts,stel,sadd,sfax,sdesc from suppliers where sid=?";
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
				supplier.setSid(rs.getInt(1));
				supplier.setSname(rs.getString(2));
				supplier.setScontacts(rs.getString(3));
				supplier.setStel(rs.getString(4));
				supplier.setSadd(rs.getString(5));
				supplier.setSfax(rs.getString(6));
				supplier.setSdesc(rs.getString(7));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}
		
		return supplier;
	}

	@Override
	public List<Suppliers> getPageInfo(Page pages) {
		List<Suppliers> lists = new ArrayList<Suppliers>();

		String sql = " select em.* from( select e.* ,rownum r from( select * from suppliers_view order by sid ) e where rownum<=(?))"
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
				Suppliers suppliers = new Suppliers();
				suppliers.setSid(rs.getInt(1));
				suppliers.setSname(rs.getString(2));
				suppliers.setScontacts(rs.getString(3));
				suppliers.setStel(rs.getString(4));
				suppliers.setSfax(rs.getString(5));
				String date=rs.getString(6);
				suppliers.setDate(date.split(" ")[0]);
				// 放在集合中
				lists.add(suppliers);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}

	@Override
	public int update(Suppliers suppliers) {
		// 1)sql语句
				String sql = "update suppliers set  stel=?,sadd=?,sfax=?,sdesc=? where sid=?";

				// 2)参数值
				Object[] param = {suppliers.getStel(),suppliers.getSadd(),suppliers.getSfax(),suppliers.getSdesc(),suppliers.getSid()};

				// 3)调用方法
				int count = this.executeUpdateSql(sql, param);
				return count;
	}

	@Override
	public int del(int id) {
		// 1)sql语句
		String sql = "delete from suppliers where sid=?";

		// 2)参数值
		Object[] param = {id};

		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public int add(Suppliers suppliers) {
		// 1)sql语句
		String sql = "insert into suppliers values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'))";

		// 2)参数值
		//截取日期，去掉时分秒
		String date=suppliers.getDate().split(" ")[0];
		Object[] param = {suppliers.getSid(),suppliers.getSname(),suppliers.getScontacts(), suppliers.getStel(),suppliers.getSadd(),
				suppliers.getSfax(), suppliers.getSdesc(),date};

		// 3)调用方法
		int count = this.executeUpdateSql(sql, param);
		return count;
	}

	@Override
	public List<Suppliers> findByName(String name) {
		// 存放类型数据集合
		List<Suppliers> lists = new ArrayList<Suppliers>();

		// sql语句
		String sql = "select sid,sname,scontacts,stel,sfax,sdate from suppliers  where sname like ? order by sid";

		// 1)获得连接
		con = this.getPoolConnection();

		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,"%"+name+"%");
			// 3)rs对象
			rs = ps.executeQuery();
			// 遍历数据
			while (rs.next()) {
				// 4)获得数据
				Suppliers suppliers = new Suppliers();
				suppliers.setSid(rs.getInt(1));
				suppliers.setSname(rs.getString(2));
				suppliers.setScontacts(rs.getString(3));
				suppliers.setStel(rs.getString(4));
				suppliers.setSfax(rs.getString(5));
				suppliers.setDate(rs.getString(6).split(" ")[0]);
				// 放在集合中
				lists.add(suppliers);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(rs, ps, con); // 关闭连接
		}

		return lists;
	}


}
