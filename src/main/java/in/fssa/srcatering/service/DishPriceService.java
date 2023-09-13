package in.fssa.srcatering.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.dao.DishPriceDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.validator.CategoryDishValidator;
import in.fssa.srcatering.validator.CategoryValidator;
import in.fssa.srcatering.validator.DishPriceValidator;
import in.fssa.srcatering.validator.DishValidator;
import in.fssa.srcatering.validator.MenuValidator;

public class DishPriceService {

	CategoryDishService categoryDishService = new CategoryDishService();
	DishService dishService = new DishService();
	DishPriceDAO dishPriceDAO = new DishPriceDAO();

	/**
	 * Creates a new dish price entry.
	 *
	 * @param dishId The ID of the dish for which the price is being created.
	 * @param price  The price of the dish.
	 * @throws ValidationException If the provided parameters are not valid.
	 * @throws ServiceException    If there's an issue with the service operation.
	 */
	public void createDishPrice(int dishId, int price) throws ValidationException, ServiceException {

		try {

			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(localDateTime);

			DishValidator.isDishIdIsValid(dishId);

			IntUtil.priceCheck(price, "Price");

			dishPriceDAO.create(dishId, price, dateTime);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Invalid DishId");
		}

	}

	/**
	 * Updates the price of a dish.
	 *
	 * @param dishId    The ID of the dish for which the price is being updated.
	 * @param dishPrice The new price for the dish.
	 * @throws ValidationException If the provided parameters are not valid.
	 * @throws ServiceException    If there's an issue with the service operation.
	 */
	public void updateDishPrice(int dishId, int dishPrice) throws ValidationException, ServiceException {

		try {
			IntUtil.rejectIfInvalidInt(dishId, "Dish Id");
			IntUtil.priceCheck(dishPrice, "Price");

			categoryDishService.isDishIdIsValid(dishId);
			DishPriceValidator.isDishIdIsValid(dishId);

			int price = dishPriceDAO.findPriceByDishId(dishId);

			int priceId = dishPriceDAO.findPriceIdByDishId(dishId);

			IntUtil.rejectIfInvalidInt(priceId, "PriceId");

			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(localDateTime);

			if (price != dishPrice) {
				System.out.println("price in if condition" + price);

				dishPriceDAO.update(priceId, dateTime);
				dishPriceDAO.create(dishId, dishPrice, dateTime);
			}

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Invalid DishId");
		}
	}

	/**
	 * Retrieves the current price of a dish.
	 *
	 * @param dishId The ID of the dish for which the price is being retrieved.
	 * @return The current price of the dish.
	 * @throws ValidationException If the provided dish ID is not valid.
	 * @throws ServiceException    If there's an issue with the service operation.
	 */
	public int findPriceByDishId(int dishId) throws ValidationException, ServiceException {
		int price = -1;
		try {
			IntUtil.rejectIfInvalidInt(dishId, "DishId");
			price = dishPriceDAO.findPriceByDishId(dishId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return price;
	}

	/**
	 * Validates if the provided dish ID is valid.
	 *
	 * @param dishId The dish ID to validate.
	 * @throws ValidationException If the provided dish ID is not valid.
	 */
	public void isDishIdIsValid(int dishId) throws ValidationException {

		DishPriceValidator.isDishIdIsValid(dishId);

	}

	/**
	 * 
	 * @param menuId
	 * @param categoryId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public List<Integer> getAllPriceIdsByMenuIdAndCategoryId(int menuId, int categoryId) throws ValidationException, ServiceException {

		List<Integer> priceIds = new ArrayList<>();
		try {
			MenuValidator.isMenuIdIsValid(menuId);
			CategoryDishValidator.isCategoryIdIsValid(menuId, categoryId);
			
			priceIds = dishPriceDAO.findAllPriceIdsByMenuIdAndCategoryId(menuId, categoryId);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return priceIds;
	}

}
