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
	 * 
	 * @param cart
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param noOfGuest
	 * @param cartId
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param cartId
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @throws ServiceException
	 */
	public void deleteAllCart() throws ServiceException {
		
		try {
			addtoCartDAO.deleteAllCart();
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException("AllCart items deletion failed");
		}
		
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param userId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param userId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException 
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
