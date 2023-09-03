import in.fssa.srcatering.dao.CategoryDAO;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.model.QuantityUnit;
import in.fssa.srcatering.service.CategoryService;
import in.fssa.srcatering.service.DishService;
import in.fssa.srcatering.validator.CategoryValidator;

public class App {

	public static void main(String[] args) {

		System.out.println("App ");
		
		DishService cs = new DishService();
		
		try {
			System.out.println(cs.getDishByDishId(2));
			
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
