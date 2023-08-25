package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.dao.CategoryDishDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.validator.CategoryDishValidator;

public class CategoryDishService {

	private CategoryDishDAO categoryDishDAO = new CategoryDishDAO();

	/**
     * Validates if the provided menu ID is valid.
     *
     * @param menuId The menu ID to validate.
     * @throws ValidationException If the provided menu ID is not valid.
     */
	public void isMenuIdIsValid(int menuId) throws ValidationException {

		CategoryDishValidator.isMenuIdIsValid(menuId);

	}

	/**
     * Validates if the provided category ID is valid within a given menu.
     *
     * @param menuId     The menu ID containing the category.
     * @param categoryId The category ID to validate.
     * @throws ValidationException If the provided category ID is not valid.
     */
	public void isCategoryIdIsValid(int menuId, int categoryId) throws ValidationException {

		this.isMenuIdIsValid(menuId);

		CategoryDishValidator.isCategoryIdIsValid(menuId, categoryId);

	}

	 /**
     * Validates if the provided dish ID is valid.
     *
     * @param dishId The dish ID to validate.
     * @throws ValidationException If the provided dish ID is not valid.
     */
	public void isDishIdIsValid(int dishId) throws ValidationException {

		CategoryDishValidator.isDishIdIsValid(dishId);

	}

	/**
     * Retrieves a list of dish names based on the provided menu ID and category ID.
     *
     * @param menuId     The menu ID.
     * @param categoryId The category ID.
     * @return A list of dish names.
     * @throws ValidationException If the provided IDs are not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public List<String> findDishNameByMenuIdAndCategoryId(int menuId, int categoryId)
			throws ValidationException, ServiceException {

		List<String> dishNames = new ArrayList<>();
		
		try {

			dishNames = categoryDishDAO.findDishNameByMenuIdAndCategoryId(menuId, categoryId);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Find Dish Name By MenuId & CategoryId");
		}
		return dishNames;
	}
	
	/**
     * Retrieves a list of dish IDs based on the provided menu ID and category ID.
     *
     * @param menuId     The menu ID.
     * @param categoryId The category ID.
     * @return A list of dish IDs.
     * @throws ServiceException If there's an issue with the service operation.
     */
	public List<Integer> findDishIdByMenuIdAndCategoryId(int menuId, int categoryId) throws ServiceException{
		
		List<Integer> dishIds;
		try {
			dishIds = categoryDishDAO.findDishIdByMenuIdAndCategoryId(menuId, categoryId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return dishIds;
	}

	/**
     * Creates a new relationship between a menu, category, and dish.
     *
     * @param menuId     The menu ID.
     * @param categoryId The category ID.
     * @param dishId     The dish ID.
     * @throws ValidationException If the provided IDs are not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void createCategoryDish(int menuId, int categoryId, int dishId) throws ValidationException, ServiceException {

		try {
			CategoryDishValidator.validate(menuId, categoryId, dishId);
			categoryDishDAO.create(menuId, categoryId, dishId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Create Dish");
		}

	}

	/**
     * Deletes a relationship between a menu, category, and dish.
     *
     * @param menuId     The menu ID.
     * @param categoryId The category ID.
     * @param dishId     The dish ID.
     * @throws ValidationException If the provided IDs are not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void deleteCategoryDish(int menuId, int categoryId, int dishId) throws ValidationException, ServiceException {

		try {

			IntUtil.rejectIfInvalidInt(menuId, "Menu Id");
			IntUtil.rejectIfInvalidInt(categoryId, "Category Id");
			IntUtil.rejectIfInvalidInt(dishId, "Dish Id");

			categoryDishDAO.delete(menuId, categoryId, dishId);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Delete Dish");
		}
	}
	
	/**
     * Changes the status of a relationship between a menu, category, and dish.
     *
     * @param menuId     The menu ID.
     * @param categoryId The category ID.
     * @param dishId     The dish ID.
     * @throws ValidationException If the provided IDs are not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public void changeCategoryDishStatus(int menuId, int categoryId, int dishId) throws ValidationException, ServiceException {
		
		
		try {
			IntUtil.rejectIfInvalidInt(menuId, "Menu Id");
			IntUtil.rejectIfInvalidInt(categoryId, "Category Id");
			IntUtil.rejectIfInvalidInt(dishId, "Dish Id");
			
			categoryDishDAO.changeStatus(menuId, categoryId, dishId);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
			
		}
	}

}
