package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.CategoryDishDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.service.CategoryService;
import in.fssa.srcatering.service.DishService;
import in.fssa.srcatering.service.MenuService;
import in.fssa.srcatering.util.IntUtil;

public class CategoryDishValidator {

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

	public static void isMenuIdIsValid(int menu_id) throws ValidationException {

		try {
			CategoryDishDAO categorydishdao = new CategoryDishDAO();

			IntUtil.rejectIfInvalidInt(menu_id, "MenuId");
			categorydishdao.isMenuIdIsValid(menu_id);

		} catch (DAOException e) {
			throw new ValidationException("Invalid MenuId");
		}

	}

	public static void isCategoryIdIsValid(int menu_id, int category_id) throws ValidationException {

		try {

			CategoryDishDAO categorydishdao = new CategoryDishDAO();

			IntUtil.rejectIfInvalidInt(category_id, "CategoryId");
			categorydishdao.isCategoryIdIsValid(menu_id, category_id);

		} catch (DAOException e) {
			throw new ValidationException("Invalid CategoryId");
		}

	}

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
