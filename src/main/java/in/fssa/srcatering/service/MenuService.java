package in.fssa.srcatering.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import in.fssa.srcatering.dao.MenuDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Menu;
import in.fssa.srcatering.util.Logger;
import in.fssa.srcatering.validator.MenuValidator;

public class MenuService {

	MenuDAO menuDAO = new MenuDAO();

	/**
	 * Retrieves a list of all menus.
	 *
	 * @return A list of all menus.
	 * @throws ServiceException If there's an issue with the service operation.
	 */
	public Set<Menu> getAllActiveMenus() throws ServiceException {

		Set<Menu> menuList = new TreeSet<>();
		try {

			menuList = menuDAO.findAllActiveMenus();
 
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return menuList;
	}
	
	/**
	 * Retrieve a set of all available menus.
	 * 
	 * @return A Set of Menu objects representing all available menus.
	 * @throws ServiceException
	 */
	public Set<Menu> getAllMenus() throws ServiceException{
		Set<Menu> menuList = new TreeSet<>();
		
		try {
			menuList = menuDAO.findAllMenus();
			
		}  catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return menuList;
	}
	
	

	/**
	 * Creates a new menu in the database using the provided Menu object,
	 * after performing validation checks on the menu and its image.
	 *
	 * @param menu The Menu object representing the menu to be created.
	 * @throws ValidationException If the provided menu or its image is invalid or if the menu name already exists.
	 * @throws ServiceException If a service-level error occurs during the creation.
	 */
	public void createMenu(Menu menu) throws ValidationException, ServiceException {

		try {
			MenuValidator.validateMenu(menu);
			MenuValidator.validateImage(menu.getImage());
			MenuValidator.isMenuNameAlreadyExists(menu.getMenuName());
			menuDAO.create(menu);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * Updates an existing menu in the database using the provided Menu object,
	 * after performing validation checks on the menu and its image.
	 *
	 * @param menu The Menu object representing the updated menu information.
	 * @throws ValidationException If the provided menu or its image is invalid or if the menu ID is invalid.
	 * @throws ServiceException If a service-level error occurs during the update.
	 */
	public void updateMenu(Menu menu) throws ValidationException, ServiceException {
		
		try {
			MenuValidator.validateMenu(menu);
			MenuValidator.validateImage(menu.getImage());
			this.isMenuIdIsValid(menu.getId());
			menuDAO.update(menu);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Finds a menu by its ID.
	 *
	 * @param menuId The menu ID to search for.
	 * @return The menu object.
	 * @throws ValidationException If the provided menu ID is not valid.
	 * @throws ServiceException    If there's an issue with the service operation.
	 */
	public Menu findByMenuId(int menuId) throws ValidationException, ServiceException {

		Menu menu = null;
		try {

			MenuValidator.isMenuIdIsValid(menuId);

			menu = menuDAO.findById(menuId);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return menu;

	}

	/**
	 * Validates if the provided menu ID is valid.
	 *
	 * @param menuId The menu ID to validate.
	 * @throws ValidationException If the provided menu ID is not valid.
	 */
	public void isMenuIdIsValid(int menuId) throws ValidationException {

		MenuValidator.isMenuIdIsValid(menuId);
	}
	
	/**
	 * Retrieves a list of all menu names from the database.
	 * 
	 * @return A List of String objects containing all menu names.
	 * @throws ServiceException
	 */
	public List<String> getAllMenuNames() throws ServiceException {
		
		List<String> menuNames;
		try {
			menuNames = menuDAO.findAllMenuNames();
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return menuNames;
	}
	

}
