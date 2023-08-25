package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.MenuDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;

public class MenuValidator {

	/**
	 * Validates whether the provided Menu ID is valid.
	 *
	 * @param menuId The Menu ID to validate.
	 * @throws ValidationException If the Menu ID is invalid or not found.
	 */
	public static void isMenuIdIsValid(int menuId) throws ValidationException {

		try {

			MenuDAO menuDAO = new MenuDAO();

			IntUtil.rejectIfInvalidInt(menuId, "MenuId");

			menuDAO.isMenuIdIsValid(menuId);

		} catch (DAOException e) {
			throw new ValidationException("MenuId not found");
		}

	}

}
