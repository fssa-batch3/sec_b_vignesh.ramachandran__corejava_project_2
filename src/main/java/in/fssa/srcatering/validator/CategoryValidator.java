package in.fssa.srcatering.validator;

import in.fssa.srcatering.dao.CategoryDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;

public class CategoryValidator {

	public static void isCategoryIdIsValid(int category_id) throws ValidationException {

		try {
			CategoryDAO categorydao = new CategoryDAO();

			IntUtil.rejectIfInvalidInt(category_id, "Category Id");
			categorydao.isCategoryIdIsValid(category_id);

		} catch (DAOException e) {
			throw new ValidationException("Invalid CategoryId");
		}
	}

}
