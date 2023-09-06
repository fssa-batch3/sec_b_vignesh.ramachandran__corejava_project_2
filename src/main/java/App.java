import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.service.CategoryService;
import in.fssa.srcatering.service.UserService;

public class App {

	public static void main(String[] args) {

		System.out.println("App ");
		CategoryService categoryService = new CategoryService();
		
		try {
			System.out.println(categoryService.getCategoryByMenuIdAndCategoryId(1, 1));
			
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
