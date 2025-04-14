package com.suwe.demoservlet.dao;

import com.suwe.demoservlet.entity.Order;
import com.suwe.demoservlet.utils.DBUtil;

import java.sql.*;

public class OrderDao {
	public void createOrder(Order order,Connection conn) {

		PreparedStatement ps = null;  //预编译的 SQL 语句对象
		ResultSet generatedKeys = null;  // 新增：用于获取自增 ID

		try {
			String sql = "INSERT INTO orders (user_id, movie_id, price,showing_id) VALUES (?, ?, ?, ?)";
			// 启用 RETURN_GENERATED_KEYS 以获取自增 ID
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// 设置参数
			ps.setLong(1, order.getUserId());  //将 Java 对象的值绑定到 SQL 的占位符 ?
			ps.setLong(2, order.getMovieId());
			ps.setBigDecimal(3, order.getPrice());
			ps.setLong(4, order.getShowingId());

			// 执行写操作（如 INSERT/UPDATE/DELETE），返回受影响的行数
			ps.executeUpdate();

			// 新增：获取自增 ID 并回填到实体类
			generatedKeys = ps.getGeneratedKeys(); //获取包含自增ID的ResultSet
			if ( generatedKeys != null && generatedKeys.next() ) {  // 确保结果集有效且有数据
				Long generatedId = generatedKeys.getLong(1);  // 提取第一列的ID（Long类型）
				order.setId(generatedId);  // 关键：将 ID 设置到 Order 对象
			} else {
				throw new SQLException("无法获取生成的订单 ID");
			}

		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			// 修改：关闭 ResultSet
			DBUtil.closeConnection(null, ps, generatedKeys);  // 确保 DBUtil 能关闭 ResultSet
		}
	}
}