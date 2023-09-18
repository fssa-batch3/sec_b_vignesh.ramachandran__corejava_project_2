package in.fssa.srcatering.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import in.fssa.srcatering.dao.OrderDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.OrderProduct;
import in.fssa.srcatering.model.OrderStatus;
import in.fssa.srcatering.util.IntUtil;

public class OrderProductValidator {
	
	public static void validateOrderProduct(OrderProduct orderProduct) throws ValidationException {
		
		IntUtil.rejectIfInvalidInt(orderProduct.getMenuId(), "MenuId");
		IntUtil.rejectIfInvalidInt(orderProduct.getCategoryId(), "CategoryId");
		IntUtil.rejectIfInvalidInt(orderProduct.getNoOfGuest(), "NoOfGuest");
		
		
		LocalDate today = LocalDate.now();
		LocalDate deliveryDate = orderProduct.getDeliveryDate();
		LocalDate twoMonthsLater = today.plusMonths(2);
		

		// getting no of Days
		long daysDifference = ChronoUnit.DAYS.between(today, deliveryDate);
		long monthsDifference = ChronoUnit.MONTHS.between(today, twoMonthsLater);
		
		if (daysDifference < 7) {
			throw new ValidationException("The deliveryDate is more than one week from today");
		}
		
		if(monthsDifference > 60) {
			throw new ValidationException("Please ensure that the delivery date falls within a two-month period.");
		}

		if (orderProduct.getNoOfGuest() < 50 || orderProduct.getNoOfGuest() > 1500) {
			throw new ValidationException("NoOfGuest should be above 49 and less than 1501");
		}
		
		MenuValidator.isMenuIdIsValid(orderProduct.getMenuId());
		CategoryValidator.isCategoryIdExistsForThatMenu(orderProduct.getMenuId(), orderProduct.getCategoryId());
		
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
			e.printStackTrace();
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
	
	

}
