package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import in.fssa.srcatering.dao.CategoryDAO;
import in.fssa.srcatering.dao.CategoryImageDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Category;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.StringUtil;
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

			Iterator<Category> iterator = categoryList.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to GetAll Category");
		}
		return categoryList;
	}
	
	/**
	 * 
	 * @param menuId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Set<Category> getAllActiveCategoriesByMenuId(int menuId) throws ValidationException, ServiceException{
		
		Set<Category> categoryList = new TreeSet<>();
		
		try {
			MenuValidator.isMenuIdIsValid(menuId);
			
			categoryList = categoryDAO.findAllActiveCategoriesByMenuId(menuId);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Get categories");
		}
		return categoryList;
	}

	/**
	 * 
	 * @param menuId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Set<String> getAllCategoryNamesByMenuId(int menuId) throws ValidationException, ServiceException {

		Set<String> categoryNames = new HashSet<>();

		try {
			MenuValidator.isMenuIdIsValid(menuId);

			categoryNames = categoryDAO.findAllCategoryNamesByMenuId(menuId);
			Iterator<String> iterator = categoryNames.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to GetAll Category Names by menuId");
		}
		return categoryNames;
	}

	/**
	 * 
	 * @param menuId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Set<Category> getCategoriesByMenuId(int menuId) throws ValidationException, ServiceException {

		MenuValidator.isMenuIdIsValid(menuId);

		Set<Category> categoryList = new TreeSet<>();

		try {
			categoryList = categoryDAO.findCategoriesByMenuId(menuId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to get category");
		}

		return categoryList;
	}

	/**
	 * 
	 * @param menuId
	 * @param categoryId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Category getCategoryByMenuIdAndCategoryId(int menuId, int categoryId)
			throws ValidationException, ServiceException {

		MenuValidator.isMenuIdIsValid(menuId);
		CategoryValidator.isCategoryIdIsValid(categoryId);

		Category category = null;

		try {
			category = categoryDAO.findCategoryByMenuIdAndCategoryId(menuId, categoryId);
		} catch (DAOException e) {
			e.printStackTrace();
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
				categoryImageService.createCategoryImage(category.getMenu_id(), id, category.getImage());

			} else { 
				
				int generatedCategoryId = -1;
				// category
				generatedCategoryId = categoryDAO.createCategory(category);

				CategoryImageService categoryImageService = new CategoryImageService();
				categoryImageService.createCategoryImage(category.getMenu_id(), generatedCategoryId,
						category.getImage());
			}

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Falied to create Category");
		}
	}

	/**
	 * 
	 * @param category
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void updateCategory(Category category) throws ValidationException, ServiceException {

		StringUtil.rejectIfInvalidString(category.getCategoryName(), "CategoryName");
		StringUtil.rejectIfInvalidName(category.getCategoryName(), "CategoryName");
 
		MenuValidator.isMenuIdIsValid(category.getMenu_id());

		CategoryValidator.isCategoryIdExistsForThatMenu(category.getMenu_id(), category.getId());

		StringUtil.rejectIfInvalidString(category.getImage(), "CategoryImage");
		MenuValidator.validateImage(category.getImage());

		CategoryImageDAO categoryImageDAO = new CategoryImageDAO();

		try {
			categoryImageDAO.updateCategoryImage(category.getMenu_id(), category.getId(), category.getImage());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to create Category");
		}

	}

}
