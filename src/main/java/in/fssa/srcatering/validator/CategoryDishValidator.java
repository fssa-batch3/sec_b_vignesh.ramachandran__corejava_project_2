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
     * @param menu_id     The menu ID to validate.
     * @param category_id The category ID to validate.
     * @param dish_id     The dish ID to validate.
     * @throws ValidationException If any of the IDs are invalid.
     */
	public static void Validate(int menu_id, int category_id, int dish_id) throws ValidationException {

//		try {

			IntUtil.rejectIfInvalidInt(menu_id, "MenuId");
			IntUtil.rejectIfInvalidInt(category_id, "CategoryId");
			IntUtil.rejectIfInvalidInt(dish_id, "DishId");

			MenuService menuservice = new MenuService();
			menuservice.isMenuIdIsValid(menu_id);

			CategoryService categoryservice = new CategoryService();
			categoryservice.isCategoryIdIsValid(category_id);

			DishService dishservice = new DishService();
			dishservice.isDishIdIsValid(dish_id);

//		} catch (DAOException e) {
//			if (e.getMessage().contains("Invaid MenuId")) {
//				throw new ValidationException("Invalid MenuId");
//			}
//
//			else if (e.getMessage().contains("Invalid CategoryId")) {
//				throw new ValidationException("Invalid CategoryId");
//			}
//
//			else if (e.getMessage().contains("Invalid DishId")) {
//				throw new ValidationException("Invalid DishId");
//			}
//		}

	}

	/**
     * Validates if the provided menu ID is valid.
     *
     * @param menu_id The menu ID to validate.
     * @throws ValidationException If the menu ID is invalid.
     */
	public static void isMenuIdIsValid(int menu_id) throws ValidationException {

		try {
			CategoryDishDAO categorydishdao = new CategoryDishDAO();

			IntUtil.rejectIfInvalidInt(menu_id, "MenuId");
			categorydishdao.isMenuIdIsValid(menu_id);

		} catch (DAOException e) {
			throw new ValidationException("Invalid MenuId");
		}

	}

	/**
     * Validates if the provided category ID is valid within the context of the given menu ID.
     *
     * @param menu_id     The menu ID to validate against.
     * @param category_id The category ID to validate.
     * @throws ValidationException If the category ID is invalid.
     */
	public static void isCategoryIdIsValid(int menu_id, int category_id) throws ValidationException {

		try {

			CategoryDishDAO categorydishdao = new CategoryDishDAO();

			IntUtil.rejectIfInvalidInt(category_id, "CategoryId");
			categorydishdao.isCategoryIdIsValid(menu_id, category_id);

		} catch (DAOException e) {
			throw new ValidationException("Invalid CategoryId");
		}

	}

	/**
     * Validates if the provided dish ID is valid.
     *
     * @param dish_id The dish ID to validate.
     * @throws ValidationException If the dish ID is invalid.
     */
	public static void isDishIdIsValid(int dish_id) throws ValidationException {

		try {

			CategoryDishDAO categorydishdao = new CategoryDishDAO();

			IntUtil.rejectIfInvalidInt(dish_id, "DishId");
			categorydishdao.isDishIdIsValid(dish_id);

		} catch (DAOException e) {
			throw new ValidationException("Invalid DishId");
		}

	}
}
