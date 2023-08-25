package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.DishPriceDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;

public class DishPriceValidator {

	/**
     * Validates if the provided dish ID is valid.
     *
     * @param dishId The dish ID to validate.
     * @throws ValidationException If the dish ID is invalid or not found.
     */
	public static void isDishIdIsValid(int dishId) throws ValidationException {

		try {

			IntUtil.rejectIfInvalidInt(dishId, "Dish Id");
			DishPriceDAO dishPriceDAO = new DishPriceDAO();

			dishPriceDAO.iDishIdIsValid(dishId);

		} catch (DAOException e) {
			throw new ValidationException("Invalid DishId");
		}

	}

}
