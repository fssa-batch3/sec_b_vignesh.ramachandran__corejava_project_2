package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.dao.CategoryDishDAO;
import in.fssa.srcatering.dao.DishDAO;
import in.fssa.srcatering.dao.DishPriceDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.model.DishPrice;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.validator.CategoryDishValidator;
import in.fssa.srcatering.validator.CategoryValidator;
import in.fssa.srcatering.validator.DishValidator;
import in.fssa.srcatering.validator.MenuValidator;

public class DishService {

	DishDAO dishdao = new DishDAO();

	CategoryDishService categorydishservice = new CategoryDishService();

	/**
	 * 
	 * @param dish
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void create(Dish dish) throws ValidationException, ServiceException {

		try {

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

		} catch (DAOException e) {
			throw new ServiceException("Dish creation failed");

		}

	}

	/**
	 * 
	 * @param dish
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void update(Dish dish) throws ValidationException, ServiceException {

		try {

			DishPriceService dishpriceservice = new DishPriceService();

			DishValidator.Validate(dish);
			DishValidator.ValidateIds(dish.getMenu_id(), dish.getCategory_id(), dish.getId());

			dishdao.update(dish);

			dishpriceservice.update(dish.getId(), dish.getDish_price());

		} catch (DAOException e) {
			throw new ServiceException("Dish Updation failed");
		}

	}

	/**
	 * 
	 * @param menu_id
	 * @param category_id
	 * @param dish_id
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void delete(int menu_id, int category_id, int dish_id) throws ValidationException, ServiceException {

		DishValidator.isDishIdIsValid(dish_id);

		categorydishservice.isDishIdIsValid(dish_id);

		categorydishservice.delete(menu_id, category_id, dish_id);

	}

	/**
	 * 
	 * @param dish_id
	 * @throws ValidationException
	 */
	public void isDishIdIsValid(int dish_id) throws ValidationException {

		DishValidator.isDishIdIsValid(dish_id);

	}
	
	/**
	 * 
	 * @param dish_id
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void findByDishId(int dish_id) throws ValidationException, ServiceException {

		try {

			CategoryDishValidator.isDishIdIsValid(dish_id);
			DishValidator.isDishIdIsValid(dish_id);
			dishdao.findByDishId(dish_id);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param menu_id
	 * @param category_id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public List<Dish> findAllDishesByMenuIdAndCategoryId(int menu_id, int category_id) throws ValidationException, ServiceException {

		CategoryDishDAO categorydishdao = new CategoryDishDAO();
		DishPriceDAO dishpricedao = new DishPriceDAO();
		
		List<Dish> dishes = new ArrayList<Dish>();
		
		try {
			
			MenuValidator.isMenuIdIsValid(menu_id);
			CategoryValidator.isCategoryIdIsValid(category_id);
			
			List<Integer> dishIds = categorydishdao.findDishIdByMenuIdAndCategoryId(menu_id, category_id);

			for(int id: dishIds) {
				
				dishes.add(dishdao.findByDishId(id));
			}	 
			
			for(Dish dish: dishes) {
				
				int price_1 = dishpricedao.findPriceByDishId(dish.getId());
				
				dish.setDish_price(price_1);
				dish.setMenu_id(menu_id);
				dish.setCategory_id(category_id);
			}
			
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return dishes;
	}
	
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Dish> getAllDishes() throws ServiceException{
		List<Dish> dishes;
		try {
			dishes = dishdao.findAllDishes();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return dishes;
	}

}
