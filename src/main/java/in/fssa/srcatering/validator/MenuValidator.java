package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.MenuDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;

public class MenuValidator {

	/**
	 * 
	 * @param menu_id
	 * @throws ValidationException
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
