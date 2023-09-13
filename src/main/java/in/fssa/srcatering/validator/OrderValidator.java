package in.fssa.srcatering.validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import in.fssa.srcatering.dao.OrderDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Order;
import in.fssa.srcatering.util.IntUtil;

public class OrderValidator {

	/**
	 * 
	 * @param order
	 * @throws ValidationException
	 */
	public static void validateOrder(Order order) throws ValidationException {

		IntUtil.rejectIfInvalidInt(order.getUserId(), "UserId");
		IntUtil.rejectIfInvalidInt(order.getMenuId(), "MenuId");
		IntUtil.rejectIfInvalidInt(order.getCategoryId(), "CategoryId");
		IntUtil.rejectIfInvalidInt(order.getNoOfGuest(), "NoOfGuest");

		LocalDate today = LocalDate.now();
		LocalDate deliveryDate = order.getDeliveryDate(); 

		long daysDifference = ChronoUnit.DAYS.between(today, deliveryDate);

		if (daysDifference < 7) {
			throw new ValidationException("The date is more than one week from today");
		}

		if (order.getNoOfGuest() < 50 || order.getNoOfGuest() > 500) {
			throw new ValidationException("NoOfGuest should be above 49 and less than 501");
		}

		UserValidator.isUserIdIsValid(order.getUserId());
		MenuValidator.isMenuIdIsValid(order.getMenuId());
		CategoryValidator.isCategoryIdExistsForThatMenu(order.getMenuId(), order.getCategoryId());
	}
	
	/**
	 * 
	 * @param orderId
	 * @throws ValidationException
	 */
	public static void isOrderIdIsValid(int orderId) throws ValidationException {
		
		try {
			IntUtil.rejectIfInvalidInt(orderId, "OrderId");
			OrderDAO orderDAO = new OrderDAO();
			orderDAO.isOrderIdIsValid(orderId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}

}
