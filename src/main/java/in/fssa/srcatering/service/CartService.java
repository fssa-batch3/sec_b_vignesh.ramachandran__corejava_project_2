package in.fssa.srcatering.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.dao.CartDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Cart;
import in.fssa.srcatering.util.Logger;
import in.fssa.srcatering.validator.CartValidator;
import in.fssa.srcatering.validator.UserValidator;

public class CartService {

	CartDAO addtoCartDAO = new CartDAO();

	/**
	 * Create a new cart entry or update an existing one in the database. Validates the cart data, calculates the total price, and sets a delivery date.
	 *
	 * @param cart The cart data to be created or updated.
	 * @throws ValidationException If the cart data is invalid.
	 * @throws ServiceException If a service error occurs during cart creation or update.
	 */
	public void createAddtoCart(Cart cart) throws ValidationException, ServiceException {

		try {

			// validate addtoCart
			CartValidator.validateCart(cart); 

			// setting totalCost
			int price = new CategoryService().getTotalPriceOfTheCategoryByMenuIdAndCategoryId(cart.getMenuId(),
					cart.getCategoryId());

			cart.setPrice(price);
			
			LocalDate deliveryDate = LocalDate.now().plusDays(7);
			cart.setDeliveryDate(deliveryDate);

			CartValidator.isThatMenuAndCategoryAlreadyExistsForThatUser(cart.getMenuId(), cart.getCategoryId(),
					cart.getUserId());

			// create addtoCart
			addtoCartDAO.create(cart);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Cart creation failed");
		}
	}

	/**
	 * Update an existing cart entry in the database with new guest count and delivery date.
	 *
	 * @param noOfGuest The number of guests for the cart.
	 * @param deliveryDate The delivery date for the cart.
	 * @param cartId The ID of the cart to update.
	 * @throws ValidationException If the provided data is invalid or the cart ID is invalid.
	 * @throws ServiceException If a service error occurs during cart update.
	 */
	public void updateCart(int noOfGuest, LocalDate deliveryDate, int cartId)
			throws ValidationException, ServiceException {

		try {
			// validate data
			CartValidator.validateNoOfGuest(noOfGuest);
			CartValidator.validateDeliveryDate(deliveryDate);

			if (!CartValidator.isCartIdIsValid(cartId)) {
				throw new ValidationException("Invalid CartId");
			}

			// update cart
			addtoCartDAO.updateCart(noOfGuest, deliveryDate, cartId);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Cart updation failed");
		}
	}

	/**
	 * Delete a cart entry from the database based on the provided cart ID.
	 *
	 * @param cartId The ID of the cart to delete.
	 * @throws ValidationException If the provided cart ID is invalid.
	 * @throws ServiceException If a service error occurs during cart deletion.
	 */
	public void deleteCart(int cartId) throws ValidationException, ServiceException {

		try {

			if (!CartValidator.isCartIdIsValid(cartId)) {
				throw new ValidationException("Invalid CartId");
			}
			addtoCartDAO.deleteCart(cartId);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Cart deletion failed");
		}
	}
	
	/**
	 * Delete all cart items associated with a specific user from the database.
	 *
	 * @param userId The ID of the user for whom cart items should be deleted.
	 * @throws ValidationException If the provided user ID is invalid.
	 * @throws ServiceException If a service error occurs during cart items deletion.
	 */
	public void deleteAllCart(int userId) throws ServiceException, ValidationException {
		
		try {
			UserValidator.isUserIdIsValid(userId);
			addtoCartDAO.deleteAllCart(userId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("AllCart items deletion failed");
		}
		
	}

	/**
	 * Get all cart entries associated with a specific user from the database.
	 *
	 * @param userId The ID of the user to retrieve cart entries for.
	 * @return A list of cart entries associated with the user.
	 * @throws ValidationException If the provided user ID is invalid.
	 * @throws ServiceException If a service error occurs during cart retrieval.
	 */
	public List<Cart> getAllCartsByUserId(int userId) throws ValidationException, ServiceException {

		List<Cart> cartList = new ArrayList<>();
		try {

			UserValidator.isUserIdIsValid(userId);
			cartList = addtoCartDAO.findAllCartsByUserId(userId);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Unable to getAll Carts");
		}
		return cartList;
	}

	/**
	 * Get a specific cart entry by its ID from the database.
	 *
	 * @param cartId The ID of the cart entry to retrieve.
	 * @return The cart entry with the provided ID, or null if not found.
	 * @throws ValidationException If the provided cart ID is invalid.
	 * @throws ServiceException If a service error occurs during cart retrieval.
	 */
	public Cart getCartByCartId(int cartId) throws ValidationException, ServiceException {

		Cart cart = null;
		try {

			if (!CartValidator.isCartIdIsValid(cartId)) {
				throw new ValidationException("Invalid CartId");
			}
			cart = addtoCartDAO.findCartByCartId(cartId);

		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("Unable to get Cart");
		}
		return cart;
	}

	/**
	 * Get the count of cart entries associated with a specific user from the database.
	 *
	 * @param userId The ID of the user to count cart entries for.
	 * @return The count of cart entries associated with the user.
	 * @throws ValidationException If the provided user ID is invalid.
	 * @throws ServiceException If a service error occurs during count retrieval.
	 */
	public int getCartCountByUserId(int userId) throws ValidationException, ServiceException {

		int count = 0;
		try {
			UserValidator.isUserIdIsValid(userId);
			count = addtoCartDAO.findCartCountByUserId(userId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return count;
	}

}
