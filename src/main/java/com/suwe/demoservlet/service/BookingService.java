package com.suwe.demoservlet.service;

import com.suwe.demoservlet.dao.OrderDao;
import com.suwe.demoservlet.dao.OrderSeatDao;
import com.suwe.demoservlet.dao.SeatDao;
import com.suwe.demoservlet.dao.ShowingDao;
import com.suwe.demoservlet.entity.Order;
import com.suwe.demoservlet.entity.OrderSeat;
import com.suwe.demoservlet.entity.Showing;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class BookingService {
	private ShowingDao showingDao = new ShowingDao();
	private SeatDao seatDao = new SeatDao();
	private OrderDao orderDao = new OrderDao();
	private OrderSeatDao orderSeatDao = new OrderSeatDao();



	// 创建订单
	public void createOrder(Long userId, Long movieId, BigDecimal price, List<Long> seatIds,Long showingId) {
		Order order = new Order();
		order.setUserId(userId);
		order.setMovieId(movieId);
		order.setPrice(price);
		order.setShowingId(showingId);
		order.setOrderTime(new Timestamp(System.currentTimeMillis()));
		// 保存订单
		orderDao.createOrder(order); // 成功

		// 保存订单与座位的关系
		for (Long seatId : seatIds) {
			OrderSeat orderSeat = new OrderSeat();
			//null
			orderSeat.setOrderId(order.getId());
			orderSeat.setSeatId(seatId);

			orderSeatDao.createOrderSeat(orderSeat);
			//更新座位状态
			seatDao.updateSeatStatus(seatId, 1);
		}
		System.out.println("[成功] 订单创建成功：" + order+"--座位更新成功seatIds:"+seatIds);


	}
}