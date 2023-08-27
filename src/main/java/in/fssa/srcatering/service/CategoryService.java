package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import in.fssa.srcatering.dao.CategoryDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Category;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.StringUtil;
import in.fssa.srcatering.validator.CategoryValidator;

public class CategoryService {

	CategoryDAO categoryDAO = new CategoryDAO();
	
	/**
	 * Creates a new category in the database using the provided Category object,
	 * after performing validation checks on the category name.
	 *
	 * @param category The Category object representing the category to be created.
	 * @throws ValidationException If the provided category name is invalid or already exists.
	 */
	public void createCategory(Category category) throws ValidationException {
		
		try {
			StringUtil.rejectIfInvalidString(category.getCategoryName(), "CategoryName");
			StringUtil.rejectIfInvalidName(category.getCategoryName(), "CategoryName");
			CategoryValidator.isCategoryNameAlreadyExists(category.getCategoryName());
			categoryDAO.createCategory(category);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
     * Retrieves a list of all categories.
     *
     * @return A list of all categories.
     * @throws ServiceException If there's an issue with the service operation.
     */
	public List<Category> getAllCategories() throws ServiceException {
		
		List<Category> categoryList = new ArrayList<>();
		
		try {
			
			categoryList = categoryDAO.findAll();

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
     * Retrieves a category by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return The retrieved category.
     * @throws ValidationException If the provided category ID is not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public Category findByIdCategoryId(int categoryId) throws ValidationException, ServiceException {
		
		Category category = null;
		
		try {
			
			IntUtil.rejectIfInvalidInt(categoryId, "CategoryId");
			this.isCategoryIdIsValid(categoryId);
			
			category =  categoryDAO.findById(categoryId);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Get Category");
		}
		return category;
	}
	
	/**
     * Validates if the provided category ID is valid.
     *
     * @param categoryId The category ID to validate.
     * @throws ValidationException If the provided category ID is not valid.
     */
	public void isCategoryIdIsValid(int categoryId) throws ValidationException {
		
		CategoryValidator.isCategoryIdIsValid(categoryId);
	}

}
