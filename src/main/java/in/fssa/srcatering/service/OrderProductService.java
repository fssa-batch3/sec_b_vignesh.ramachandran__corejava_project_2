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
	 * 
	 * @param status
	 * @param orderId
	 * @param menuId
	 * @param categoryId
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param caterApproval
	 * @param reason
	 * @param orderId
	 * @param menuId
	 * @param categoryId
	 * @throws ValidationException
	 * @throws ServiceException 
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
			Logger.error(e);
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
			Logger.error(e);
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
	 * 
	 * @return
	 * @throws ServiceException
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
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
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
