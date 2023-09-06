package in.fssa.srcatering.validator;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import in.fssa.srcatering.dao.CategoryDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Category;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.StringUtil;

public class CategoryValidator { 

	/**
	 * 
	 * @param category
	 * @throws ValidationException
	 */
	public static void validateCategory(Category category) throws ValidationException {
		
		if(category == null) { 
			throw new ValidationException("Invalid Category Input");
		}

		MenuValidator.isMenuIdIsValid(category.getMenu_id());
		
		StringUtil.rejectIfInvalidString(category.getImage(), "CategoryImage");
		MenuValidator.validateImage(category.getImage());

		isCategoryNameAlreadyExistsForThatMenu(category.getCategoryName(), category.getMenu_id());
	}

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
	 * 
	 * @param menuId
	 * @param categoryId
	 * @throws ValidationException
	 */
	public static void isCategoryIdExistsForThatMenu(int menuId,int categoryId) throws ValidationException {
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			
			IntUtil.rejectIfInvalidInt(menuId, "MenuId");
			IntUtil.rejectIfInvalidInt(categoryId, "CategoryId");
			
			categoryDAO.isCategoryIdExistsForThatMenu(menuId,categoryId);
		} catch (DAOException e) {
			throw new ValidationException("CategoryId not found");
		}
	}
	

	/**
	 * Checks if a category name already exists in the database.
	 *
	 * @param categoryName The category name to check for existence.
	 * @throws ValidationException If the provided category name is invalid or if it
	 *                             already exists.
	 */
	public static void isCategoryNameAlreadyExistsForThatMenu(String categoryName, int menu_id)
			throws ValidationException {

		CategoryDAO categoryDAO = new CategoryDAO();

		try {

			StringUtil.rejectIfInvalidString(categoryName, "CategoryName");
			StringUtil.rejectIfInvalidName(categoryName, "CategoryName");

			Set<String> categoryNames = categoryDAO.findAllCategoryNamesByMenuId(menu_id);

			if (categoryNames.contains(categoryName.trim().toLowerCase())) {
				throw new ValidationException("CategoryName already exists");
			}

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ValidationException("Failed to find CategoryNames");
		}
	}

	public static boolean isCategoryNameExistsInTheCategoryTable(String categoryName) throws ValidationException {
		CategoryDAO categoryDAO = new CategoryDAO();

		StringUtil.rejectIfInvalidString(categoryName, "CategoryName");
		StringUtil.rejectIfInvalidName(categoryName, "CategoryName");
		
		Set<String> categoryNames = new TreeSet<>();

		try {
			Set<Category> categoryList = categoryDAO.findAllCategories();
			
			for(Category category:categoryList) {
				 categoryNames.add(category.getCategoryName().trim().toLowerCase());
			}
			
			return categoryNames.contains(categoryName.trim().toLowerCase());
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ValidationException("Failed to find CategoryNames");
		}

	}

}
