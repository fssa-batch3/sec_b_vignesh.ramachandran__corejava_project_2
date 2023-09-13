package in.fssa.srcatering.service;

import in.fssa.srcatering.dao.OrderProductDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.OrderProduct;
import in.fssa.srcatering.validator.OrderProductValidator;
import in.fssa.srcatering.validator.OrderValidator;

public class OrderProductService {

	OrderProductDAO orderProductDAO = new OrderProductDAO();

	public void createOrderProduct(OrderProduct orderProduct) throws ValidationException, ServiceException {

		try {

			OrderProductValidator.validateOrderId(orderProduct.getOrderId());

			orderProductDAO.create(orderProduct);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param orderId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public OrderProduct getOrderProductsByOrderId(int orderId) throws ValidationException, ServiceException {

		OrderProduct orderProduct = null;
		try {
			OrderValidator.isOrderIdIsValid(orderId);
			orderProduct = orderProductDAO.findOrderProductsByOrderId(orderId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return orderProduct;
	}

}
