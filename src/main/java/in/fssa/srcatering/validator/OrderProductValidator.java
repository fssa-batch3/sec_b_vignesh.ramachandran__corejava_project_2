package in.fssa.srcatering.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import in.fssa.srcatering.dao.OrderDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.CaterApproval;
import in.fssa.srcatering.model.OrderProduct;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.Logger;

public class OrderProductValidator {
	
	public static void validateOrderProduct(OrderProduct orderProduct) throws ValidationException {
		
		if (orderProduct == null) {
			throw new ValidationException("Invalid OrderProduct Input");
		}
		
		IntUtil.rejectIfInvalidInt(orderProduct.getMenuId(), "MenuId");
		IntUtil.rejectIfInvalidInt(orderProduct.getCategoryId(), "CategoryId");
		IntUtil.rejectIfInvalidInt(orderProduct.getNoOfGuest(), "NoOfGuest");
		IntUtil.rejectIfInvalidInt(orderProduct.getOrderId(), "OrderId");
		
		LocalDate today = LocalDate.now();
		LocalDate deliveryDate = orderProduct.getDeliveryDate();
		LocalDate twoMonthsLater = today.plusMonths(2);
		

		// getting no of Days
		long daysDifference = ChronoUnit.DAYS.between(today, deliveryDate);
		long monthsDifference = ChronoUnit.DAYS.between(today, twoMonthsLater);
		
		if (daysDifference < 7) {
			throw new ValidationException(
					"Delivery date cannot be less than one week from today");
		}

		if (daysDifference > monthsDifference) {
			throw new ValidationException("Delivery date cannot be more than two months from today");
		}

		if (orderProduct.getNoOfGuest() < 50 || orderProduct.getNoOfGuest() > 1500) {
			throw new ValidationException("NoOfGuest should be above 49 and less than 1501");
		}
		
		MenuValidator.isMenuIdIsValid(orderProduct.getMenuId());
		CategoryValidator.isCategoryIdExistsForThatMenu(orderProduct.getMenuId(), orderProduct.getCategoryId());
		OrderValidator.isOrderIdIsValid(orderProduct.getOrderId());
	}
	
	

	/**
	 * 
	 * @param orderId
	 * @throws ValidationException
	 */
	public static void validateOrderId(int orderId) throws ValidationException {

		try {
			IntUtil.rejectIfInvalidInt(orderId, "OrderId");

			OrderDAO orderDAO = new OrderDAO();
			orderDAO.isOrderIdIsValid(orderId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ValidationException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param orderId
	 * @param menuId
	 * @param categoryId
	 * @throws ValidationException
	 */
	public static void validateOrderProductUpdate(int orderId, int menuId, int categoryId) throws ValidationException {
		
		OrderValidator.isOrderIdIsValid(orderId);
		MenuValidator.isMenuIdIsValid(menuId);
		CategoryValidator.isCategoryIdExistsForThatMenu(menuId, categoryId);
		
	}
	
	
	/**
	 * 
	 * @param caterApproval
	 * @param orderId
	 * @param menuId
	 * @param categoryId
	 * @throws ValidationException
	 */
	public static void validateUpdateCaterApprovalByOrderIdAndMenuIdAndCategoryId(CaterApproval caterApproval, int orderId, int menuId, int categoryId) throws ValidationException {
		
		OrderValidator.isOrderIdIsValid(orderId);
		MenuValidator.isMenuIdIsValid(menuId);
		CategoryValidator.isCategoryIdExistsForThatMenu(menuId, categoryId);
	}
	
	

}
