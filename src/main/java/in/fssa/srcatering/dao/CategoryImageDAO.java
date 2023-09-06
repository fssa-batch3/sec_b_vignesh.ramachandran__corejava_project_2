package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.Category;
import in.fssa.srcatering.util.ConnectionUtil;

public class CategoryImageDAO {

	/**
	 * 
	 * @param menu_id
	 * @param category_id
	 * @param image
	 * @throws DAOException
	 */
	public void createCategoryImage(int menu_id, int category_id, String image) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null; 

		try {
			String query = "INSERT INTO category_images(menu_id, category_id, image) VALUES(?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menu_id);
			ps.setInt(2, category_id);
			ps.setString(3, image);

			ps.executeUpdate();
			System.out.println("Category Image created sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @param menu_id
	 * @param category_id
	 * @param image
	 * @throws DAOException
	 */
	public void updateCategoryImage(int menu_id, int category_id, String image) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE category_images SET image=? WHERE menu_id = ? AND category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, image);
			ps.setInt(2, menu_id);
			ps.setInt(3, category_id);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Category with id " + category_id + " has been updated successfully.");
			} else {
				System.out.println("Category with id " + category_id + " not found.");
			}

		} catch (SQLException e) { 
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	
	
	

}
