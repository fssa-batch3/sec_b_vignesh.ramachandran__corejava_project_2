package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.dao.CartDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Cart;
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
			
			int totalCost = price * cart.getNoOfGuest();
			
			cart.setTotalCost(totalCost);

			CartValidator.isThatMenuAndCategoryAlreadyExistsForThatUser(cart.getMenuId(), cart.getCategoryId(),
					cart.getUserId());

			// create addtoCart
			addtoCartDAO.create(cart);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Cart creation failed");
		}

	}

	/**
	 * 
	 * @param cart
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void updateAddtoCart(Cart cart) throws ValidationException, ServiceException {

		try {
			// validate addtoCart
			CartValidator.validateCart(cart);

			// setting totalCost
			int totalCost = new CategoryService().getTotalPriceOfTheCategoryByMenuIdAndCategoryId(cart.getMenuId(),
					cart.getCategoryId());
			cart.setTotalCost(totalCost);

			if (!CartValidator.isCartIdIsValid(cart.getId())) {
				throw new ValidationException("Invalid CartId");
			}

			// update cart
			addtoCartDAO.update(cart);

		} catch (DAOException e) {
			e.printStackTrace();
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
			e.printStackTrace();
			throw new ServiceException("Cart deletion failed");
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
			e.printStackTrace();
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
			e.printStackTrace();
			throw new ServiceException("Unable to get Cart");
		}
		return cart;
	}

}
