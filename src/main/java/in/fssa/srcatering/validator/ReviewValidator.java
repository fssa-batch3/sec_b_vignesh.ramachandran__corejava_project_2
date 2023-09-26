package in.fssa.srcatering.validator;

import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Review;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.util.StringUtil;

public class ReviewValidator {
	
	public static void validateReview(Review review) throws ValidationException {
		
		if (review == null) {
			throw new ValidationException("Invalid Review Input");
		}
		
		MenuValidator.isMenuIdIsValid(review.getMenuId());
		CategoryValidator.isCategoryIdExistsForThatMenu(review.getMenuId(), review.getCategoryId());
		OrderValidator.isOrderIdIsValid(review.getOrderId());
		IntUtil.rejectIfInvalidInt(review.getStar(), "Star");
		StringUtil.rejectIfInvalidString(review.getFeedback(), "FeedBack"); 
		
	}
	
	
	public static void getReviewByOrderIdMenuIdCategoryId(int orderId, int menuId, int categoryId) throws ValidationException {
		
		MenuValidator.isMenuIdIsValid(menuId);
		CategoryValidator.isCategoryIdExistsForThatMenu(menuId, categoryId);
		OrderValidator.isOrderIdIsValid(orderId);
	}
	
	
	public static void getAllReviewsByMenuIdCategoryId(int menuId, int categoryId) throws ValidationException {
		
		MenuValidator.isMenuIdIsValid(menuId);
		CategoryValidator.isCategoryIdExistsForThatMenu(menuId, categoryId);
		
	}

}
