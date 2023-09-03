package in.fssa.srcatering.validator;

import java.util.List;

import in.fssa.srcatering.dao.CategoryDishDAO;
import in.fssa.srcatering.dao.DishDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.service.CategoryDishService;
import in.fssa.srcatering.service.CategoryService;
import in.fssa.srcatering.service.DishPriceService;
import in.fssa.srcatering.service.DishService;
import in.fssa.srcatering.service.MenuService;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.StringUtil;

public class DishValidator {

	/**
     * Validates a Dish object.
     *
     * @param dish The Dish object to validate.
     * @throws ValidationException If the Dish object is invalid.
     */
	public static void validate(Dish dish) throws ValidationException {

		if (dish == null) {
			throw new ValidationException("Invalid Dish Input");
		}

		StringUtil.rejectIfInvalidString(dish.getDishName(), "Dish Name");
		StringUtil.rejectIfInvalidName(dish.getDishName(), "Dish Name");
		IntUtil.rejectIfInvalidInt(dish.getQuantity(), "Quantity");
		IntUtil.quantityCheck(dish.getQuantity(), "Quantity");
		IntUtil.priceCheck(dish.getDishPrice(), "Price");
		
		
		if(dish.getQuantityUnit().name().equals("NOS") && dish.getQuantity() > 5 ) {
			throw new ValidationException("Check Quantity and QuantityUnit");
		}
		
		if(dish.getQuantityUnit().name().equals("GRAMS") && dish.getQuantity() < 20){
			throw new ValidationException("Check Quantity and QuantityUnit");
		}

	}

	/**
     * Validates if Dish IDs, Menu ID, and Category ID are valid, and checks for duplicate Dish names.
     *
     * @param dish The Dish object to validate.
     * @throws ValidationException If the Dish IDs are invalid or duplicate Dish names are found.
     */
	public static void validateAllIdsAndDishName(Dish dish) throws ValidationException {

		try {
			
			MenuService menuService = new MenuService();
			menuService.isMenuIdIsValid(dish.getMenuId());

			CategoryValidator.isCategoryIdIsValid(dish.getCategoryId());
			

			CategoryDishDAO categoryDishDAO = new CategoryDishDAO();

			List<String> dishNames = categoryDishDAO.findDishNamesByMenuIdAndCategoryId(dish.getMenuId(),
					dish.getCategoryId());

			if (dishNames.contains(dish.getDishName().trim().toUpperCase())) {
				throw new ValidationException("Dish name already Exists");
			}

		} catch (DAOException e) {
			throw new ValidationException("Failed to Find Dish Name By MenuId & CategoryId");
		}

	}

	/**
     * Validates Dish IDs, Menu ID, and Category ID.
     *
     * @param menuId     The Menu ID.
     * @param categoryId The Category ID.
     * @param dishId     The Dish ID.
     * @throws ValidationException If any of the IDs are invalid.
     */
	public static void validateIds(int menuId, int categoryId, int dishId) throws ValidationException {

			IntUtil.rejectIfInvalidInt(menuId, "MenuId");
			IntUtil.rejectIfInvalidInt(categoryId, "CategoryId");
			IntUtil.rejectIfInvalidInt(dishId, "DishId");

			MenuService menuService = new MenuService();
			menuService.isMenuIdIsValid(menuId);

			CategoryValidator.isCategoryIdIsValid(categoryId);

			// validate menu_id , category_id, dish_id in category_dish table
			CategoryDishService categoryDishService = new CategoryDishService();
			categoryDishService.isCategoryIdIsValid(menuId, categoryId);
			categoryDishService.isDishIdIsValid(dishId);

			// validate dish_id in dish table
			isDishIdIsValid(dishId);

			// validate dish_id in dish_price table
			DishPriceService dishPriceService = new DishPriceService();
			dishPriceService.isDishIdIsValid(dishId);

	}

	/**
     * Validates if the provided dish ID is valid.
     *
     * @param dishId The dish ID to validate.
     * @throws ValidationException If the dish ID is invalid or not found.
     */
	public static void isDishIdIsValid(int dishId) throws ValidationException {

		try {
			DishDAO dishDAO = new DishDAO();

			IntUtil.rejectIfInvalidInt(dishId, "DishId");
			dishDAO.isDishIdIsValid(dishId);

		} catch (DAOException e) {
			throw new ValidationException("DishId not found");
		}

	}

}
