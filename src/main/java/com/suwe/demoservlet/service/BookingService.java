package com.suwe.demoservlet.service;

import com.alibaba.fastjson.JSON;
import com.suwe.demoservlet.dao.OrderDao;
import com.suwe.demoservlet.dao.OrderSeatDao;
import com.suwe.demoservlet.dao.SeatDao;
import com.suwe.demoservlet.dao.ShowingDao;
import com.suwe.demoservlet.entity.Order;
import com.suwe.demoservlet.entity.OrderSeat;
import com.suwe.demoservlet.utils.Result;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
	private ShowingDao showingDao = new ShowingDao();
	private SeatDao seatDao = new SeatDao();
	private OrderDao orderDao = new OrderDao();
	private OrderSeatDao orderSeatDao = new OrderSeatDao();
	private SeatService seatService = new SeatService();


	// 创建订单
	public Result createOrder(Long userId, Long movieId, BigDecimal price, String[] seatIdsStr, Long showingId) {

		Order order = generateOneOrder(userId,movieId,price,showingId);

		//座位类型转换
		List<Long> seatIdsArr = new ArrayList<>();
		if ( seatIdsStr != null ) {
			for ( String str : seatIdsStr ) {
				long seatId = Long.parseLong(str);
				boolean b = seatService.checkSeatAvailability(seatId);
				if ( !b ) {
					System.out.println("[显示] 购票不成功，座位已被预定！");
					return Result.fail("座位已被预定");
				}
				seatIdsArr.add(seatId);
			}
		}
		System.out.println("[成功] 座位类型转换成功");


		// 保存订单
		orderDao.createOrder(order);
		// 保存订单与座位的关系，一个订单对应多个座位id
		for ( Long seatId : seatIdsArr ) {
			OrderSeat orderSeat = new OrderSeat();
			orderSeat.setOrderId(order.getId());
			orderSeat.setSeatId(seatId);
			orderSeatDao.createOrderSeat(orderSeat);//添加订单与座位关系
			//更新座位状态
			seatDao.updateSeatStatus(seatId, 1);
		}
		System.out.println("[成功] 订单创建成功：" + order + "--座位更新成功seatIds:" + seatIdsArr);
		return Result.ok("购票成功");
	}

	private Order generateOneOrder(Long userId, Long movieId, BigDecimal price, Long showingId){
		Order order = new Order();
		order.setUserId(userId);
		order.setMovieId(movieId);
		order.setPrice(price);
		order.setShowingId(showingId);
		order.setOrderTime(new Timestamp(System.currentTimeMillis()));
		return order;
	}
}