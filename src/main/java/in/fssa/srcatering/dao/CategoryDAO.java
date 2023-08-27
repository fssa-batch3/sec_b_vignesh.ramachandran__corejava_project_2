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
	 * Creates a new category in the database.
	 *
	 * @param category The Category object representing the category to be created.
	 * @throws DAOException If a database error occurs during the category creation.
	 */
	public void createCategory(Category category) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO categories(category_name) VALUES(?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, category.getCategoryName());
			ps.executeUpdate();

			System.out.println("Category created sucessfully");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * This method retrieves a list of all categories from the database.
	 * 
	 * @return A list of Category objects representing the categories retrieved from
	 *         the database.
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
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return categoryList;
	}

	/**
	 * Retrieves a Category object from the database based on the provided category
	 * ID.
	 *
	 * @param categoryId The ID of the category to search for.
	 * @return A Category object representing the category with the specified ID, or
	 *         null if not found.
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
	 * @throws DAOException If there's an issue with the database operation or if
	 *                      the category ID is not found.
	 */
	public void isCategoryIdIsValid(int categoryId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM categories WHERE id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, categoryId);

			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new DAOException("CategoryId not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	/**
	 * Retrieves a list of all category names from the database.
	 *
	 * @return A List of String objects containing all category names.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public List<String> findAllCategoryNames() throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<String> categoryNames = new ArrayList<>();

		try {
			String query = "SELECT category_name FROM categories";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {
				categoryNames.add(rs.getString("category_name").trim().toLowerCase());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return categoryNames;
	}

}
