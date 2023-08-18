package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.dao.CategoryDishDAO;
import in.fssa.srcatering.dao.DishDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.validator.CategoryDishValidator;

public class CategoryDishService {

	CategoryDishDAO categorydishdao = new CategoryDishDAO();
	DishDAO dishdao = new DishDAO();

	/**
	 * 
	 * @param menu_id
	 * @throws ValidationException
	 */
	public void isMenuIdIsValid(int menu_id) throws ValidationException {

		CategoryDishValidator.isMenuIdIsValid(menu_id);

	}

	/**
	 * 
	 * @param menu_id
	 * @param category_id
	 * @throws ValidationException
	 */
	public void isCategoryIdIsValid(int menu_id, int category_id) throws ValidationException {

		this.isMenuIdIsValid(menu_id);

		CategoryDishValidator.isCategoryIdIsValid(menu_id, category_id);

	}

	/**
	 * 
	 * @param dish_id
	 * @throws ValidationException
	 */
	public void isDishIdIsValid(int dish_id) throws ValidationException {

		CategoryDishValidator.isDishIdIsValid(dish_id);

	}

	/**
	 * 
	 * @param menu_id
	 * @param category_id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public List<String> findDishNameByMenuIdAndCategoryId(int menu_id, int category_id)
			throws ValidationException, ServiceException {

		List<String> dishNames = new ArrayList<String>();
		
		try {

			//this.isCategoryIdIsValid(menu_id, category_id);

			dishNames = categorydishdao.findDishNameByMenuIdAndCategoryId(menu_id, category_id);

		} catch (DAOException e) {
			throw new ServiceException("Failed to Find Dish Name By MenuId & CategoryId");
		}
		return dishNames;
	}
	
	/**
	 * 
	 * @param menu_id
	 * @param category_id
	 * @return
	 * @throws ServiceException
	 */
	public List<Integer> findDishIdByMenuIdAndCategoryId(int menu_id, int category_id) throws ServiceException{
		
		List<Integer> dishIds;
		try {
			dishIds = categorydishdao.findDishIdByMenuIdAndCategoryId(menu_id, category_id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return dishIds;
	}

	/**
	 * 
	 * @param menu_id
	 * @param category_id
	 * @param dish_id
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void create(int menu_id, int category_id, int dish_id) throws ValidationException, ServiceException {

		try {
			CategoryDishValidator.Validate(menu_id, category_id, dish_id);
			categorydishdao.create(menu_id, category_id, dish_id);
		} catch (DAOException e) {
			throw new ServiceException("Failed to Create Dish");
		}

	}

	/**
	 * 
	 * @param menu_id
	 * @param category_id
	 * @param dish_id
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void delete(int menu_id, int category_id, int dish_id) throws ValidationException, ServiceException {

		try {

			IntUtil.rejectIfInvalidInt(menu_id, "Menu Id");
			IntUtil.rejectIfInvalidInt(category_id, "Category Id");
			IntUtil.rejectIfInvalidInt(dish_id, "Dish Id");

			categorydishdao.delete(menu_id, category_id, dish_id);

		} catch (DAOException e) {
			throw new ServiceException("Failed to Delete Dish");
		}
	}
	
	public void changeStatus(int menu_id, int category_id, int dish_id) throws ValidationException, ServiceException {
		
		
		try {
			IntUtil.rejectIfInvalidInt(menu_id, "Menu Id");
			IntUtil.rejectIfInvalidInt(category_id, "Category Id");
			IntUtil.rejectIfInvalidInt(dish_id, "Dish Id");
			
			categorydishdao.changeStatus(menu_id, category_id, dish_id);
			
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
			
		}
	}

}
