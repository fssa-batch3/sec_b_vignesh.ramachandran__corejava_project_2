import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.model.QuantityUnit;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.service.CategoryService;
import in.fssa.srcatering.service.DishService;
import in.fssa.srcatering.service.MenuService;
import in.fssa.srcatering.service.UserService;

public class App {

	public static void main(String[] args) {
		UserService userservice = new UserService();
		
//		try {
//			userservice.findByEmail("vignesh@gmail.com");
//			
//		} catch (ValidationException e) {
//			
//			e.printStackTrace();
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		MenuService menuservice = new MenuService();
//		menuservice.getAll();
//		try {
//			menuservice.findById(1);
//		} catch (ValidationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		CategoryService categoryservice = new CategoryService();
//		categoryservice.getAll();
//		try {
//			categoryservice.findById(1);
//		} catch (ValidationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		DishService dishservice = new DishService();
		
//		Dish dish = new Dish();
//		dish.setDish_name("SAMBAR");
//		dish.setMenu_id(1);
//		dish.setCategory_id(1);
//		dish.setQuantity(20);
//		dish.setQuantity_unit(QuantityUnit.grams);
//		dish.setDish_price(10);
//		dish.setId(6);
//		
//		try {
//			dishservice.delete(dish);
//		} catch (ValidationException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			
//		} catch (DAOException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
//		try {
//			dishservice.findByDishId(5);
//		} catch (ValidationException | DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
