package in.fssa.srcatering.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import in.fssa.srcatering.dao.OrderProductDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.OrderProduct;
import in.fssa.srcatering.model.OrderStatus;
import in.fssa.srcatering.util.StringUtil;
import in.fssa.srcatering.validator.OrderProductValidator;
import in.fssa.srcatering.validator.OrderValidator;

public class OrderProductService {

	OrderProductDAO orderProductDAO = new OrderProductDAO();

	public void createOrderProduct(int orderId, OrderProduct orderProduct)
			throws ValidationException, ServiceException {

		try {

			Map<Integer, Integer> dishIdPriceIdMap = new CategoryDishService()
					.getDishIdAndPriceIdByMenuIdAndCategoryId(orderProduct.getMenuId(), orderProduct.getCategoryId());

			// setting orderId, dishId, priceId
			orderProduct.setOrderId(orderId);
			orderProduct.setDishIdPriceIdMap(dishIdPriceIdMap);

			// setting order status
			orderProduct.setOrderStatus(OrderStatus.NOT_DELIVERED);

			OrderValidator.isOrderIdIsValid(orderProduct.getOrderId());

			orderProductDAO.create(orderProduct);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param status
	 * @param orderId
	 * @param menuId
	 * @param categoryId
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void updateOrderStatusAndCancelDate(OrderStatus status, int orderId, int menuId, int categoryId, String cancelReason)
			throws ValidationException, ServiceException {

		try {

			OrderProductValidator.validateOrderProductUpdate(orderId, menuId, categoryId);

			if (status == OrderStatus.CANCELLED) {
				StringUtil.rejectIfInvalidString(cancelReason, "CancelReason");

				LocalDateTime cancelDateTime = LocalDateTime.now();
				orderProductDAO.updateOrderStatusAndCancelDate(status, orderId, menuId, categoryId, cancelDateTime, cancelReason);

			} else {

				orderProductDAO.updateOrderStatusAndCancelDate(status, orderId, menuId, categoryId, null,null);
			}

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Order updation failed");
		}

	}

	/**
	 * 
	 * @param orderId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Set<OrderProduct> getAllOrderProductsByOrderId(int orderId) throws ValidationException, ServiceException {

		Set<OrderProduct> orderProductList = new HashSet<>();
		try {
			OrderValidator.isOrderIdIsValid(orderId);
			orderProductList = orderProductDAO.findAllOrderProductsByOrderId(orderId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return orderProductList;

	}

	/**
	 * 
	 * @param orderId
	 * @param menuId
	 * @param categoryId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException 
	 */
	public OrderProduct getOrderProductByOrderIdAndMenuIdAndCategoryId(int orderId, int menuId, int categoryId)
			throws ValidationException, ServiceException {
		
		OrderProduct orderProduct = null;

		try {
			OrderProductValidator.validateOrderProductUpdate(orderId, menuId, categoryId);

			orderProduct = orderProductDAO.findOrderProductByOrderIdAndMenuIdAndCategoryId(orderId, menuId, categoryId);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
		return orderProduct;
	}
	
	/**
	 * 
	 * @param orderId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Set<Map.Entry<Integer, Integer>> getOrderedMenuIdAndCategoryIdByOrderId(int orderId) throws ValidationException, ServiceException{
		
//		Map<Integer, Integer> menuIdCategoryIdMap = new HashMap<>();
		
		Set<Map.Entry<Integer, Integer>> menuIdCategoryIdMap = new HashSet<>();
		
		OrderValidator.isOrderIdIsValid(orderId);
		
		try {
			menuIdCategoryIdMap = orderProductDAO.findOrderedMenuIdAndCategoryIdByOrderId(orderId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return menuIdCategoryIdMap;
		
	}

}
