package in.fssa.srcatering.model;

import java.util.List;
import java.util.Map;

public class OrderProduct extends OrderProductEntity {

	public OrderProduct() {

	}

	public OrderProduct(int ordeId, int dishId, int priceId, List<Integer> dishIds, Map<Integer, Integer> dishIdPriceIdMap) {
		super.setOrderId(ordeId);
		super.setDishId(dishId);
		super.setPriceId(priceId);
		super.setDishIdPriceIdMap(dishIdPriceIdMap);
	}

}
