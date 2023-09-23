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
	 * 
	 * @param review
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param orderId
	 * @param menuId
	 * @param categoryId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
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
	 * 
	 * @param menuId
	 * @param categoryId
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
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
