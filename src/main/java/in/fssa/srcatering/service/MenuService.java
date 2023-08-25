package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.fssa.srcatering.dao.MenuDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Menu;
import in.fssa.srcatering.validator.MenuValidator;

public class MenuService {

	MenuDAO menuDAO = new MenuDAO();

	/**
     * Retrieves a list of all menus.
     *
     * @return A list of all menus.
     * @throws ServiceException If there's an issue with the service operation.
     */
	public List<Menu> getAllMenus() throws ServiceException {

		List<Menu> menuList = new ArrayList<>();
		try {

			menuList = menuDAO.findAll();

			Iterator<Menu> iterator = menuList.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return menuList;
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

			System.out.println(menuDAO.findById(menuId));

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

}
