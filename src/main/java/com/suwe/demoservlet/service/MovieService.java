package com.suwe.demoservlet.service;

import com.suwe.demoservlet.dao.MovieDao;
import com.suwe.demoservlet.entity.Movie;
import com.suwe.demoservlet.utils.Result;

import java.util.List;

public class MovieService {
	private MovieDao movieDao = new MovieDao();

//	public Result getAllMovies() {
//		List<Movie> movies = movieDao.findAllMovies();
//		return Result.ok(movies, (long) movies.size());
//	}

	public List<Movie> getAllMovies() {
		return movieDao.findAllMovies();
	}

	public Result getMovieById(Long id) {
		if (id == null) {
			return Result.fail("电影ID不能为空");
		}
		Movie movie = movieDao.findById(id);
		if (movie == null) {
			return Result.fail("电影不存在");
		}
		return Result.ok(movie);
	}
}