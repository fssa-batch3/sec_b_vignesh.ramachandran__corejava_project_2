package in.fssa.srcatering.validator;

import java.util.List;

import in.fssa.srcatering.dao.DishDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
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
	public static void Validate(Dish dish) throws ValidationException {

		if (dish == null) {
			throw new ValidationException("Invalid Dish Input");
		}

		StringUtil.rejectIfInvalidString(dish.getDish_name(), "Dish Name");
		IntUtil.rejectIfInvalidInt(dish.getQuantity(), "Quantity");
		IntUtil.priceCheck(dish.getDish_price(), "Price");
		
		
		if(dish.getQuantity_unit().name().equals("NOS") && dish.getQuantity() > 5 ) {
			throw new ValidationException("Check Quantity and QuantityUnit");
		}
		
		if(dish.getQuantity_unit().name().equals("GRAMS") && dish.getQuantity() < 20){
			throw new ValidationException("Check Quantity and QuantityUnit");
		}

	}

	/**
     * Validates if Dish IDs, Menu ID, and Category ID are valid, and checks for duplicate Dish names.
     *
     * @param dish The Dish object to validate.
     * @throws ValidationException If the Dish IDs are invalid or duplicate Dish names are found.
     */
	public static void ValidateAllIdsAndDishName(Dish dish) throws ValidationException {

		try {
			
			MenuService menuservice = new MenuService();
			menuservice.isMenuIdIsValid(dish.getMenu_id());

			CategoryService categoryservice = new CategoryService();
			categoryservice.isCategoryIdIsValid(dish.getCategory_id());
			

			CategoryDishService categorydishservice = new CategoryDishService();

			List<String> dishNames = categorydishservice.findDishNameByMenuIdAndCategoryId(dish.getMenu_id(),
					dish.getCategory_id());

			if (dishNames.contains(dish.getDish_name().trim())) {
				throw new ValidationException("Dish name already Exists");
			}

		} catch (ServiceException e) {
			throw new ValidationException("Failed to Find Dish Name By MenuId & CategoryId");
		}

	}

	/**
     * Validates Dish IDs, Menu ID, and Category ID.
     *
     * @param menu_id     The Menu ID.
     * @param category_id The Category ID.
     * @param dish_id     The Dish ID.
     * @throws ValidationException If any of the IDs are invalid.
     */
	public static void ValidateIds(int menu_id, int category_id, int dish_id) throws ValidationException {

//		try {

			IntUtil.rejectIfInvalidInt(menu_id, "MenuId");
			IntUtil.rejectIfInvalidInt(category_id, "CategoryId");
			IntUtil.rejectIfInvalidInt(dish_id, "DishId");

			MenuService menuservice = new MenuService();
			menuservice.isMenuIdIsValid(menu_id);

			CategoryService categoryservice = new CategoryService();
			categoryservice.isCategoryIdIsValid(category_id);

			// validate menu_id , category_id, dish_id in category_dish table
			CategoryDishService categorydishservice = new CategoryDishService();
			categorydishservice.isCategoryIdIsValid(menu_id, category_id);
			categorydishservice.isDishIdIsValid(dish_id);

			// validate dish_id in dish table
			DishService dishservice = new DishService();
			dishservice.isDishIdIsValid(dish_id);

			// validate dish_id in dish_price table
			DishPriceService dishpriceservice = new DishPriceService();
			dishpriceservice.isDishIdIsValid(dish_id);

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
     * Validates if the provided dish ID is valid.
     *
     * @param dish_id The dish ID to validate.
     * @throws ValidationException If the dish ID is invalid or not found.
     */
	public static void isDishIdIsValid(int dish_id) throws ValidationException {

		try {
			DishDAO dishdao = new DishDAO();

			IntUtil.rejectIfInvalidInt(dish_id, "DishId");
			dishdao.isDishIdIsValid(dish_id);

		} catch (DAOException e) {
			throw new ValidationException("DishId not found");
		}

	}

}
