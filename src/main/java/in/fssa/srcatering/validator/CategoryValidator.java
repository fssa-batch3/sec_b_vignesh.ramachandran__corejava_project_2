package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.CategoryDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;

public class CategoryValidator {

	/**
     * Validates if the provided category ID is valid.
     *
     * @param category_id The category ID to validate.
     * @throws ValidationException If the category ID is invalid or not found.
     */
	public static void isCategoryIdIsValid(int category_id) throws ValidationException {

		try {
			CategoryDAO categorydao = new CategoryDAO();

			IntUtil.rejectIfInvalidInt(category_id, "CategoryId");
			categorydao.isCategoryIdIsValid(category_id);

		} catch (DAOException e) {
			throw new ValidationException("CategoryId not found");
		}
	}

}
