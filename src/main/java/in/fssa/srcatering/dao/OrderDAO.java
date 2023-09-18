package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
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
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return order;
	}
	

}
