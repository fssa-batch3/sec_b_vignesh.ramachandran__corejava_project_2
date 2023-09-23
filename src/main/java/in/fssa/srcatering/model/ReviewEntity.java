package in.fssa.srcatering.model;

public class ReviewEntity {
	
	private int id;
	private int orderId;
	private int userId;
	private int menuId;
	private int categoryId;
	private int star;
	private String feedback;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	@Override
	public String toString() {
		return "ReviewEntity [id=" + id + ", orderId=" + orderId + ", userId=" + userId + ", menuId=" + menuId
				+ ", categoryId=" + categoryId + ", star=" + star + ", feedback=" + feedback + "]";
	}
	

}
