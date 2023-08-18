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
	 * 
	 * @param menu_id
	 * @throws DAOException
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
	 * 
	 * @param menu_id
	 * @param category_id
	 * @throws DAOException
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
	 * 
	 * @param dish_id
	 * @throws DAOException
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
	 * 
	 * @param menu_id
	 * @param category_id
	 * @return
	 * @throws DAOException
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
	 * 
	 * @param menu_id
	 * @param category_id
	 * @return
	 * @throws DAOException
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
	 * 
	 * @param menu_id
	 * @param category_id
	 * @param dish_id
	 * @throws DAOException
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
	 * 
	 * @param menu_id
	 * @param category_id
	 * @param dish_id
	 * @throws DAOException
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
