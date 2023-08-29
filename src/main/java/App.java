import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.service.UserService;

public class App {

	public static void main(String[] args) {

		System.out.println("App ");
		UserService user = new UserService();
		try {
			user.findByEmail("abc@gmail.com");
		} catch (ValidationException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
