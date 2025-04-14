package com.suwe.demoservlet.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.suwe.demoservlet.entity.Seat;
import com.suwe.demoservlet.utils.DBUtil;

import javax.lang.model.element.VariableElement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatDao {
	public List<Seat> findSeatsByShowingId(Long showingId) {
		List<Seat> seats = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM seats WHERE showing_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, showingId);
			rs = ps.executeQuery();

			while ( rs.next() ) {
				Seat seat = new Seat();
				seat.setId(rs.getLong("id"));
				seat.setShowingId(rs.getLong("showing_id"));
				seat.setSeatNumber(rs.getString("seat_number"));
				seat.setStatus(rs.getInt("status"));
				seats.add(seat);
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn, ps, rs);
		}
		return seats;
	}

	public List<Seat> findSeatsByShowingIdAndMovieId(Long showingId, Long movieId) {

		List<Seat> seats = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM seats where showing_id IN (SELECT id FROM showings WHERE movie_id =?)";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, movieId);


			ResultSet resultSet = ps.executeQuery();

			while ( resultSet.next() ) {
				Seat seat = new Seat();
				seat.setId(resultSet.getLong("id"));
				seat.setShowingId(resultSet.getLong("showing_id"));
				seat.setSeatNumber(resultSet.getString("seat_number"));
				seat.setStatus(resultSet.getInt("status"));
				seats.add(seat);
			}
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.closeConnection(conn, ps, rs);
		}
		return seats;

	}

	public void updateSeatStatus(Long seatId, int status,Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "UPDATE seats SET status = ? WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setLong(2, seatId);
			ps.executeUpdate();

		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		}
		finally {
			DBUtil.closeConnection(null, ps, rs);
		}

	}

	public boolean checkSeatAvailability(long seatId) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT status FROM seats WHERE id =?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, seatId);
			rs = ps.executeQuery();
			if ( rs.next() ) {
				int status = rs.getInt("status");
				return status == 0;
			}

		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		}finally {
			DBUtil.closeConnection(conn, ps, rs);
		}
		return false;
	}
}
