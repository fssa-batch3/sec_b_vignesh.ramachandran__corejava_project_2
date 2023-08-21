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
		
		UserService userservice = new UserService();
		
		MenuService menuservice = new MenuService();

		
		CategoryService categoryservice = new CategoryService();


		DishService dishservice = new DishService();
		

		CategoryDishService categorydishservice = new CategoryDishService();

		

	}

}
