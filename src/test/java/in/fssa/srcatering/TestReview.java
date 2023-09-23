package in.fssa.srcatering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Review;
import in.fssa.srcatering.service.ReviewService;

public class TestReview {
	
	@Test
	void testCreateReviewWithInvalidInput() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			reviewService.createReview(null) ;
		});
		String expectedMessage = "Invalid Review Input";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage,actualMessage);
	}
	
	
	@Test
	void testCreateReviewWithOrderIdZero() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(0);
			review.setMenuId(1);
			review.setCategoryId(1);
			review.setStar(4);
			review.setFeedback("Excellent Food");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "OrderId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateReviewWithNegativeOrderId() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(-2);
			review.setMenuId(1);
			review.setCategoryId(1);
			review.setStar(4);
			review.setFeedback("Excellent Food");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "OrderId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateReviewWithInvalidOrderId() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(1000);
			review.setMenuId(1);
			review.setCategoryId(1);
			review.setStar(4);
			review.setFeedback("Excellent Food");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "Invalid OrderId";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateReviewWithMenuIdZero() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(1);
			review.setMenuId(0);
			review.setCategoryId(1);
			review.setStar(4);
			review.setFeedback("Excellent Food");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateReviewWithNegativeMenuId() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(1);
			review.setMenuId(-2);
			review.setCategoryId(1);
			review.setStar(4);
			review.setFeedback("Excellent Food");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "MenuId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateReviewWithInvalidMenuId() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(1);
			review.setMenuId(100);
			review.setCategoryId(1);
			review.setStar(4);
			review.setFeedback("Excellent Food");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "MenuId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateReviewWithCategoryIdZero() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(1);
			review.setMenuId(1);
			review.setCategoryId(0);
			review.setStar(4);
			review.setFeedback("Excellent Food");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateReviewWithNegativeCategoryId() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(1);
			review.setMenuId(1);
			review.setCategoryId(-2);
			review.setStar(4);
			review.setFeedback("Excellent Food");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "CategoryId cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateReviewWithInvalidCategoryId() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(1);
			review.setMenuId(1);
			review.setCategoryId(100);
			review.setStar(4);
			review.setFeedback("Excellent Food");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "CategoryId not found";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateReviewWithStarZero() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(1);
			review.setMenuId(1);
			review.setCategoryId(1);
			review.setStar(0);
			review.setFeedback("Excellent Food");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "Star cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void testCreateReviewWithNegativeStar() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(1);
			review.setMenuId(1);
			review.setCategoryId(1);
			review.setStar(-1);
			review.setFeedback("Excellent Food");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "Star cannot be less than or equal to zero";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateReviewWithFeedBackNull() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(1);
			review.setMenuId(1);
			review.setCategoryId(1);
			review.setStar(5);
			review.setFeedback(null);
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "FeedBack cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	
	@Test
	void testCreateReviewWithFeedBackEmpty() {
		ReviewService reviewService = new ReviewService();
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			
			Review review = new Review();
			review.setOrderId(1);
			review.setMenuId(1);
			review.setCategoryId(1);
			review.setStar(5);
			review.setFeedback("");
			
			reviewService.createReview(review);

		});
		
		String expectedMessage = "FeedBack cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	

	
	

}
