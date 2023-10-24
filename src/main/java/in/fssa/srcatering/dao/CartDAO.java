package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.Cart;
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.Logger;

public class CartDAO {

	/**
	 * Create a new cart in the cart table
	 * 
	 * @param cart
	 * @throws DAOException
	 */
	public void create(Cart cart) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO cart(user_id, menu_id, category_id, total_cost, no_of_guest, delivery_date) "
					+ "VALUES(?, ?, ?, ?, ?, ?)"; 
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			// Parse the date string into a LocalDate object
			java.sql.Date deliveryDate = java.sql.Date.valueOf(cart.getDeliveryDate());

			ps.setInt(1, cart.getUserId());
			ps.setInt(2, cart.getMenuId());
			ps.setInt(3, cart.getCategoryId());
			ps.setInt(4, cart.getPrice());
			ps.setInt(5, cart.getNoOfGuest());
			ps.setDate(6, deliveryDate);

			ps.executeUpdate();

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	
	/**
	 * Update cart details in the cart table using cartId
	 * 
	 * @param noOfGuest
	 * @param deliveryDate
	 * @param cartId
	 * @throws DAOException
	 */
	public void updateCart(int noOfGuest,LocalDate deliveryDate, int cartId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE cart SET no_of_guest = ?, delivery_date = ? WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			// Parse the date string into a LocalDate object
			java.sql.Date deliveryDate1 = java.sql.Date.valueOf(deliveryDate);

			ps.setInt(1, noOfGuest);
			ps.setDate(2, deliveryDate1);
			ps.setInt(3, cartId);

			ps.executeUpdate();

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	

	/**
	 * Delete cart from cart table using cartId
	 * 
	 * @param cartId
	 * @throws DAOException
	 */
	public void deleteCart(int cartId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "DELETE FROM cart WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, cartId);
			ps.executeUpdate();

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	
	/**
	 * Delete cart from cart table using userId
	 * 
	 * @param userId
	 * @throws DAOException if userId is invalid
	 */
	public void deleteAllCart(int userId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "DELETE FROM cart WHERE user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, userId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	
	/**
	 * Check the cartId is valid or not
	 * 
	 * @param cartId
	 * @return true if cartId is valid otherwise return false 
	 * @throws DAOException
	 */
	public boolean isCartIdIsValid(int cartId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM cart WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query); 

			ps.setInt(1, cartId);
			rs = ps.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	/**
	 * Retrieve list of carts from cart table by using userId
	 * 
	 * @param userId
	 * @return list of carts
	 * @throws DAOException if userId is not found
	 */
	public List<Cart> findAllCartsByUserId(int userId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Cart> cartList = new ArrayList<>();

		try {
			String query = "SELECT id, user_id, menu_id, category_id, no_of_guest, total_cost, delivery_date FROM cart WHERE user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Cart cart = new Cart();
				cart.setId(rs.getInt("id"));
				cart.setUserId(rs.getInt("user_id"));
				cart.setMenuId(rs.getInt("menu_id"));
				cart.setCategoryId(rs.getInt("category_id"));
				cart.setNoOfGuest(rs.getInt("no_of_guest"));
				cart.setPrice(rs.getInt("total_cost"));

				Date sqlDate = rs.getDate("delivery_date");
				cart.setDeliveryDate(sqlDate.toLocalDate());

				cartList.add(cart);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return cartList;
	}
	
	/**
	 * find cart by cartId
	 * 
	 * @param userId
	 * @return cart details
	 * @throws DAOException if cartId is invalid
	 */
	public Cart findCartByCartId(int cartId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Cart cart = null;
		
		try {
			String query = "SELECT id, user_id, menu_id, category_id, no_of_guest, total_cost, delivery_date FROM cart WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, cartId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				cart = new Cart();
				cart.setId(rs.getInt("id"));
				cart.setUserId(rs.getInt("user_id"));
				cart.setMenuId(rs.getInt("menu_id"));
				cart.setCategoryId(rs.getInt("category_id"));
				cart.setNoOfGuest(rs.getInt("no_of_guest"));
				cart.setPrice(rs.getInt("total_cost"));

				Date sqlDate = rs.getDate("delivery_date");
				cart.setDeliveryDate(sqlDate.toLocalDate());
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return cart;
	}
	
	/**
	 * Check the menu and categoryId is exists in the cart table for the particular user
	 * 
	 * @param menuId
	 * @param categoryId
	 * @param userId
	 * @throws DAOException if menuId or categoryId or userId is invalid
	 */
	public void isThatMenuAndCategoryAlreadyExists(int menuId, int categoryId, int userId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT menu_id, category_id, user_id FROM cart WHERE user_id = ? AND menu_id = ? AND category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, userId);
			ps.setInt(2, menuId);
			ps.setInt(3, categoryId);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				throw new DAOException("This Menu With Category Already Exists in the cart");
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
	/**
	 * get the count of the cart items for the particular user
	 * 
	 * @param userId
	 * @return count of the cart items
	 * @throws DAOException if userId is invalid
	 */
	public int findCartCountByUserId(int userId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			String query = "SELECT COUNT(id) FROM cart WHERE user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
	            count = rs.getInt(1);
	        } 
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return count;
	}

}
