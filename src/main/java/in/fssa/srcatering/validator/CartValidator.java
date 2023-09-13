package in.fssa.srcatering.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import in.fssa.srcatering.dao.CartDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Cart;
import in.fssa.srcatering.util.IntUtil;

public class CartValidator {

	/**
	 * 
	 * @param cart
	 * @throws ValidationException
	 */
	public static void validateCart(Cart cart) throws ValidationException {
		
		if(cart == null) {
			throw new ValidationException("Invalid Cart Input");
		}

		IntUtil.rejectIfInvalidInt(cart.getUserId(), "UserId");
		IntUtil.rejectIfInvalidInt(cart.getMenuId(), "MenuId");
		IntUtil.rejectIfInvalidInt(cart.getCategoryId(), "CategoryId"); 
		IntUtil.rejectIfInvalidInt(cart.getNoOfGuest(), "NoOfGuest");
		//IntUtil.rejectIfInvalidInt(cart.getTotalCost(), "TotalCost");

		LocalDate today = LocalDate.now();
		LocalDate deliveryDate = cart.getDeliveryDate(); 

		// Period period = Period.between(today, addtoCart.getDeliveryDate());
		long daysDifference = ChronoUnit.DAYS.between(today, deliveryDate);

		if (daysDifference < 7) {
			throw new ValidationException("The date is more than one week from today");
		}

		if (cart.getNoOfGuest() < 50 || cart.getNoOfGuest() > 500) {
			throw new ValidationException("NoOfGuest should be above 49 and less than 501");
		}

		UserValidator.isUserIdIsValid(cart.getUserId());
		MenuValidator.isMenuIdIsValid(cart.getMenuId());
		CategoryValidator.isCategoryIdExistsForThatMenu(cart.getMenuId(), cart.getCategoryId());
	}

	/**
	 * 
	 * @param cartId
	 * @return
	 * @throws ValidationException
	 */
	public static boolean isCartIdIsValid(int cartId) throws ValidationException {

		try {
			IntUtil.rejectIfInvalidInt(cartId, "CartId");

			CartDAO cartDAO = new CartDAO();
			if (cartDAO.isCartIdIsValid(cartId)) {
				return true;
			}

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
		return false;
	}

	public static void isThatMenuAndCategoryAlreadyExistsForThatUser(int menuId, int categoryId, int userId)
			throws ValidationException {

		try {
			MenuValidator.isMenuIdIsValid(menuId);
			CategoryValidator.isCategoryIdExistsForThatMenu(menuId, categoryId);
			UserValidator.isUserIdIsValid(userId);

			CartDAO cartDAO = new CartDAO();
			cartDAO.isThatMenuAndCategoryAlreadyExists(menuId, categoryId, userId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}

}
