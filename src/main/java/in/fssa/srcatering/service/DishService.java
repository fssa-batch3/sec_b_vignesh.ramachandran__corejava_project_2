package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.dao.CategoryDishDAO;
import in.fssa.srcatering.dao.DishDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.validator.CategoryDishValidator;
import in.fssa.srcatering.validator.CategoryValidator;
import in.fssa.srcatering.validator.DishValidator;
import in.fssa.srcatering.validator.MenuValidator;

public class DishService {

	DishDAO dishDAO = new DishDAO();

	CategoryDishService categoryDishService = new CategoryDishService();

	/**
     * Creates a new dish.
     *
     * @param dish The dish object containing dish details.
     * @throws ValidationException If the provided dish data is not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void createDish(Dish dish) throws ValidationException, ServiceException {

		try {

			DishPriceService dishPriceService = new DishPriceService();
			int generatedDishId = -1;

			DishValidator.validate(dish);
			DishValidator.validateAllIdsAndDishName(dish);

			// dish
			generatedDishId = dishDAO.create(dish.getDishName().trim().toUpperCase(), dish.getQuantity(), dish.getQuantityUnit());

			// dish price
			dishPriceService.createDishPrice(generatedDishId, dish.getDishPrice());

			// category_dish
			categoryDishService.createCategoryDish(dish.getMenuId(), dish.getCategoryId(), generatedDishId);

		} catch (DAOException e) {
			throw new ServiceException("Dish creation failed");

		}

	}

	/**
     * Updates an existing dish.
     *
     * @param dish The updated dish object.
     * @throws ValidationException If the provided dish data is not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void updateDish(Dish dish) throws ValidationException, ServiceException {

		try {

			DishPriceService dishPriceService = new DishPriceService();

			DishValidator.validate(dish);
			DishValidator.validateIds(dish.getMenuId(), dish.getCategoryId(), dish.getId());

			dishDAO.update(dish);

			dishPriceService.updateDishPrice(dish.getId(), dish.getDishPrice());

		} catch (DAOException e) {
			throw new ServiceException("Dish Updation failed");
		}

	}

	 /**
     * Deletes a dish by its IDs.
     *
     * @param menuId     The menu ID of the dish.
     * @param categoryId The category ID of the dish.
     * @param dishId     The dish ID.
     * @throws ValidationException If the provided IDs are not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void deleteDish(int menuId, int categoryId, int dishId) throws ValidationException, ServiceException {

		DishValidator.isDishIdIsValid(dishId);

		categoryDishService.isDishIdIsValid(dishId);

		categoryDishService.deleteCategoryDish(menuId, categoryId, dishId);

	}

	/**
     * Validates if the provided dish ID is valid.
     *
     * @param dishId The dish ID to validate.
     * @throws ValidationException If the provided dish ID is not valid.
     */
	public void isDishIdIsValid(int dishId) throws ValidationException {

		DishValidator.isDishIdIsValid(dishId);

	}
	
	 /**
     * Finds a dish by its ID.
     *
     * @param dishId The dish ID to search for.
     * @return The dish object.
     * @throws ValidationException If the provided dish ID is not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public Dish findByDishId(int dishId) throws ValidationException, ServiceException {

		Dish dish = new Dish();
		try {

			CategoryDishValidator.isDishIdIsValid(dishId);
			DishValidator.isDishIdIsValid(dishId);
			dish = dishDAO.findByDishId(dishId);
			
			DishPriceService dishPriceService = new DishPriceService();
			dish.setDishPrice(dishPriceService.findPriceByDishId(dishId));
			

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return dish;
	}

	/**
     * Retrieves all dishes by a given menu ID and category ID.
     *
     * @param menuId     The menu ID.
     * @param categoryId The category ID.
     * @return A list of dishes matching the given menu and category.
     * @throws ValidationException If the provided menu or category ID is not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public List<Dish> findAllDishesByMenuIdAndCategoryId(int menuId, int categoryId) throws ValidationException, ServiceException {

		CategoryDishDAO categoryDishDAO = new CategoryDishDAO();
		
		
		List<Dish> dishes = new ArrayList<>();
		
		try {
			
			MenuValidator.isMenuIdIsValid(menuId);
			CategoryValidator.isCategoryIdIsValid(categoryId);
			
			List<Integer> dishIds = categoryDishDAO.findDishIdByMenuIdAndCategoryId(menuId, categoryId);

			for(int id: dishIds) {
				
				dishes.add(this.findByDishId(id));
			}	 
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return dishes;
	}
	
	 /**
     * Retrieves all dishes.
     *
     * @return A list of all dishes.
     * @throws ServiceException If there's an issue with the service operation.
     */
	public List<Dish> getAllDishes() throws ServiceException{
		List<Dish> dishes;
		try {
			dishes = dishDAO.findAllDishes();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return dishes;
	}

}
