package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import in.fssa.srcatering.dao.CategoryDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Category;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.validator.CategoryValidator;

public class CategoryService {

	CategoryDAO categorydao = new CategoryDAO();

	/**
     * Retrieves a list of all categories.
     *
     * @return A list of all categories.
     * @throws ServiceException If there's an issue with the service operation.
     */
	public List<Category> getAll() throws ServiceException {
		
		List<Category> categoryList = new ArrayList<Category>();
		
		try {
			
			categoryList = categorydao.findAll();

			Iterator<Category> iterator = categoryList.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
			
		} catch (DAOException e) {
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
	public Category findById(int category_id) throws ValidationException, ServiceException {
		
		Category category = null;
		
		try {
			category = new Category();
			IntUtil.rejectIfInvalidInt(category_id, "CategoryId");
			this.isCategoryIdIsValid(category_id);
			
			category =  categorydao.findById(category_id);
			
		} catch (DAOException e) {
			throw new ServiceException("Failed to Get Category");
		}
		return category;
	}
	
	/**
     * Validates if the provided category ID is valid.
     *
     * @param category_id The category ID to validate.
     * @throws ValidationException If the provided category ID is not valid.
     */
	public void isCategoryIdIsValid(int category_id) throws ValidationException {
		
		CategoryValidator.isCategoryIdIsValid(category_id);
	}

}
