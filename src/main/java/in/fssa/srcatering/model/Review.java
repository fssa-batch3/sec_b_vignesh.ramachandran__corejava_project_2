package in.fssa.srcatering.model;

public class Review extends ReviewEntity {
	
	public Review() {
		
	}
	
	public Review(int id, int orderId, int userId,int menuId, int categoryId, int star, String feedback) {
		super.setId(id);
		super.setOrderId(orderId);
		super.setUserId(userId);
		super.setMenuId(menuId);
		super.setCategoryId(categoryId);
		super.setStar(star);
		super.setFeedback(feedback);
	}

}
