package in.fssa.srcatering.service;

import java.time.LocalDateTime;

import in.fssa.srcatering.dao.DishPriceDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.validator.DishPriceValidator;
import in.fssa.srcatering.validator.DishValidator;

public class DishPriceService {

	CategoryDishService categorydishservice = new CategoryDishService();
	DishService dishservice = new DishService();
	DishPriceDAO dishpricedao = new DishPriceDAO();

	/**
     * Creates a new dish price entry.
     *
     * @param dish_id The ID of the dish for which the price is being created.
     * @param price   The price of the dish.
     * @throws ValidationException If the provided parameters are not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void create(int dish_id, int price) throws ValidationException, ServiceException {

		try {
			
			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(localDateTime);

			dishservice.isDishIdIsValid(dish_id);

			IntUtil.priceCheck(price, "Price");

			dishpricedao.create(dish_id, price,dateTime);

		} catch (DAOException e) {
			throw new ServiceException("Invalid DishId");
		}

	}

	/**
     * Updates the price of a dish.
     *
     * @param dish_id     The ID of the dish for which the price is being updated.
     * @param dish_price  The new price for the dish.
     * @throws ValidationException If the provided parameters are not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void update(int dish_id, int dish_price) throws ValidationException, ServiceException {
		
		try {
			IntUtil.rejectIfInvalidInt(dish_id, "Dish Id");
			IntUtil.priceCheck(dish_price, "Price");

			categorydishservice.isDishIdIsValid(dish_id);
			DishPriceValidator.isDishIdIsValid(dish_id);
			
			int price = dishpricedao.findPriceByDishId(dish_id);
			int price_id = dishpricedao.findPriceIdByDishId(dish_id);
			
			IntUtil.rejectIfInvalidInt(price_id, "PriceId");

			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(localDateTime);
			
			if (price != dish_price) {
				dishpricedao.update(price_id, dateTime);
				dishpricedao.create(dish_id, dish_price, dateTime);
			}
			
		} catch (DAOException e) {
			throw new ServiceException("Invalid DishId");
		}
	}
	
	/**
     * Retrieves the current price of a dish.
     *
     * @param dish_id The ID of the dish for which the price is being retrieved.
     * @return The current price of the dish.
     * @throws ValidationException If the provided dish ID is not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public int findPriceByDishId(int dish_id) throws ValidationException, ServiceException {
		int price = -1;
		try {
			IntUtil.rejectIfInvalidInt(dish_id, "DishId");
			 price = dishpricedao.findPriceByDishId(dish_id);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return price;
	}

	/**
     * Validates if the provided dish ID is valid.
     *
     * @param dish_id The dish ID to validate.
     * @throws ValidationException If the provided dish ID is not valid.
     */
	public void isDishIdIsValid(int dish_id) throws ValidationException {

		DishPriceValidator.isDishIdIsValid(dish_id);

	}

}
