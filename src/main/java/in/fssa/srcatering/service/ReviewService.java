package in.fssa.srcatering.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.dao.ReviewDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Review;
import in.fssa.srcatering.util.Logger;
import in.fssa.srcatering.validator.ReviewValidator;

public class ReviewService {
	
	ReviewDAO reviewDAO = new ReviewDAO();
	
	/**
	 * Create a new review and store it in the database.
	 *
	 * @param review The review to be created.
	 * @throws ValidationException If the provided review is invalid.
	 * @throws ServiceException If a service error occurs during review creation.
	 */
	public void createReview(Review review) throws ValidationException, ServiceException {

		try {
			ReviewValidator.validateReview(review);
			reviewDAO.create(review);
		} catch (DAOException e) {
			Logger.error(e); 
			throw new ServiceException(e.getMessage()); 
		}
	}
	
	/**
	 * Get a review by its associated order ID, menu ID, and category ID.
	 *
	 * @param orderId The ID of the associated order.
	 * @param menuId The ID of the associated menu.
	 * @param categoryId The ID of the associated category.
	 * @return The review associated with the provided order, menu, and category.
	 * @throws ValidationException If the provided order ID, menu ID, or category ID is invalid.
	 * @throws ServiceException If a service error occurs while retrieving the review.
	 */
	public Review getReviewByOrderIdAndMenuIdAndCategoryId(int orderId, int menuId, int categoryId) throws ValidationException, ServiceException {
		
		Review review = null;
		
		try {
			ReviewValidator.getReviewByOrderIdMenuIdCategoryId(orderId, menuId, categoryId);
			review  = reviewDAO.findReviewByOrderIdAndMenuIdAndCategoryId(orderId, menuId, categoryId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return review;
		
	}
	
	/**
	 * Get a list of reviews associated with a specific menu and category.
	 *
	 * @param menuId The ID of the menu.
	 * @param categoryId The ID of the category.
	 * @return A list of reviews associated with the provided menu and category.
	 * @throws ValidationException If the provided menu ID or category ID is invalid.
	 * @throws ServiceException If a service error occurs while retrieving reviews.
	 */
	public List<Review> getAllReviewsByMenuIdAndCategoryId(int menuId, int categoryId) throws ValidationException, ServiceException{
		
		List<Review> reviewList = new ArrayList<>();
		
		try {
			ReviewValidator.getAllReviewsByMenuIdCategoryId(menuId, categoryId);
			reviewList = reviewDAO.findAllReviewsByMenuIdAndCategoryId(menuId, categoryId);
		} catch (DAOException e) {
			Logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return reviewList;
	}

}
