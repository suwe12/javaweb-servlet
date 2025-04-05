package com.suwe.demoservlet.service;

import com.suwe.demoservlet.dao.ShowingDao;
import com.suwe.demoservlet.entity.Showing;

import java.util.List;

public class ShowingService {

	private ShowingDao showingDao = new ShowingDao();

	// 获取电影场次
	public List<Showing> getShowings(Long movieId) {
		return showingDao.findShowingsByMovieId(movieId);
	}
}
