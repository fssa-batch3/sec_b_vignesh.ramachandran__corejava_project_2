package in.fssa.srcatering.model;

import java.time.LocalDate;

public class Cart extends CartEntity {
	
	public Cart() {
		
	}
	
	public Cart(int id, int userId, int menuId, int categoryId, int totalCost, int noOfGuest, LocalDate deliveryDate) {
		
		super.setId(id);
		super.setUserId(userId);
		super.setMenuId(menuId);
		super.setCategoryId(categoryId);
		super.setPrice(totalCost);
		super.setNoOfGuest(noOfGuest);
		super.setDeliveryDate(deliveryDate);
	}

}
