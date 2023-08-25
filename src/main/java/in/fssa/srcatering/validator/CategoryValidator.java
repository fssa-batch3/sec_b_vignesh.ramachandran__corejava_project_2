package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.CategoryDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;

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

}
