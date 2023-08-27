package in.fssa.srcatering.validator;

import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.dao.CategoryDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.StringUtil;

public class CategoryValidator {

	/**
     * Validates if the provided category ID is valid.
     *
     * @param categoryId The category ID to validate.
     * @throws ValidationException If the category ID is invalid or not found.
     */
	public static void isCategoryIdIsValid(int categoryId) throws ValidationException {

		try {
			CategoryDAO categoryDAO = new CategoryDAO();

			IntUtil.rejectIfInvalidInt(categoryId, "CategoryId");
			categoryDAO.isCategoryIdIsValid(categoryId);

		} catch (DAOException e) {
			throw new ValidationException("CategoryId not found");
		}
	}
	
	/**
	 * Checks if a category name already exists in the database.
	 *
	 * @param categoryName The category name to check for existence.
	 * @throws ValidationException If the provided category name is invalid or if it already exists.
	 */
	public static void isCategoryNameAlreadyExists(String categoryName) throws ValidationException {
		
		CategoryDAO categoryDAO = new CategoryDAO();
		
		List<String> categoryNames = new ArrayList<>();
		
		try {
			
			StringUtil.rejectIfInvalidString(categoryName, "CategoryName");
			StringUtil.rejectIfInvalidName(categoryName, "CategoryName");
			
			categoryNames = categoryDAO.findAllCategoryNames();
			
			if(categoryNames.contains(categoryName.trim().toLowerCase())) {
				throw new ValidationException("CategoryName already exists");
			}
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ValidationException("Failed to find CategoryNames");
		}
	}

}
