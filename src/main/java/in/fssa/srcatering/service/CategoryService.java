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
import in.fssa.srcatering.validator.CategoryValidator;

public class CategoryService {

	CategoryDAO categoryDAO = new CategoryDAO();

	/**
     * Retrieves a list of all categories.
     *
     * @return A list of all categories.
     * @throws ServiceException If there's an issue with the service operation.
     */
	public List<Category> getAllCategories() throws ServiceException {
		
		List<Category> categoryList = new ArrayList<Category>();
		
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
     * @param category_id The ID of the category to retrieve.
     * @return The retrieved category.
     * @throws ValidationException If the provided category ID is not valid.
     * @throws ServiceException    If there's an issue with the service operation.
     */
	public Category findByIdCategoryId(int category_id) throws ValidationException, ServiceException {
		
		Category category = null;
		
		try {
			category = new Category();
			IntUtil.rejectIfInvalidInt(category_id, "CategoryId");
			this.isCategoryIdIsValid(category_id);
			
			category =  categoryDAO.findById(category_id);
			
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
