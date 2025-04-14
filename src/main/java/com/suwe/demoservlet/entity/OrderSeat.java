package com.suwe.demoservlet.entity;

//订单与座位关系
public class OrderSeat {
	private Long id;
	private Long orderId; // 订单ID
	private Long seatId;  // 座位ID

	// Getter和Setter方法
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Long getOrderId() { return orderId; }
	public void setOrderId(Long orderId) { this.orderId = orderId; }

	public Long getSeatId() { return seatId; }
	public void setSeatId(Long seatId) { this.seatId = seatId; }
}