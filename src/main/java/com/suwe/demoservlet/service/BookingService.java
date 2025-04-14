package com.suwe.demoservlet.service;

import com.alibaba.fastjson.JSON;
import com.suwe.demoservlet.dao.OrderDao;
import com.suwe.demoservlet.dao.OrderSeatDao;
import com.suwe.demoservlet.dao.SeatDao;
import com.suwe.demoservlet.dao.ShowingDao;
import com.suwe.demoservlet.entity.Order;
import com.suwe.demoservlet.entity.OrderSeat;
import com.suwe.demoservlet.utils.DBUtil;
import com.suwe.demoservlet.utils.Result;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
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

		Connection connection = null;

		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);
			Order order = generateOneOrder(userId, movieId, price, showingId); //创建订单

			//座位类型转换
			List<Long> seatIdsArr = convertSeatIds(seatIdsStr);
			if ( seatIdsArr == null ) {
				return Result.fail("座位已被预定");
			}
			System.out.println("[成功] 座位类型转换成功");

			// 保存订单
			orderDao.createOrder(order,connection);


			int i = Integer.parseInt("qqq");  //人位为制造错误
			System.out.println(i);

			// 保存订单与座位的关系，一个订单对应多个座位id
			for ( Long seatId : seatIdsArr ) {
				OrderSeat orderSeat = new OrderSeat();
				orderSeat.setOrderId(order.getId());
				orderSeat.setSeatId(seatId);
				orderSeatDao.createOrderSeat(orderSeat,connection);//添加订单与座位关系
				//更新座位状态
				seatDao.updateSeatStatus(seatId, 1,connection);
			}
			connection.commit();  //提交事务
			System.out.println("[成功] 订单创建成功：" + order + "--座位更新成功seatIds:" + seatIdsArr);
			return Result.ok("购票成功");

		} catch ( Exception e ) {
			System.out.println("开始回滚------------");
			DBUtil.rollbackCommit(connection);
			return Result.fail("操作失败" + e.getMessage());
		}finally {
			DBUtil.closeConnection(connection);
		}


	}

	private Order generateOneOrder(Long userId, Long movieId, BigDecimal price, Long showingId) {
		Order order = new Order();
		order.setUserId(userId);
		order.setMovieId(movieId);
		order.setPrice(price);
		order.setShowingId(showingId);
		order.setOrderTime(new Timestamp(System.currentTimeMillis()));
		return order;
	}

	private List<Long> convertSeatIds(String[] seatIdsStr) {
		List<Long> seatIdsArr = new ArrayList<>();
		for ( String str : seatIdsStr ) {
			long seatId = Long.parseLong(str);
			if ( !seatService.checkSeatAvailability(seatId) ) {
				return null;
			}
			seatIdsArr.add(seatId);
		}
		return seatIdsArr;
	}
}