package in.fssa.srcatering.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import in.fssa.srcatering.dao.OrderProductDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.CaterApproval;
import in.fssa.srcatering.model.OrderProduct;
import in.fssa.srcatering.model.OrderStatus;
import in.fssa.srcatering.util.Logger;
import in.fssa.srcatering.util.StringUtil;
import in.fssa.srcatering.validator.OrderProductValidator;
import in.fssa.srcatering.validator.OrderValidator;

public class OrderProductService {

	OrderProductDAO orderProductDAO = new OrderProductDAO();

	/**
	 * Create one or more order products in the database for a specific order. Each order product corresponds to a dish in the order.
	 * 
	 * @param orderId
	 * @param orderProduct
	 * @throws ValidationException
	 * @throws ServiceException
	 */
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
			
			OrderProductValidator.validateOrderProduct(orderProduct);

			orderProductDAO.create(orderProduct); 

		} catch (DAOException e) { 
			Logger.error(e);
			throw new ServiceException(e.getMessage()); 
		}
	}

	/**
	 * Update the order status and cancel date for a specific order product. If the status is set to CANCELLED,
	 * the cancel date and reason will be recorded.
	 *
	 * @param status The new order status (e.g., CANCELLED or any other status).
	 * @param orderId The ID of the order product.
	 * @param menuId The ID of the menu associated with the order product.
	 * @param categoryId The ID of the category associated with the order product.
	 * @param cancelReason The reason for cancelling the order, required when status is CANCELLED.
	 * @throws ValidationException If any of the provided parameters are invalid.
	 * @throws ServiceException If a service error occurs during the update process.
	 */
	public void updateOrderStatusAndCancelDate(OrderStatus status, int orderId, int menuId, int categoryId,
			String cancelReason) throws ValidationException, ServiceException {

		try {

			OrderProductValidator.validateOrderProductUpdate(orderId, menuId, categoryId);

			if (status == OrderStatus.CANCELLED) {
				
				StringUtil.rejectIfInvalidString(cancelReason, "CancelReason");

				LocalDateTime cancelDateTime = LocalDateTime.now();
				
				orderProductDAO.updateOrderStatusAndCancelDate(status, orderId, menuId, categoryId, cancelDateTime,
						cancelReason);

			} else {

				orderProductDAO.updateOrderStatusAndCancelDate(status, orderId, menuId, categoryId, null, null);
			}

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Order updation failed");
		}

	}

	/**
	 * Update the caterer's approval status and reason for a specific order product.
	 *
	 * @param caterApproval The new caterer approval status (e.g., APPROVED, REJECTED, or any other status).
	 * @param reason The reason for approving or rejecting the order, required when caterApproval is REJECTED.
	 * @param orderId The ID of the order product.
	 * @param menuId The ID of the menu associated with the order product.
	 * @param categoryId The ID of the category associated with the order product.
	 * @throws ValidationException If any of the provided parameters are invalid.
	 * @throws ServiceException If a service error occurs during the update process.
	 */
	public void updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(CaterApproval caterApproval, String reason,
			int orderId, int menuId, int categoryId) throws ValidationException, ServiceException {

		try {
			OrderProductValidator.validateUpdateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval,orderId, menuId, categoryId);
			
			if(caterApproval == CaterApproval.REJECTED) {
				StringUtil.rejectIfInvalidString(reason, "RejectReason");
			}
			
			orderProductDAO.updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(caterApproval, reason, orderId, menuId,
					categoryId);
			
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("CaterApproval updation failed");
		}
	}

	/**
	 * Get a set of all order products associated with a specific order.
	 *
	 * @param orderId The ID of the order for which to retrieve order products.
	 * @return A set of order products associated with the given order.
	 * @throws ValidationException If the provided order ID is invalid.
	 * @throws ServiceException If a service error occurs while retrieving order products.
	 */
	public Set<OrderProduct> getAllOrderProductsByOrderId(int orderId) throws ValidationException, ServiceException {

		Set<OrderProduct> orderProductList = new HashSet<>();
		try {
			OrderValidator.isOrderIdIsValid(orderId);
			orderProductList = orderProductDAO.findAllOrderProductsByOrderId(orderId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return orderProductList;

	}

	/**
	 * Get the order product associated with a specific order, menu, and category.
	 *
	 * @param orderId The ID of the order to which the order product belongs.
	 * @param menuId The ID of the menu associated with the order product.
	 * @param categoryId The ID of the category associated with the order product.
	 * @return The order product associated with the provided order, menu, and category.
	 * @throws ValidationException If any of the provided parameters are invalid.
	 * @throws ServiceException If a service error occurs while retrieving the order product.
	 */
	public OrderProduct getOrderProductByOrderIdAndMenuIdAndCategoryId(int orderId, int menuId, int categoryId)
			throws ValidationException, ServiceException {

		OrderProduct orderProduct = null;

		try {
			OrderProductValidator.validateOrderProductUpdate(orderId, menuId, categoryId);

			orderProduct = orderProductDAO.findOrderProductByOrderIdAndMenuIdAndCategoryId(orderId, menuId, categoryId);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return orderProduct;
	}

	/**
	 * Get a set of ordered menu IDs and category IDs associated with a specific order.
	 *
	 * @param orderId The ID of the order for which to retrieve menu and category information.
	 * @return A set of key-value pairs where keys are menu IDs and values are category IDs.
	 * @throws ValidationException If the provided order ID is invalid.
	 * @throws ServiceException If a service error occurs while retrieving menu and category information.
	 */
	public Set<Map.Entry<Integer, Integer>> getOrderedMenuIdAndCategoryIdByOrderId(int orderId)
			throws ValidationException, ServiceException {

		Set<Map.Entry<Integer, Integer>> menuIdCategoryIdMap = new HashSet<>();

		OrderValidator.isOrderIdIsValid(orderId);

		try {
			menuIdCategoryIdMap = orderProductDAO.findOrderedMenuIdAndCategoryIdByOrderId(orderId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return menuIdCategoryIdMap;
	}
	
	
	/**
	 * Get a list of all order IDs.
	 *
	 * @return A list of all available order IDs.
	 * @throws ServiceException If a service error occurs while retrieving order IDs.
	 */
	public List<Integer> getAllOrderIds() throws ServiceException{
		
		List<Integer> orderIds = new ArrayList<>();
		
		try {
			orderIds =orderProductDAO.findAllOrderIds();
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return orderIds;
	}
	
	
	
	/**
	 * Get a list of all order products across all orders.
	 *
	 * @return A list of all order products across all orders.
	 * @throws ServiceException If a service error occurs while retrieving order products.
	 * @throws ValidationException If a validation error occurs during data retrieval.
	 */
	public List<OrderProduct> getAllOrderProducts() throws ServiceException, ValidationException{
		
		List<OrderProduct> allOrderProducts = new ArrayList<>();
		
		List<Integer> orderIds = new ArrayList<>();
		
		orderIds = getAllOrderIds();

		
		Set<Map.Entry<Integer, Integer>> menuIdCategoryIdMap = new HashSet<>();
	
		for(int orderId : orderIds) {
			
			menuIdCategoryIdMap = getOrderedMenuIdAndCategoryIdByOrderId(orderId);
			
			for (Map.Entry<Integer, Integer> entry : menuIdCategoryIdMap) {
				
			    Integer menuId = entry.getKey();
			    Integer categorId = entry.getValue();
			    
			    OrderProduct orderProduct = getOrderProductByOrderIdAndMenuIdAndCategoryId(orderId, menuId, categorId);
			    
			    allOrderProducts.add(orderProduct);
			}
			
		}
		
		return allOrderProducts;
	}

}
