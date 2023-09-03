package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.CategoryDishDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.service.CategoryService;
import in.fssa.srcatering.service.DishService;
import in.fssa.srcatering.service.MenuService;
import in.fssa.srcatering.util.IntUtil;

public class CategoryDishValidator {

	/**
	 * Validates menu, category, and dish IDs.
	 *
	 * @param menuId     The menu ID to validate.
	 * @param categoryId The category ID to validate.
	 * @param dishId     The dish ID to validate.
	 * @throws ValidationException If any of the IDs are invalid.
	 */
	public static void validate(int menuId, int categoryId, int dishId) throws ValidationException {

		IntUtil.rejectIfInvalidInt(menuId, "MenuId");
		IntUtil.rejectIfInvalidInt(categoryId, "CategoryId");
		IntUtil.rejectIfInvalidInt(dishId, "DishId");

		MenuService menuService = new MenuService();
		menuService.isMenuIdIsValid(menuId);

		CategoryValidator.isCategoryIdIsValid(categoryId);

		DishValidator.isDishIdIsValid(dishId);

	}

	/**
	 * Validates if the provided menu ID is valid.
	 *
	 * @param menuId The menu ID to validate.
	 * @throws ValidationException If the menu ID is invalid.
	 */
	public static void isMenuIdIsValid(int menuId) throws ValidationException {

		try {
			CategoryDishDAO categoryDishDAO = new CategoryDishDAO();

			IntUtil.rejectIfInvalidInt(menuId, "MenuId");
			categoryDishDAO.isMenuIdIsValid(menuId);

		} catch (DAOException e) {
			throw new ValidationException("Invalid MenuId");
		}

	}

	/**
	 * Validates if the provided category ID is valid within the context of the
	 * given menu ID.
	 *
	 * @param menuId     The menu ID to validate against.
	 * @param categoryId The category ID to validate.
	 * @throws ValidationException If the category ID is invalid.
	 */
	public static void isCategoryIdIsValid(int menuId, int categoryId) throws ValidationException {

		try {

			CategoryDishDAO categoryDishDAO = new CategoryDishDAO();

			IntUtil.rejectIfInvalidInt(categoryId, "CategoryId");
			categoryDishDAO.isCategoryIdIsValid(menuId, categoryId);

		} catch (DAOException e) {
			throw new ValidationException("Invalid CategoryId");
		}

	}

	/**
	 * Validates if the provided dish ID is valid.
	 *
	 * @param dishId The dish ID to validate.
	 * @throws ValidationException If the dish ID is invalid.
	 */
	public static void isDishIdIsValid(int dishId) throws ValidationException {

		try {

			CategoryDishDAO categoryDishDAO = new CategoryDishDAO();

			IntUtil.rejectIfInvalidInt(dishId, "DishId");
			categoryDishDAO.isDishIdIsValid(dishId);

		} catch (DAOException e) {
			throw new ValidationException("Invalid DishId");
		}

	}
}
