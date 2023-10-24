package in.fssa.srcatering.dao;

import java.sql.Connection;
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
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.Logger;

public class OrderDAO {
	
	/**
	 * Create a new order entry in the database.
	 *
	 * @param order The Order object representing the order to be created.
	 * @return The ID of the newly created order.
	 * @throws DAOException If a database error occurs during the creation.
	 */
	public int create(Order order) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int generatedId = -1;
		
		try {
			String query = "INSERT INTO orders(user_id, address_id, order_date,event_name,total_cost) VALUES(?, ?, ?, ?, ?)";
			con = ConnectionUtil.getConnection(); 
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			java.sql.Timestamp orderDateTime = java.sql.Timestamp.valueOf(order.getOrderDate());
			
			ps.setInt(1, order.getUserId());
			ps.setInt(2, order.getAddressId());
			ps.setTimestamp(3, orderDateTime);
			ps.setString(4, order.getEventName());
			ps.setInt(5, order.getTotalCost()); 
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		}finally {
			ConnectionUtil.close(con, ps);
		}
		return generatedId;
	}
	
	
	
	/**
	 * Check if an order with the specified order ID exists in the database.
	 *
	 * @param orderId The ID of the order to check.
	 * @throws DAOException If there's an issue with the database operation or if the order ID is not found.
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
				throw new DAOException("Invalid OrderId");
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
	/**
	 * Retrieve a list of all orders associated with a specific user.
	 *
	 * @param userId The ID of the user to filter orders.
	 * @return A List of Order objects representing all orders of the specified user.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public List<Order> findAllOrdersByUserId(int userId) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Order> orderList = new ArrayList<>();
		
		try {
			String query = "SELECT id, user_id, address_id, total_cost, order_date, event_name FROM orders WHERE user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, userId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setAddressId(rs.getInt("address_id"));
				order.setTotalCost(rs.getInt("total_cost"));
				order.setEventName(rs.getString("event_name"));
				
				// Convert SQL Timestamp to LocalDateTime
                Timestamp timestamp = rs.getTimestamp("order_date");
                LocalDateTime orderDate = timestamp.toLocalDateTime();
                order.setOrderDate(orderDate);
				
				orderList.add(order);
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return orderList;
	}
	

	/**
	 * Retrieve an order by its order ID.
	 *
	 * @param orderId The ID of the order to retrieve.
	 * @return The Order object representing the requested order.
	 * @throws DAOException If a database error occurs during the retrieval or if the order ID is not found.
	 */
	public Order findOrderByOrderId(int orderId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Order order = null;
		
		try {
			String query = "SELECT id, user_id, address_id, total_cost, order_date, event_name FROM orders WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setAddressId(rs.getInt("address_id"));
				order.setTotalCost(rs.getInt("total_cost"));
				order.setEventName(rs.getString("event_name"));
				
				// Convert SQL Timestamp to LocalDateTime
                Timestamp timestamp = rs.getTimestamp("order_date");
                LocalDateTime orderDate = timestamp.toLocalDateTime();
                order.setOrderDate(orderDate);
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return order;
	}
	
	/**
	 * Retrieve a list of all orders in the database.
	 *
	 * @return A List of Order objects representing all orders in the database.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public List<Order> findAllOrders() throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Order> orderList = new ArrayList<>();
		
		try {
			String query = "SELECT id, user_id, address_id, total_cost, order_date, event_name FROM orders";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			rs =  ps.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setAddressId(rs.getInt("address_id"));
				order.setTotalCost(rs.getInt("total_cost"));
				order.setEventName(rs.getString("event_name"));
				
				// Convert SQL Timestamp to LocalDateTime
                Timestamp timestamp = rs.getTimestamp("order_date");
                LocalDateTime orderDate = timestamp.toLocalDateTime();
                order.setOrderDate(orderDate);
                
                orderList.add(order);
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return orderList;
		
	}
	
}
