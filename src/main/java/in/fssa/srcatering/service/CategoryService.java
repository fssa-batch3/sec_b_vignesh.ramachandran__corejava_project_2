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
	 * @param menu_id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Set<String> getAllCategoryNamesByMenuId(int menu_id) throws ValidationException, ServiceException {

		Set<String> categoryNames = new HashSet<>();

		try {
			MenuValidator.isMenuIdIsValid(menu_id);

			categoryNames = categoryDAO.findAllCategoryNamesByMenuId(menu_id);
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
	 * @param menu_id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Set<Category> getCategoriesByMenuId(int menu_id) throws ValidationException, ServiceException {

		MenuValidator.isMenuIdIsValid(menu_id);

		Set<Category> categoryList = new TreeSet<>();

		try {
			categoryList = categoryDAO.findCategoriesByMenuId(menu_id);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to get category");
		}

		return categoryList;
	}

	/**
	 * 
	 * @param menu_id
	 * @param category_id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public Category getCategoryByMenuIdAndCategoryId(int menu_id, int category_id)
			throws ValidationException, ServiceException {

		MenuValidator.isMenuIdIsValid(menu_id);
		CategoryValidator.isCategoryIdIsValid(category_id);

		Category category = null;

		try {
			category = categoryDAO.findCategoryByMenuIdAndCategoryId(menu_id, category_id);
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
				
				System.out.println("not created in categories");

				int id = categoryDAO.findCategoryIdByCategoryName(category.getCategoryName());

				CategoryImageService categoryImageService = new CategoryImageService();
				categoryImageService.createCategoryImage(category.getMenu_id(), id, category.getImage());

			} else {
				System.out.println("created in both tables");
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

		CategoryValidator.isCategoryIdIsValid(category.getId());

		StringUtil.rejectIfInvalidString(category.getImage(), "CategoryImage");
		MenuValidator.validateImage(category.getImage());

		CategoryImageDAO categoryImageDAO = new CategoryImageDAO();

		try {
			categoryImageDAO.updateCategoryImage(category.getMenu_id(), category.getId(), category.getImage());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Falied to create Category");
		}

	}

}
