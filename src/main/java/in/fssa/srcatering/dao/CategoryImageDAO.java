package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.Logger;

public class CategoryImageDAO {

	/**
	 * Create a new category image entry in the database for a specific menu and category.
	 *
	 * @param menu_id The ID of the menu to associate the image with.
	 * @param category_id The ID of the category to associate the image with.
	 * @param image The image URL or path to be stored.
	 * @throws DAOException If a database error occurs during the creation.
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
			ps.setString(3, image.trim());

			ps.executeUpdate();

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Update the image URL for an existing category image entry in the database.
	 *
	 * @param menu_id The ID of the menu associated with the category image.
	 * @param category_id The ID of the category associated with the image.
	 * @param image The updated image URL or path.
	 * @throws DAOException If a database error occurs during the update.
	 */
	public void updateCategoryImage(int menu_id, int category_id, String image) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE category_images SET image=? WHERE menu_id = ? AND category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, image.trim());
			ps.setInt(2, menu_id);
			ps.setInt(3, category_id);

			ps.executeUpdate();

		} catch (SQLException e) { 
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	
	
	

}
