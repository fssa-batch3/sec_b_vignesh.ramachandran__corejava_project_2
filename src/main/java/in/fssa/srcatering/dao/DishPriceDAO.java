package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.Logger;

public class DishPriceDAO {

	/**
	 * Creates a new dish price entry in the 'dish_price' table.
	 *
	 * @param dishId   The ID of the dish for which the price is being created.
	 * @param price    The price of the dish.
	 * @param dateTime The start date and time of the price.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public void create(int dishId, int price, Timestamp dateTime) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null; 
		boolean rollBack = false;

		try {
			con = ConnectionUtil.getConnection();
			con.setAutoCommit(false);

			String query = "INSERT INTO dish_price(dish_id, price,start_date) VALUES (?,?,?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, dishId);
			ps.setInt(2, price);
			ps.setTimestamp(3, dateTime); 
			ps.executeUpdate(); 

			con.commit();

		} catch (SQLException e) {
			rollBack = true;
			Logger.error(e);

			try {
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace(); 
			}

			throw new DAOException("Error creating DishPrice "+e.getMessage());
		} finally {
			try {

				if (ps != null) {
					ps.close();
				}

				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
				
			} catch (SQLException e) {
				Logger.error(e);
				Logger.debug(e);
			}

			if (rollBack) {
				// Perform any necessary cleanup or additional handling
				DishDAO dishDAO = new DishDAO();
				dishDAO.deleteByDishId(dishId);
			}
		}

	}

	/**
	 * Updates the end date of a dish price entry in the 'dish_price' table.
	 *
	 * @param priceId  The ID of the dish price entry to update.
	 * @param dateTime The end date and time of the price.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public void update(int priceId, Timestamp dateTime) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE dish_price SET end_date = ? WHERE id =? AND end_date IS NULL";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setTimestamp(1, dateTime);
			ps.setInt(2, priceId);
			ps.executeUpdate();

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Retrieves the active price of a dish based on the provided dish ID from the
	 * 'dish_price' table.
	 *
	 * @param dishId The ID of the dish for which to retrieve the price.
	 * @return The active price of the dish.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public int findPriceByDishId(int dishId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int price = -1;

		try {
			String query = "SELECT price FROM dish_price WHERE dish_id=? AND end_date IS NULL";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dishId);

			rs = ps.executeQuery();

			if (rs.next()) {
				price = rs.getInt("price");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return price;
	}

	/**
	 * Retrieves the ID of the active price entry for a dish based on the provided
	 * dish ID from the 'dish_price' table.
	 *
	 * @param dishId The ID of the dish for which to retrieve the price entry ID.
	 * @return The ID of the active price entry for the dish.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public int findPriceIdByDishId(int dishId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int priceId = 0;

		try {
			String query = "SELECT id FROM dish_price WHERE dish_id = ? AND end_date IS NULL";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dishId);
			rs = ps.executeQuery();

			if (rs.next()) {
				priceId = rs.getInt("id");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return priceId;
	}

	/**
	 * Checks whether a dish ID exists in the 'dish_price' table.
	 *
	 * @param dishId The ID of the dish to check.
	 * @throws DAOException If there's an issue with the database operation or if
	 *                      the dish ID is not found.
	 */
	public void iDishIdIsValid(int dishId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT dish_id FROM dish_price WHERE dish_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dishId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("Invalid DishId");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
	/**
	 * 
	 * @param menuId
	 * @param categoryId
	 * @return
	 * @throws DAOException
	 */
	public List<Integer> findAllPriceIdsByMenuIdAndCategoryId(int menuId, int categoryId) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Integer> priceIds = new ArrayList<>();
		
		try {
			String query = "SELECT dp.id FROM dish_price dp JOIN category_dishes cd ON dp.dish_id = cd.dish_id "
					+ "WHERE dp.end_date IS NULL AND cd.menu_id = ? AND cd.category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, menuId);
			ps.setInt(2, categoryId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				priceIds.add(rs.getInt("id"));
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return priceIds;
	}
	

	/**
	 * Deletes dish prices from the database associated with the provided dish ID.
	 *
	 * @param dishId The ID of the dish for which dish prices are to be deleted.
	 * @throws DAOException If a database error occurs during the deletion.
	 */
	void deleteDishPriceByDishId(int dishId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "DELETE FROM dish_price WHERE dish_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, dishId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * 
	 * @param dishIds
	 * @throws DAOException
	 */
	public int getTotalPriceByPriceIds(List<Integer> priceIds) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int sum = 0;
		
		try {
			
			StringBuilder idList = new StringBuilder();
            for (int i = 0; i < priceIds.size(); i++) {
                idList.append(priceIds.get(i));
                if (i < priceIds.size() - 1) {
                    idList.append(",");
                }
            }
			
            String query = "SELECT SUM(price) AS total FROM dish_price WHERE id IN (" + idList.toString() + ")";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				sum = rs.getInt("total");
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
		
		return sum;
	}

}
