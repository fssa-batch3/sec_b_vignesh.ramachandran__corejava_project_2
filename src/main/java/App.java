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
//		List<Dish> dishes = new ArrayList<Dish>();
//		
//		Dish dish = new Dish();
//		dish.setDish_name("VADA");
//		dish.setMenu_id(1);
//		dish.setCategory_id(1);
//		dish.setQuantity(1);
//		dish.setQuantity_unit(QuantityUnit.NOS);
//		dish.setDish_price(10);
//		
//		try {
//			dishservice.create(dish);
//		} catch (ValidationException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			
//		} catch (ServiceException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}

		CategoryDishService categorydishservice = new CategoryDishService();
		try {
			categorydishservice.changeStatus(1, 1, 4);
		} catch (ValidationException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			System.out.println(categorydishservice.findDishNameByMenuIdAndCategoryId(1,1));
//			
//		} catch (ValidationException e) {
//			
//			e.printStackTrace();
//		} catch (ServiceException e) {
//			
//			e.printStackTrace();
//		}
		

	}

}
