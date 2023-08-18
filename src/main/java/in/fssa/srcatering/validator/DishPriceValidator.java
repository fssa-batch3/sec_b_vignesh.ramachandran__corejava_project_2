package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.DishPriceDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.service.DishService;
import in.fssa.srcatering.util.IntUtil;

public class DishPriceValidator {

	/**
	 * 
	 * @param dish_id
	 * @throws ValidationException
	 */
	public static void isDishIdIsValid(int dish_id) throws ValidationException {

		try {

			IntUtil.rejectIfInvalidInt(dish_id, "Dish Id");
			DishPriceDAO dishpricedao = new DishPriceDAO();

			dishpricedao.iDishIdIsValid(dish_id);

		} catch (DAOException e) {
			throw new ValidationException("Invalid DishId");
		}

	}

}
