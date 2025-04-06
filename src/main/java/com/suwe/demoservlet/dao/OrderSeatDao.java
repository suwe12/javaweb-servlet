package com.suwe.demoservlet.dao;

import com.suwe.demoservlet.entity.OrderSeat;
import com.suwe.demoservlet.utils.DBUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class OrderSeatDao {
	public void createOrderSeat(OrderSeat orderSeat) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO order_seat (order_id, seat_id) VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, orderSeat.getOrderId());
			ps.setLong(2, orderSeat.getSeatId());
			ps.executeUpdate();
		} catch ( SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn, ps, null);
		}
	}
}