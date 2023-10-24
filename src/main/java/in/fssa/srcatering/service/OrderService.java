package in.fssa.srcatering.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import in.fssa.srcatering.dao.OrderDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Order;
import in.fssa.srcatering.util.Logger;
import in.fssa.srcatering.validator.OrderValidator;
import in.fssa.srcatering.validator.UserValidator;

public class OrderService {

	OrderDAO orderDAO = new OrderDAO();

	/**
	 * Create a new order and store it in the database.
	 *
	 * @param order The order to be created.
	 * @return The generated order ID.
	 * @throws ValidationException If the provided order is invalid or if the associated address is invalid.
	 * @throws ServiceException If a service error occurs during order creation.
	 */
	public int createOrder(Order order) throws ValidationException, ServiceException {

		int generatedOrderId = -1;

		try {
			
			LocalDateTime localDateTime = LocalDateTime.now();
			order.setOrderDate(localDateTime);

			// validate order 
			OrderValidator.validateOrder(order);
			
			// create order
			generatedOrderId = orderDAO.create(order); 
			
			new AddressBookService().setSelectedTrue(order.getAddressId());

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Order creation failed");
		}
		return generatedOrderId;
	}


	/**
	 * Get a list of all orders associated with a specific user.
	 *
	 * @param userId The ID of the user for whom to retrieve orders.
	 * @return A list of orders associated with the provided user.
	 * @throws ValidationException If the provided user ID is invalid.
	 * @throws ServiceException If a service error occurs while retrieving user orders.
	 */
	public List<Order> getAllOrdersByUserId(int userId) throws ValidationException, ServiceException {

		List<Order> orderList = new ArrayList<>();
		try {
			UserValidator.isUserIdIsValid(userId);
			orderList = orderDAO.findAllOrdersByUserId(userId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Unable to get Orders");
		}
		return orderList;
	}

	/**
	 * Get an order by its order ID.
	 *
	 * @param orderId The ID of the order to retrieve.
	 * @return The order associated with the provided order ID.
	 * @throws ValidationException If the provided order ID is invalid.
	 * @throws ServiceException If a service error occurs while retrieving the order.
	 */
	public Order getOrderByOrderId(int orderId) throws ValidationException, ServiceException {
		Order order = null;

		try {
			OrderValidator.isOrderIdIsValid(orderId);
			order = orderDAO.findOrderByOrderId(orderId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Unable to get Order");
		}
		return order;
	}
	
	/**
	 * Get a list of all orders stored in the database.
	 *
	 * @return A list of all orders.
	 * @throws ServiceException If a service error occurs while retrieving orders.
	 */
	public List<Order> getAllOrders() throws ServiceException{
		
		List<Order> orderList = new ArrayList<>();
		
		try {
			orderList = orderDAO.findAllOrders();
			
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Unable to getAllOrders");
		}
		return orderList;
		
	}

}
