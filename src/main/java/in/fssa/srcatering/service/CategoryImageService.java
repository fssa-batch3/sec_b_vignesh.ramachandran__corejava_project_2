package in.fssa.srcatering.service;

import in.fssa.srcatering.dao.CategoryImageDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.validator.CategoryValidator;
import in.fssa.srcatering.validator.MenuValidator;

public class CategoryImageService {

	CategoryImageDAO categoryImageDAO = new CategoryImageDAO();

	public void createCategoryImage(int menu_id, int category_id, String image)
			throws ValidationException, ServiceException {

		try {

			MenuValidator.isMenuIdIsValid(menu_id);
			CategoryValidator.isCategoryIdIsValid(category_id); 

			categoryImageDAO.createCategoryImage(menu_id, category_id, image);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
