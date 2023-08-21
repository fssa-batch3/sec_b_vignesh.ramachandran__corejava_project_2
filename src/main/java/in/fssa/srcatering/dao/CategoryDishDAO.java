package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.Menu;
import in.fssa.srcatering.util.ConnectionUtil;

public class CategoryDishDAO {
	
	/**
	 * Checks whether a menu ID exists in the 'category_dish' table with an active status.
	 *
	 * @param menu_id The ID of the menu to check.
	 * @throws DAOException If there's an issue with the database operation or if the menu ID is not found.
	 */
	public void isMenuIdIsValid(int menu_id)throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT * FROM category_dish WHERE menu_id=? AND status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, menu_id);
			rs = ps.executeQuery();
			
			if(!rs.next()) {
				throw new DAOException("MenuId not found");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} 
		finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
	/**
	 * Checks whether a category ID exists for a given menu ID in the 'category_dish' table with an active status.
	 *
	 * @param menu_id The ID of the menu to check.
	 * @param category_id The ID of the category to check.
	 * @throws DAOException If there's an issue with the database operation or if the category ID is not found.
	 */
	public void isCategoryIdIsValid(int menu_id, int category_id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM category_dish WHERE menu_id = ? AND category_id = ? AND status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, menu_id);
			ps.setInt(2, category_id);
			
			rs = ps.executeQuery();
			
			if(!rs.next()) {
				throw new DAOException("CategoryId not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} 
		finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
	/**
	 * Checks whether a dish ID exists in the 'category_dish' table with an active status.
	 *
	 * @param dish_id The ID of the dish to check.
	 * @throws DAOException If there's an issue with the database operation or if the dish ID is not found.
	 */
	public void isDishIdIsValid(int dish_id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM category_dish WHERE dish_id=? AND status =1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dish_id);
			rs = ps.executeQuery();
			
			if(!rs.next()) {
				throw new DAOException("DishId not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
	
	/**
	 * Retrieves a list of dish names based on the provided menu ID and category ID from the 'dishes' and 'category_dish' tables.
	 *
	 * @param menu_id The ID of the menu to filter dishes.
	 * @param category_id The ID of the category to filter dishes.
	 * @return A list of dish names corresponding to the specified menu ID and category ID.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public List<String> findDishNameByMenuIdAndCategoryId(int menu_id, int category_id) throws DAOException {
		
		Connection con1 = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<String> dishNames = new ArrayList<String>();
		
		try {
			String query = "SELECT d.dish_name FROM dishes d JOIN category_dish cd ON d.id = cd.dish_id WHERE cd.menu_id = ? AND cd.category_id = ?";
			con1 = ConnectionUtil.getConnection();
			ps = con1.prepareStatement(query);
			ps.setInt(1, menu_id);
			ps.setInt(2, category_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dishNames.add(rs.getString("dish_name").trim());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} 
		finally {
			ConnectionUtil.close(con1, ps, rs);
		}
		return dishNames;
	}
		
	/**
	 * Retrieves a list of dish IDs based on the provided menu ID and category ID from the 'category_dish' table.
	 *
	 * @param menu_id The ID of the menu to filter dishes.
	 * @param category_id The ID of the category to filter dishes.
	 * @return A list of dish IDs corresponding to the specified menu ID and category ID.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public List<Integer> findDishIdByMenuIdAndCategoryId(int menu_id, int category_id) throws DAOException{
		Connection con1 = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Integer> dishIds = new ArrayList<Integer>();
		
		try {
			String query = "SELECT dish_id FROM category_dish WHERE menu_id = ? AND category_id = ?";
			con1 = ConnectionUtil.getConnection();
			ps = con1.prepareStatement(query);
			ps.setInt(1, menu_id);
			ps.setInt(2, category_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dishIds.add(rs.getInt("dish_id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} 
		finally {
			ConnectionUtil.close(con1, ps, rs);
		}
		return dishIds;
	}
	
	
	
	/**
	 * Creates a new entry in the 'category_dish' table.
	 *
	 * @param menu_id The ID of the menu.
	 * @param category_id The ID of the category.
	 * @param dish_id The ID of the dish.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public void create(int menu_id, int category_id, int dish_id) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO category_dish(menu_id, category_id, dish_id) VALUES (?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, menu_id);
			ps.setInt(2, category_id);
			ps.setInt(3, dish_id);
			
			ps.executeUpdate();
			
			System.out.println("CategoryDish has been created sucessfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * Deletes an entry in the 'category_dish' table.
	 *
	 * @param menu_id The ID of the menu.
	 * @param category_id The ID of the category.
	 * @param dish_id The ID of the dish.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public void delete(int menu_id, int category_id, int dish_id) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE category_dish SET status = 0 WHERE menu_id=? AND category_id=? AND dish_id=? AND status =1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, menu_id);
			ps.setInt(2, category_id);
			ps.setInt(3, dish_id);
			
			ps.executeUpdate();
			System.out.println("Dish deleted sucessfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * Changes the status of an entry in the 'category_dish' table to active.
	 *
	 * @param menu_id The ID of the menu.
	 * @param category_id The ID of the category.
	 * @param dish_id The ID of the dish.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public void changeStatus(int menu_id, int category_id, int dish_id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE category_dish SET status =1 WHERE menu_id=? AND category_id=? AND dish_id=? AND status = 0";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menu_id);
			ps.setInt(2, category_id);
			ps.setInt(3, dish_id);
			
			ps.executeUpdate();
			System.out.println("done");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}


}
