package com.suwe.demoservlet.dao;

import com.suwe.demoservlet.entity.Movie;
import com.suwe.demoservlet.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//
public class MovieDao {

	public List<Movie> findAllMovies() {
		List<Movie> movies = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM movies WHERE status != 2 ORDER BY release_date DESC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Movie movie = new Movie();
				movie.setId(rs.getLong("id"));
				movie.setTitle(rs.getString("title"));
				movie.setPrice(rs.getDouble("price"));
				movie.setReleaseDate(rs.getDate("release_date"));
				movie.setImageUrl(rs.getString("image_url"));
				movie.setDescription(rs.getString("description"));
				movie.setDuration(rs.getInt("duration"));
				movie.setType(rs.getString("type"));
				movie.setDirector(rs.getString("director"));
				movie.setActors(rs.getString("actors"));
				movie.setStatus(rs.getInt("status"));
				movies.add(movie);
			}
		} catch ( SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn, ps, rs);
		}
		return movies;
	}

	public Movie findById(Long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Movie movie = null;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM movies WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				movie = new Movie();
				movie.setId(rs.getLong("id"));
				movie.setTitle(rs.getString("title"));
				movie.setPrice(rs.getDouble("price"));
				movie.setReleaseDate(rs.getDate("release_date"));
				movie.setImageUrl(rs.getString("image_url"));
				movie.setDescription(rs.getString("description"));
				movie.setDuration(rs.getInt("duration"));
				movie.setType(rs.getString("type"));
				movie.setDirector(rs.getString("director"));
				movie.setActors(rs.getString("actors"));
				movie.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn, ps, rs);
		}
		return movie;
	}
}
