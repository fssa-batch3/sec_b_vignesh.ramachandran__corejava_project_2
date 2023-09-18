package in.fssa.srcatering.validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import in.fssa.srcatering.dao.OrderDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Order;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.StringUtil;

public class OrderValidator {

	/**
	 * 
	 * @param order
	 * @throws ValidationException
	 */
	public static void validateOrder(Order order) throws ValidationException {

		IntUtil.rejectIfInvalidInt(order.getUserId(), "UserId");
		IntUtil.rejectIfInvalidInt(order.getAddressId(), "AddressId");
		IntUtil.rejectIfInvalidInt(order.getTotalCost(), "TotalCost");
		StringUtil.rejectIfInvalidString(order.getEventName(), "EventName");

		UserValidator.isUserIdIsValid(order.getUserId());	
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
