package in.fssa.srcatering.service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import in.fssa.srcatering.dao.CategoryDAO;
import in.fssa.srcatering.dao.CategoryImageDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Category;
import in.fssa.srcatering.util.Logger;
import in.fssa.srcatering.util.StringUtil;
import in.fssa.srcatering.validator.CategoryDishValidator;
import in.fssa.srcatering.validator.CategoryValidator;
import in.fssa.srcatering.validator.MenuValidator;

public class CategoryService {

	CategoryDAO categoryDAO = new CategoryDAO();

	/**
	 * Retrieves a list of all categories.
	 *
	 * @return A list of all categories.
	 * @throws ServiceException If there's an issue with the service operation.
	 */
	public Set<Category> getAllCategories() throws ServiceException {

		Set<Category> categoryList = new HashSet<>();

		try {

			categoryList = categoryDAO.findAllCategories();

		} catch (DAOException e) { 
			Logger.error(e);
			throw new ServiceException("Failed to GetAll Category");
		}
		return categoryList;
	}

	/**
	 * Retrieve all active categories within a menu by their IDs.
	 *
	 * @param menuId The ID of the menu containing the categories.
	 * @return A set of Category objects representing the active categories in the menu.
	 * @throws ValidationException If the provided menu ID is invalid.
	 * @throws ServiceException If a service error occurs during the retrieval process.
	 */
	public Set<Category> getAllActiveCategoriesByMenuId(int menuId) throws ValidationException, ServiceException {

		Set<Category> categoryList = new TreeSet<>();

		try {
			MenuValidator.isMenuIdIsValid(menuId);

			categoryList = categoryDAO.findAllActiveCategoriesByMenuId(menuId);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Failed to Get categories");
		}
		return categoryList;
	}

	/**
	 * Retrieve the names of all categories within a menu by their IDs.
	 *
	 * @param menuId The ID of the menu containing the categories.
	 * @return A set of category names within the menu.
	 * @throws ValidationException If the provided menu ID is invalid.
	 * @throws ServiceException If a service error occurs during the retrieval process.
	 */
	public Set<String> getAllCategoryNamesByMenuId(int menuId) throws ValidationException, ServiceException {

		Set<String> categoryNames = new HashSet<>();

		try {
			MenuValidator.isMenuIdIsValid(menuId);

			categoryNames = categoryDAO.findAllCategoryNamesByMenuId(menuId);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Failed to GetAll Category Names by menuId");
		}
		return categoryNames;
	}

	/**
	 * Retrieve all categories within a menu by their IDs.
	 *
	 * @param menuId The ID of the menu containing the categories.
	 * @return A set of Category objects representing all categories in the menu.
	 * @throws ValidationException If the provided menu ID is invalid.
	 * @throws ServiceException If a service error occurs during the retrieval process.
	 */
	public Set<Category> getCategoriesByMenuId(int menuId) throws ValidationException, ServiceException {

		MenuValidator.isMenuIdIsValid(menuId);

		Set<Category> categoryList = new TreeSet<>();

		try {
			categoryList = categoryDAO.findCategoriesByMenuId(menuId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Failed to get category");
		}

		return categoryList;
	}

	/**
	 * Retrieve a specific category within a menu by its ID.
	 *
	 * @param menuId The ID of the menu containing the category.
	 * @param categoryId The ID of the category to retrieve.
	 * @return The Category object representing the requested category.
	 * @throws ValidationException If the provided menu ID or category ID is invalid.
	 * @throws ServiceException If a service error occurs during the retrieval process.
	 */
	public Category getCategoryByMenuIdAndCategoryId(int menuId, int categoryId)
			throws ValidationException, ServiceException {

		MenuValidator.isMenuIdIsValid(menuId);
		CategoryValidator.isCategoryIdIsValid(categoryId);

		Category category = null;

		try {
			category = categoryDAO.findCategoryByMenuIdAndCategoryId(menuId, categoryId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Failed to Category");
		}
		return category;
	}

	/**
	 * Creates a new category in the database using the provided Category object,
	 * after performing validation checks on the category name.
	 *
	 * @param category The Category object representing the category to be created.
	 * @throws ValidationException If the provided category name is invalid or
	 *                             already exists.
	 * @throws ServiceException
	 */
	public void createCategory(Category category) throws ValidationException, ServiceException {

		try {

			CategoryValidator.validateCategory(category);

			if (CategoryValidator.isCategoryNameExistsInTheCategoryTable(category.getCategoryName())) {

				int id = categoryDAO.findCategoryIdByCategoryName(category.getCategoryName());

				CategoryImageService categoryImageService = new CategoryImageService();
				categoryImageService.createCategoryImage(category.getMenuId(), id, category.getImage());

			} else {

				int generatedCategoryId = -1;
				// category
				generatedCategoryId = categoryDAO.createCategory(category);

				CategoryImageService categoryImageService = new CategoryImageService();
				categoryImageService.createCategoryImage(category.getMenuId(), generatedCategoryId,
						category.getImage());
			}

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Falied to create Category");
		}
	}

	/**
	 * Update a category with new information.
	 *
	 * @param category The Category object containing the updated information.
	 * @throws ValidationException If the category name, menu ID, or category image is invalid,
	 *                           or if the category doesn't exist for that menu.
	 * @throws ServiceException If a service error occurs during the update process.
	 */
	public void updateCategory(Category category) throws ValidationException, ServiceException {

		StringUtil.rejectIfInvalidString(category.getCategoryName(), "CategoryName");
		StringUtil.rejectIfInvalidName(category.getCategoryName(), "CategoryName");

		MenuValidator.isMenuIdIsValid(category.getMenuId());

		CategoryValidator.isCategoryIdExistsForThatMenu(category.getMenuId(), category.getId());

		StringUtil.rejectIfInvalidString(category.getImage(), "CategoryImage");
		MenuValidator.validateImage(category.getImage());

		CategoryImageDAO categoryImageDAO = new CategoryImageDAO();

		try {
			categoryImageDAO.updateCategoryImage(category.getMenuId(), category.getId(), category.getImage());
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Failed to create Category");
		}

	}

	/**
	 * Get the total price of a category by its menu ID and category ID.
	 *
	 * @param menuId The ID of the menu containing the category.
	 * @param categoryId The ID of the category to retrieve the total price for.
	 * @return The total price of the category.
	 * @throws ValidationException If the provided menu ID or category ID is invalid.
	 * @throws ServiceException If a service error occurs during the retrieval process.
	 */
	public int getTotalPriceOfTheCategoryByMenuIdAndCategoryId(int menuId, int categoryId)
			throws ValidationException, ServiceException {

		int totalPrice;
		try {
			CategoryDishValidator.isCategoryIdIsValid(menuId, categoryId);

			totalPrice = categoryDAO.getTotalPriceOfTheCategoryByMenuIdAndCategoryId(menuId, categoryId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Failed to get category total price");
		}
		return totalPrice;
	}
	
	/**
	 * Check if a category ID exists for the specified menu.
	 *
	 * @param menuId The ID of the menu to check within.
	 * @param categoryId The ID of the category to check for existence.
	 * @throws ValidationException If the provided menu ID or category ID is invalid.
	 * @throws ServiceException If a service error occurs during the check.
	 */
	public void isCategoryIdExistsForThatMenu(int menuId, int categoryId) throws ValidationException, ServiceException {
		
		try {
			CategoryDishValidator.isCategoryIdIsValid(menuId, categoryId);
			categoryDAO.isCategoryIdExistsForThatMenu(menuId, categoryId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		
	}
}
