package com.suwe.demoservlet.entity;

public class Seat {
	private Long id;
	private Long showingId;
	private String seatNumber;
	private Integer status; // 0-可用，1-已售出

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShowingId() {
		return showingId;
	}

	public void setShowingId(Long showingId) {
		this.showingId = showingId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	// Getter和Setter方法
}