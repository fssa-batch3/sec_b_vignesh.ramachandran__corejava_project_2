package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.util.ConnectionUtil;

public class DishPriceDAO {
	
	/**
     * Creates a new dish price entry in the 'dish_price' table.
     *
     * @param dish_id The ID of the dish for which the price is being created.
     * @param price The price of the dish.
     * @param dateTime The start date and time of the price.
     * @throws DAOException If there's an issue with the database operation.
     */
	public void create(int dish_id, int price, Timestamp dateTime) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO dish_price(dish_id, price,start_date) VALUES (?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dish_id);
			ps.setInt(2, price);
			ps.setTimestamp(3, dateTime);
			ps.executeUpdate();
			
			System.out.println("Dish price created sucessfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
		
	}
	
	/**
     * Updates the end date of a dish price entry in the 'dish_price' table.
     *
     * @param price_id The ID of the dish price entry to update.
     * @param dateTime The end date and time of the price.
     * @throws DAOException If there's an issue with the database operation.
     */
	public void update(int price_id, Timestamp dateTime) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try {
			String query = "UPDATE dish_price SET end_date = ? WHERE id =? AND end_date IS NULL";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setTimestamp(1, dateTime);
			ps.setInt(2, price_id);
			int rowsUpdated = ps.executeUpdate();
			
			if (rowsUpdated > 0) {
				System.out.println("Price with ID " + price_id + " has been updated successfully.");
			} else {
				System.out.println("Price with ID " + price_id + " not found.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
     * Retrieves the active price of a dish based on the provided dish ID from the 'dish_price' table.
     *
     * @param dish_id The ID of the dish for which to retrieve the price.
     * @return The active price of the dish.
     * @throws DAOException If there's an issue with the database operation.
     */
	public int findPriceByDishId(int dish_id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int price = -1;
		
		try {
			String query = "SELECT * FROM dish_price WHERE dish_id=? AND end_date IS NULL";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dish_id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				price = rs.getInt("price");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
		return price;
	}
	
	/**
     * Retrieves the ID of the active price entry for a dish based on the provided dish ID from the 'dish_price' table.
     *
     * @param dish_id The ID of the dish for which to retrieve the price entry ID.
     * @return The ID of the active price entry for the dish.
     * @throws DAOException If there's an issue with the database operation.
     */
	public int findPriceIdByDishId(int dish_id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int price_id = 0;
		
		try {
			String query = "SELECT * FROM dish_price WHERE dish_id = ? AND end_date IS NULL";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dish_id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				price_id = rs.getInt("id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return price_id;
	}
	
	/**
     * Checks whether a dish ID exists in the 'dish_price' table.
     *
     * @param dish_id The ID of the dish to check.
     * @throws DAOException If there's an issue with the database operation or if the dish ID is not found.
     */
	public void iDishIdIsValid(int dish_id)throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM dish_price WHERE dish_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dish_id);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("Invalid DishId");
			}

		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
}
