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

	public void isMenuIdIsValid(int menu_id) throws ValidationException {

		CategoryDishValidator.isMenuIdIsValid(menu_id);

	}

	public void isCategoryIdIsValid(int menu_id, int category_id) throws ValidationException {

		this.isMenuIdIsValid(menu_id);

		CategoryDishValidator.isCategoryIdIsValid(menu_id, category_id);

	}

	public void isDishIdIsValid(int dish_id) throws ValidationException {

		CategoryDishValidator.isDishIdIsValid(dish_id);

	}

	public List<String> findDishNameByMenuIdAndCategoryId(int menu_id, int category_id)
			throws ValidationException, ServiceException {

		List<String> dishNames = new ArrayList<String>();
		
		try {

			this.isCategoryIdIsValid(menu_id, category_id);

			dishNames = categorydishdao.findDishNameByMenuIdAndCategoryId(menu_id, category_id);

		} catch (DAOException e) {
			throw new ServiceException("Failed to Find Dish Name By MenuId & CategoryId");
		}
		return dishNames;

	}

	public void create(int menu_id, int category_id, int dish_id) throws ValidationException, ServiceException {

		try {
			CategoryDishValidator.Validate(menu_id, category_id, dish_id);
			categorydishdao.create(menu_id, category_id, dish_id);
		} catch (DAOException e) {
			throw new ServiceException("Failed to Create Dish");
		}

	}

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

}
