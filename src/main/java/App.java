import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.model.QuantityUnit;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.service.CategoryDishService;
import in.fssa.srcatering.service.CategoryService;
import in.fssa.srcatering.service.DishService;
import in.fssa.srcatering.service.MenuService;
import in.fssa.srcatering.service.UserService;

public class App {

	public static void main(String[] args) {
		
		UserService userService = new UserService();
		try {
			userService.findByUserId(1);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MenuService menuService = new MenuService();

		
		CategoryService categoryService = new CategoryService();


		DishService dishService = new DishService();
		

		CategoryDishService categoryDishService = new CategoryDishService();

		

	}

}
