package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.OrderDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;

public class OrderProductValidator {

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
	
	

}
