package in.fssa.srcatering.service;

import in.fssa.srcatering.dao.CategoryImageDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.Logger;
import in.fssa.srcatering.validator.CategoryValidator;
import in.fssa.srcatering.validator.MenuValidator;

public class CategoryImageService {

	CategoryImageDAO categoryImageDAO = new CategoryImageDAO();

	/**
	 * Create a category image for a specific menu and category.
	 *
	 * @param menu_id The ID of the menu to associate the image with.
	 * @param category_id The ID of the category within the menu to associate the image with.
	 * @param image The image content or URL to be associated with the category.
	 * @throws ValidationException If the provided menu ID or category ID is invalid.
	 * @throws ServiceException If a service error occurs during the image creation process.
	 */
	public void createCategoryImage(int menu_id, int category_id, String image)
			throws ValidationException, ServiceException {

		try {

			MenuValidator.isMenuIdIsValid(menu_id);
			CategoryValidator.isCategoryIdIsValid(category_id); 

			categoryImageDAO.createCategoryImage(menu_id, category_id, image);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
 
	}

}
