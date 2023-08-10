package in.fssa.srcatering.service;

import in.fssa.srcatering.dao.DishDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.validator.CategoryDishValidator;
import in.fssa.srcatering.validator.DishValidator;

public class DishService {

	DishDAO dishdao = new DishDAO();

	CategoryDishService categorydishservice = new CategoryDishService();

	public void create(Dish dish) throws ValidationException, ServiceException {
		DishPriceService dishpriceservice = new DishPriceService();
		int generatedDishId = -1;

		DishValidator.Validate(dish);
		DishValidator.ValidateAllIdsAndDishName(dish);

		// dish
		generatedDishId = dishdao.create(dish.getDish_name().trim(), dish.getQuantity(), dish.getQuantity_unit());

		// dish price
		dishpriceservice.create(generatedDishId, dish.getDish_price());

		// category_dish
		categorydishservice.create(dish.getMenu_id(), dish.getCategory_id(), generatedDishId);

	}

	public void update(Dish dish) throws ValidationException, ServiceException {

		DishPriceService dishpriceservice = new DishPriceService();

		DishValidator.Validate(dish);
		DishValidator.ValidateIds(dish.getMenu_id(), dish.getCategory_id(), dish.getId());

		dishdao.update(dish);

		dishpriceservice.update(dish.getId(), dish.getDish_price());

	}

	public void delete(Dish dish) throws ValidationException, ServiceException {

		DishValidator.isDishIdIsValid(dish.getId());

		categorydishservice.isDishIdIsValid(dish.getId());

		categorydishservice.delete(dish.getMenu_id(), dish.getCategory_id(), dish.getId());

	}

	public void isDishIdIsValid(int dish_id) throws ValidationException {

		DishValidator.isDishIdIsValid(dish_id);

	}

	public void findByDishId(int dish_id) throws ValidationException, ServiceException {

		try {

			CategoryDishValidator.isDishIdIsValid(dish_id);
			DishValidator.isDishIdIsValid(dish_id);
			dishdao.findByDishId(dish_id);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

}
