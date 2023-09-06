package in.fssa.srcatering.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.srcatering.dao.MenuDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Menu;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.StringUtil;

public class MenuValidator {
	
	public static final String MENUNAME = "MenuName";

	/**
	 * Validates whether the provided Menu ID is valid.
	 *
	 * @param menuId The Menu ID to validate.
	 * @throws ValidationException If the Menu ID is invalid or not found.
	 */
	public static void isMenuIdIsValid(int menuId) throws ValidationException {

		try { 

			MenuDAO menuDAO = new MenuDAO();

			IntUtil.rejectIfInvalidInt(menuId, "MenuId");

			menuDAO.isMenuIdIsValid(menuId);

		} catch (DAOException e) {
			throw new ValidationException("MenuId not found");
		}

	}

	/**
	 * Validates a Menu object by checking its attributes for validity.
	 *
	 * @param menu The Menu object to be validated.
	 * @throws ValidationException If any attribute of the menu is found to be invalid.
	 */
	public static void validateMenu(Menu menu) throws ValidationException {
		
		if(menu == null) {
			throw new ValidationException("Invalid Menu Input");
		}

		StringUtil.rejectIfInvalidString(menu.getMenuName(), MENUNAME);
		StringUtil.rejectIfInvalidName(menu.getMenuName(), MENUNAME);
		StringUtil.rejectIfInvalidString(menu.getDescription(), "Description");
		StringUtil.rejectIfInvalidString(menu.getImage(), "Image");

	}

	/**
	 * Validates an image URL by checking if it matches the expected URL pattern.
	 *
	 * @param image The image URL to be validated.
	 * @throws ValidationException If the image URL format is invalid.
	 */
	public static void validateImage(String image) throws ValidationException {

		String urlRegex = "https?://.+";

		Pattern urlPattern = Pattern.compile(urlRegex);

		Matcher urlMatcher = urlPattern.matcher(image);

		if (!urlMatcher.matches()) {
			throw new ValidationException("Image should be URL.eg:http, https");
		}
	}

	/**
	 * Checks if a menu name already exists in the database.
	 *
	 * @param menuName The menu name to check for existence.
	 * @throws ValidationException If the provided menu name is invalid or if it already exists.
	 */
	public static void isMenuNameAlreadyExists(String menuName) throws ValidationException {

		try {

			MenuDAO menuDAO = new MenuDAO();

			StringUtil.rejectIfInvalidString(menuName, MENUNAME);
			StringUtil.rejectIfInvalidName(menuName, MENUNAME);

			List<String> menuNames;

			menuNames = menuDAO.findAllMenuNames();

			if (menuNames.contains(menuName.trim().toLowerCase())) {
				throw new ValidationException("MenuName already exists");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ValidationException("Failed to find MenuNames");
		}

	}

}
