package com.suwe.demoservlet.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {
	private Long id;
	private Long userId;
	private Long movieId;
	private Long showingId;
	private BigDecimal price;
	private Timestamp orderTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getShowingId() {
		return showingId;
	}

	public void setShowingId(Long showingId) {
		this.showingId = showingId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	// Getter和Setter方法
}