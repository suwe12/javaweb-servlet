package com.suwe.demoservlet.dao;

import com.suwe.demoservlet.entity.Showing;
import com.suwe.demoservlet.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//查询场次
public class ShowingDao {

	//根据电影id查询场次
	public List<Showing> findShowingsByMovieId(Long movieId) {
		List<Showing> showings = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM showings WHERE movie_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, movieId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Showing showing = new Showing();
				showing.setId(rs.getLong("id"));
				showing.setMovieId(rs.getLong("movie_id"));
				showing.setTheater(rs.getString("theater"));
				showing.setShowTime(rs.getTime("show_time"));
				showing.setDate(rs.getDate("date"));
				showings.add(showing);
			}
		} catch ( SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn, ps, rs);
		}
		return showings;
	}
}