package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.OrderProduct;
import in.fssa.srcatering.util.ConnectionUtil;

public class OrderProductDAO {
	
	/**
	 * 
	 * @param orderProduct
	 * @throws DAOException
	 */
	public void create(OrderProduct orderProduct) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			String query = "INSERT INTO order_products(order_id, dish_id, price_id) VALUES(?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			Map<Integer, Integer> dishIdPriceIdMap = orderProduct.getDishIdPriceIdMap();
			
			for(Map.Entry<Integer, Integer> entry: dishIdPriceIdMap.entrySet()) {
				
				int dishId = entry.getKey();
				int priceId = entry.getValue();
				
				ps.setInt(1, orderProduct.getOrderId());
				ps.setInt(2, dishId);
				ps.setInt(3, priceId);
				
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			System.out.println("OrderedProduct created sucessfully");
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * 
	 * @param orderId
	 * @return
	 * @throws DAOException
	 */
	public OrderProduct findOrderProductsByOrderId(int orderId) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		OrderProduct orderProduct = null;
		
		Map<Integer, Integer> dishIdPriceIdMap = new HashMap<>();
		
		try {
			String query = "SELECT id,order_id, dish_id, price_id FROM order_products WHERE order_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				orderProduct = new OrderProduct();
				dishIdPriceIdMap.put(rs.getInt("dish_id"), rs.getInt("price_id"));
				orderProduct.setId(rs.getInt("id"));
				orderProduct.setOrderId(rs.getInt("order_id"));
				orderProduct.setDishIdPriceIdMap(dishIdPriceIdMap);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return orderProduct;
	}

}
