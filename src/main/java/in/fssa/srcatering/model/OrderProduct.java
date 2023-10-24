package in.fssa.srcatering.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class OrderProduct extends OrderProductEntity{

	

	public OrderProduct() {

	}

	public OrderProduct(int id, int ordeId, int dishId, int priceId, Map<Integer, Integer> dishIdPriceIdMap,
			int menuId, int categoryId, int noOfGuest,LocalDate deliveryDate, OrderStatus orderStatus, LocalDateTime cancelDate, String cancelReason,
			CaterApproval caterApproval, String rejectReason) {
		
		super.setId(id);
		super.setOrderId(ordeId);
		super.setDishId(dishId);
		super.setPriceId(priceId);
		super.setDishIdPriceIdMap(dishIdPriceIdMap);
		super.setMenuId(menuId);
		super.setCategoryId(categoryId);
		super.setNoOfGuest(noOfGuest);
		super.setDeliveryDate(deliveryDate);
		super.setOrderStatus(orderStatus);
		super.setCancelDate(cancelDate);
		super.setCancelReason(cancelReason);
		super.setCaterApproval(caterApproval);
		super.setRejectReason(rejectReason);
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return getId() == that.getId() &&
               getOrderId() == that.getOrderId() &&
               getDishId() == that.getDishId() &&
               getPriceId() == that.getPriceId() &&
               getNoOfGuest() == that.getNoOfGuest() &&
               getMenuId() == that.getMenuId() &&
               getCategoryId() == that.getCategoryId() &&
               Objects.equals(getDeliveryDate(), that.getDeliveryDate()) &&
               getOrderStatus() == that.getOrderStatus() &&
               Objects.equals(getCancelDate(), that.getCancelDate());
               
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrderId(), getDishId(), getPriceId(),  getNoOfGuest(), getDeliveryDate(),  getOrderStatus(),
        		getMenuId(), getCategoryId(), getCancelDate(), getCancelReason());
    }

}
