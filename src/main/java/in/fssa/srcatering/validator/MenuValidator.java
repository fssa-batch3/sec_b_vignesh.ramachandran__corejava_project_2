package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.MenuDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;

public class MenuValidator {

	/**
	 * Validates whether the provided Menu ID is valid.
	 *
	 * @param menu_id The Menu ID to validate.
	 * @throws ValidationException If the Menu ID is invalid or not found.
	 */
	public static void isMenuIdIsValid(int menu_id) throws ValidationException {

		try {

			MenuDAO menudao = new MenuDAO();

			IntUtil.rejectIfInvalidInt(menu_id, "MenuId");

			menudao.IsMenuIdIsValid(menu_id);

		} catch (DAOException e) {
			throw new ValidationException("MenuId not found");
		}

	}

}
