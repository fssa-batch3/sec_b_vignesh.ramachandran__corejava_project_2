package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.Category;
import in.fssa.srcatering.util.ConnectionUtil;

public class CategoryDAO {

	/**
	 * This method retrieves a list of all categories from the database.
	 * @return A list of Category objects representing the categories retrieved from the database.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public List<Category> findAll() throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Category> categoryList = new ArrayList<>();

		try {
			String query = "SELECT id, category_name FROM categories";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setCategoryName(rs.getString("category_name"));
				categoryList.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return categoryList;
	}

	/**
	 * Retrieves a Category object from the database based on the provided category ID.
	 *
	 * @param categoryId The ID of the category to search for.
	 * @return A Category object representing the category with the specified ID, or null if not found.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public Category findById(int categoryId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Category newCategory = null;

		try {

			String query = "SELECT id,category_name FROM categories WHERE id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, categoryId);

			rs = ps.executeQuery();

			if (rs.next()) {
				newCategory = new Category();
				newCategory.setId(rs.getInt("id"));
				newCategory.setCategoryName(rs.getString("category_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return newCategory;

	}
	
	
	/**
	 * Checks whether a category with the specified ID exists in the database.
	 *
	 * @param categoryId The ID of the category to check.
	 * @throws DAOException If there's an issue with the database operation or if the category ID is not found.
	 */
	public void isCategoryIdIsValid(int categoryId)throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT id FROM categories WHERE id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, categoryId);
			
			rs = ps.executeQuery();
			if(!rs.next()) {
				throw new DAOException("CategoryId not found");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

}
