package in.fssa.srcatering.service;

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

	public void create(int dish_id, int price) throws ValidationException, ServiceException {

		try {

			dishservice.isDishIdIsValid(dish_id);

			IntUtil.priceCheck(price, "Price");

			dishpricedao.create(dish_id, price);

		} catch (DAOException e) {
			throw new ServiceException("Invalid DishId");
		}

	}

	public void update(int dish_id, int dish_price) throws ValidationException, ServiceException {
		
		try {
			IntUtil.rejectIfInvalidInt(dish_id, "Dish Id");
			IntUtil.priceCheck(dish_price, "Price");

			categorydishservice.isDishIdIsValid(dish_id);
			DishPriceValidator.isDishIdIsValid(dish_id);
			
			int price = dishpricedao.findPriceByDishId(dish_id);
//			System.out.println(price);
			
			if (price != dish_price) {
				dishpricedao.update(dish_id, dish_price);
				dishpricedao.create(dish_id, dish_price);
			}
			
		} catch (DAOException e) {
			throw new ServiceException("Invalid DishId");
		}

	}

	public void isDishIdIsValid(int dish_id) throws ValidationException {

		DishPriceValidator.isDishIdIsValid(dish_id);

	}

}
