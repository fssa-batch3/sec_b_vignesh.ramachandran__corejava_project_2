package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.Order;
import in.fssa.srcatering.model.OrderStatus;
import in.fssa.srcatering.util.ConnectionUtil;

public class OrderDAO {
	
	public int create(Order order) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int generatedId = -1;
		
		try {
			String query = "INSERT INTO orders(user_id, no_of_guest, total_cost, order_date, delivery_date, order_status,"
					+ "menu_id, category_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			java.sql.Timestamp orderDateTime = java.sql.Timestamp.valueOf(order.getOrderDate());
			
			// Parse the date string into a LocalDate object
	        java.sql.Date deliveryDate = java.sql.Date.valueOf(order.getDeliveryDate());
			
			ps.setInt(1, order.getUserId());
			ps.setInt(2, order.getNoOfGuest());
			ps.setInt(3, order.getTotalCost());
			ps.setTimestamp(4, orderDateTime);
			ps.setDate(5, deliveryDate);
			ps.setString(6, order.getOrderStatus().name()); 
			ps.setInt(7, order.getMenuId());
			ps.setInt(8, order.getCategoryId());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}
			
			System.out.println("Order created sucessfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}finally {
			ConnectionUtil.close(con, ps);
		}
		return generatedId;
	}
	
	/**
	 * 
	 * @param status
	 * @param orderId
	 * @throws DAOException
	 */
	public void update(OrderStatus status, int orderId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE orders SET order_status = ? WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, status.name());
			ps.setInt(2, orderId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	
	/**
	 * 
	 * @param orderId
	 * @throws DAOException
	 */
	public void isOrderIdIsValid(int orderId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT id FROM orders WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			
			if(!rs.next()) {
				throw new DAOException("OrderId not found");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws DAOException
	 */
	public List<Order> findAllOrdersByUserId(int userId) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Order> orderList = new ArrayList<>();
		
		try {
			String query = "SELECT id, user_id, menu_id, category_id, no_of_guest, total_cost, order_date, "
					+ "delivery_date, order_status FROM orders WHERE user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, userId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setMenuId(rs.getInt("menu_id"));
				order.setCategoryId(rs.getInt("category_id"));
				order.setNoOfGuest(rs.getInt("no_of_guest"));
				order.setTotalCost(rs.getInt("total_cost"));
				
				// Convert SQL Timestamp to LocalDateTime
                Timestamp timestamp = rs.getTimestamp("order_date");
                LocalDateTime orderDate = timestamp.toLocalDateTime();
                order.setOrderDate(orderDate);
				
				// Convert SQL Date to LocalDate
				Date sqlDate = rs.getDate("delivery_date");
				order.setDeliveryDate(sqlDate.toLocalDate());
				
				String statusStr = rs.getString("order_status");
				OrderStatus orderStatus = OrderStatus.valueOf(statusStr);
				order.setOrderStatus(orderStatus);
				
				orderList.add(order);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return orderList;
	}
	

	/**
	 * 
	 * @param orderId
	 * @return
	 * @throws DAOException
	 */
	public Order findOrderByOrderId(int orderId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Order order = null;
		
		try {
			String query = "SELECT id, user_id, menu_id, category_id, no_of_guest, total_cost, order_date,"
					+ "delivery_date, order_status FROM orders WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setMenuId(rs.getInt("menu_id"));
				order.setCategoryId(rs.getInt("category_id"));
				order.setNoOfGuest(rs.getInt("no_of_guest"));
				order.setTotalCost(rs.getInt("total_cost"));
				
				// Convert SQL Timestamp to LocalDateTime
                Timestamp timestamp = rs.getTimestamp("order_date");
                LocalDateTime orderDate = timestamp.toLocalDateTime();
                order.setOrderDate(orderDate);
				
				// Convert SQL Date to LocalDate
				Date sqlDate = rs.getDate("delivery_date");
				order.setDeliveryDate(sqlDate.toLocalDate());
				
				String statusStr = rs.getString("order_status");
				OrderStatus orderStatus = OrderStatus.valueOf(statusStr);
				order.setOrderStatus(orderStatus);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return order;
	}
	

}
