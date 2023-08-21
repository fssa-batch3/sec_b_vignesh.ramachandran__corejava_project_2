package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.dao.CategoryDishDAO;
import in.fssa.srcatering.dao.DishDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.validator.CategoryDishValidator;

public class CategoryDishService {

	private CategoryDishDAO categorydishdao = new CategoryDishDAO();
	private DishDAO dishdao = new DishDAO();

	/**
     * Validates if the provided menu ID is valid.
     *
     * @param menu_id The menu ID to validate.
     * @throws ValidationException If the provided menu ID is not valid.
     */
	public void isMenuIdIsValid(int menu_id) throws ValidationException {

		CategoryDishValidator.isMenuIdIsValid(menu_id);

	}

	/**
     * Validates if the provided category ID is valid within a given menu.
     *
     * @param menu_id     The menu ID containing the category.
     * @param category_id The category ID to validate.
     * @throws ValidationException If the provided category ID is not valid.
     */
	public void isCategoryIdIsValid(int menu_id, int category_id) throws ValidationException {

		this.isMenuIdIsValid(menu_id);

		CategoryDishValidator.isCategoryIdIsValid(menu_id, category_id);

	}

	 /**
     * Validates if the provided dish ID is valid.
     *
     * @param dish_id The dish ID to validate.
     * @throws ValidationException If the provided dish ID is not valid.
     */
	public void isDishIdIsValid(int dish_id) throws ValidationException {

		CategoryDishValidator.isDishIdIsValid(dish_id);

	}

	/**
     * Retrieves a list of dish names based on the provided menu ID and category ID.
     *
     * @param menu_id     The menu ID.
     * @param category_id The category ID.
     * @return A list of dish names.
     * @throws ValidationException If the provided IDs are not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public List<String> findDishNameByMenuIdAndCategoryId(int menu_id, int category_id)
			throws ValidationException, ServiceException {

		List<String> dishNames = new ArrayList<String>();
		
		try {

			//this.isCategoryIdIsValid(menu_id, category_id);

			dishNames = categorydishdao.findDishNameByMenuIdAndCategoryId(menu_id, category_id);

		} catch (DAOException e) {
			throw new ServiceException("Failed to Find Dish Name By MenuId & CategoryId");
		}
		return dishNames;
	}
	
	/**
     * Retrieves a list of dish IDs based on the provided menu ID and category ID.
     *
     * @param menu_id     The menu ID.
     * @param category_id The category ID.
     * @return A list of dish IDs.
     * @throws ServiceException If there's an issue with the service operation.
     */
	public List<Integer> findDishIdByMenuIdAndCategoryId(int menu_id, int category_id) throws ServiceException{
		
		List<Integer> dishIds;
		try {
			dishIds = categorydishdao.findDishIdByMenuIdAndCategoryId(menu_id, category_id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return dishIds;
	}

	/**
     * Creates a new relationship between a menu, category, and dish.
     *
     * @param menu_id     The menu ID.
     * @param category_id The category ID.
     * @param dish_id     The dish ID.
     * @throws ValidationException If the provided IDs are not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void create(int menu_id, int category_id, int dish_id) throws ValidationException, ServiceException {

		try {
			CategoryDishValidator.Validate(menu_id, category_id, dish_id);
			categorydishdao.create(menu_id, category_id, dish_id);
		} catch (DAOException e) {
			throw new ServiceException("Failed to Create Dish");
		}

	}

	/**
     * Deletes a relationship between a menu, category, and dish.
     *
     * @param menu_id     The menu ID.
     * @param category_id The category ID.
     * @param dish_id     The dish ID.
     * @throws ValidationException If the provided IDs are not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void delete(int menu_id, int category_id, int dish_id) throws ValidationException, ServiceException {

		try {

			IntUtil.rejectIfInvalidInt(menu_id, "Menu Id");
			IntUtil.rejectIfInvalidInt(category_id, "Category Id");
			IntUtil.rejectIfInvalidInt(dish_id, "Dish Id");

			categorydishdao.delete(menu_id, category_id, dish_id);

		} catch (DAOException e) {
			throw new ServiceException("Failed to Delete Dish");
		}
	}
	
	/**
     * Changes the status of a relationship between a menu, category, and dish.
     *
     * @param menu_id     The menu ID.
     * @param category_id The category ID.
     * @param dish_id     The dish ID.
     * @throws ValidationException If the provided IDs are not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void changeStatus(int menu_id, int category_id, int dish_id) throws ValidationException, ServiceException {
		
		
		try {
			IntUtil.rejectIfInvalidInt(menu_id, "Menu Id");
			IntUtil.rejectIfInvalidInt(category_id, "Category Id");
			IntUtil.rejectIfInvalidInt(dish_id, "Dish Id");
			
			categorydishdao.changeStatus(menu_id, category_id, dish_id);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
			
		}
	}

}
