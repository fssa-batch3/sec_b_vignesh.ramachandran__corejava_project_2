package in.fssa.srcatering.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.fssa.srcatering.dao.OrderDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Order;
import in.fssa.srcatering.model.OrderProduct;
import in.fssa.srcatering.model.OrderStatus;
import in.fssa.srcatering.validator.OrderProductValidator;
import in.fssa.srcatering.validator.OrderValidator;
import in.fssa.srcatering.validator.UserValidator;

public class OrderService {

	OrderDAO orderDAO = new OrderDAO();

	/**
	 * 
	 * @param order
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public int createOrder(Order order) throws ValidationException, ServiceException {

		LocalDateTime localDateTime = LocalDateTime.now();
		order.setOrderDate(localDateTime);

		int generatedOrderId = -1;

		try {

			// validate order
			OrderValidator.validateOrder(order);
			
			// create order
			generatedOrderId = orderDAO.create(order);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Order creation failed");
		}
		return generatedOrderId;
	}


	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public List<Order> getAllOrdersByUserId(int userId) throws ValidationException, ServiceException {

		List<Order> orderList = new ArrayList<>();
		try {
			UserValidator.isUserIdIsValid(userId);
			orderList = orderDAO.findAllOrdersByUserId(userId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to get Orders");
		}
		return orderList;
	}

	/**
	 * 
	 * @param orderId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Order getOrderByOrderId(int orderId) throws ValidationException, ServiceException {
		Order order = null;

		try {
			OrderValidator.isOrderIdIsValid(orderId);
			order = orderDAO.findOrderByOrderId(orderId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to get Order");
		}
		return order;
	}

}
