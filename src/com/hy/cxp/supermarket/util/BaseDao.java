package com.hy.cxp.supermarket.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 
 * @author cxp
 * @date 2016-11-01
 * 
 *       连接数据库
 */
public class BaseDao {


	/**
	 * 连接池
	 * 
	 * @return
	 */
	public Connection getPoolConnection() {
		// 1.获得上下文对象
		Context context;
		try {
			context = new InitialContext();
			// 2.获得数据源
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/orcl");
			// 3.获得连接对象
			return dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭连接
	 * 
	 * @param rs
	 * @param ps
	 * @param con
	 */
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 添加,删除,修改
	 * 
	 * @param sql
	 * @param param
	 *            可变数组; 没有数据就不需要写!
	 * @return
	 */
	public int executeUpdateSql(String sql, Object... param) {
		// 1)连接对象
		Connection con = this.getPoolConnection();
		PreparedStatement ps = null;

		// 2)ps对象
		try {
			ps = con.prepareStatement(sql);

			// 3)设置参数的值 ?==>对应的值
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					ps.setObject(i + 1, param[i]);
				}
			}
			// 4)执行
			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			// 5)关闭连接
			this.closeAll(null, ps, con);
		}

		return 0;

	}

}
