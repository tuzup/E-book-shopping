package com.cognizant.ebook.dao;

import java.util.List;

import com.cognizant.ebook.model.Order;
import com.cognizant.ebook.model.OrderInfo;

public interface OrderDao {
	public void addToOrder(long userId, Order newOrder);

	public int getOrderId(long userId);

	public List<OrderInfo> getAllOrders(long userId) throws OrderEmptyException;

	public void storeOrder(long orderId, long userId);

	public List<OrderInfo> getCurrentOrder(long orderId) throws OrderEmptyException;

	public void orderCancellation(long userId, long orderId);

}
