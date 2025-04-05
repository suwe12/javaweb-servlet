package com.suwe.demoservlet.service;

import com.suwe.demoservlet.dao.SeatDao;
import com.suwe.demoservlet.entity.Seat;

import java.util.List;

public class SeatService {

	private SeatDao seatDao = new SeatDao();

	public List<Seat> findSeatsByShowingId(Long showingId) {
		return seatDao.findSeatsByShowingId(showingId);
	}

	public List<Seat> findSeatsByShowingIdAndMovieId(Long showingId, Long movieId) {
		return seatDao.findSeatsByShowingIdAndMovieId(showingId, movieId);
	}


	public boolean checkSeatAvailability(long seatId) {

		return seatDao.checkSeatAvailability(seatId);
	}
}
