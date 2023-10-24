package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.Category;
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.Logger;

public class CategoryDAO {

	private static final String CATEGORYNAME = "category_name";

	/**
	 * Retrieve a list of all categories from the category table.
	 *
	 * @return A Set of Category objects representing all categories.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public Set<Category> findAllCategories() throws DAOException {

		Connection con = null;
		PreparedStatement ps = null; 
		ResultSet rs = null;

		Set<Category> categoryList = new HashSet<>();

		try {
			String query = "SELECT c.id, c.category_name,ci.image,ci.menu_id FROM categories c "
					+ "JOIN category_images ci ON c.id = ci.category_id";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) { 
				Category category = new Category();
				category.setCategoryName(rs.getString("category_name"));
				category.setId(rs.getInt("id"));
				category.setImage(rs.getString("image"));
				category.setMenuId(rs.getInt("menu_id"));
				categoryList.add(category);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return categoryList;
	}

	/**
	 * Retrieve a list of categories by a given menu ID.
	 *
	 * @param menuId The ID of the menu to filter categories.
	 * @return A Set of Category objects representing categories for the specified menu.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public Set<Category> findCategoriesByMenuId(int menuId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Category> categoryList = new TreeSet<>();

		try {
			String query = "SELECT c.id, c.category_name, ci.image, ci.menu_id FROM categories c JOIN category_images ci "
					+ "ON c.id = ci.category_id WHERE ci.menu_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menuId); 
			rs = ps.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setCategoryName(rs.getString(CATEGORYNAME));
				category.setId(rs.getInt("id"));
				category.setImage(rs.getString("image"));
				category.setMenuId(rs.getInt("menu_id"));
				categoryList.add(category);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return categoryList;
	}
	
	/**
	 * Retrieve a list of active categories by menu ID.
	 *
	 * @param menuId The ID of the menu to filter categories.
	 * @return A Set of Category objects representing active categories for the specified menu.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public Set<Category> findAllActiveCategoriesByMenuId(int menuId) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Set<Category> categoryList = new TreeSet<>();
		
		try {
			String query = "SELECT c.id,c.category_name,cd.menu_id,ci.image FROM categories c JOIN category_images ci ON c.id = ci.category_id "
					+ "JOIN category_dishes cd ON c.id = cd.category_id WHERE ci.menu_id = ? AND cd.menu_id = ? AND cd.status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, menuId);
			ps.setInt(2, menuId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setCategoryName(rs.getString("category_name"));
				category.setImage(rs.getString("image"));
				category.setMenuId(rs.getInt("menu_id"));
				categoryList.add(category);
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return categoryList;
	}
	
	
	/**
	 * Retrieve a set of category names by menu ID.
	 *
	 * @param menuId The ID of the menu to filter category names.
	 * @return A Set of Strings containing category names for the specified menu.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public Set<String> findAllCategoryNamesByMenuId(int menu_id) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<String> categoryNames = new TreeSet<>();

		try {
			String query = "SELECT c.category_name FROM categories c JOIN category_images ci ON c.id = ci.category_id "
					+ "WHERE ci.menu_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menu_id);

			rs = ps.executeQuery();

			while (rs.next()) {
				categoryNames.add(rs.getString(CATEGORYNAME).trim().toLowerCase());
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return categoryNames;
	}

	/**
	 * Retrieve a category by its menu ID and category ID.
	 *
	 * @param menu_id The ID of the menu.
	 * @param category_id The ID of the category.
	 * @return A Category object representing the category with the specified menu and category ID.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public Category findCategoryByMenuIdAndCategoryId(int menu_id, int category_id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Category category = null;

		try {
			String query = "SELECT c.id, c.category_name, ci.image, ci.menu_id FROM categories c JOIN category_images ci "
					+ "ON c.id = ci.category_id WHERE ci.menu_id = ? AND ci.category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menu_id);
			ps.setInt(2, category_id);

			rs = ps.executeQuery();

			if (rs.next()) {
				category = new Category();
				category.setCategoryName(rs.getString(CATEGORYNAME));
				category.setId(rs.getInt("id"));
				category.setImage(rs.getString("image"));
				category.setMenuId(rs.getInt("menu_id"));
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return category;
	}

	/**
	 * Retrieve the ID of a category by its name.
	 *
	 * @param categoryName The name of the category.
	 * @return The ID of the category with the specified name.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public int findCategoryIdByCategoryName(String categoryName) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int id = -1;
		try {
			String query = "SELECT id FROM categories WHERE category_name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, categoryName.trim());
			rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return id;
	}

	/**
	 * Check if a category with a specific ID exists for a given menu.
	 *
	 * @param menuId The ID of the menu.
	 * @param categoryId The ID of the category to check.
	 * @throws DAOException If the category ID is not found for the menu or if there's a database error.
	 */
	public void isCategoryIdExistsForThatMenu(int menuId, int categoryId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT c.id FROM categories c JOIN category_images ci ON c.id = ci.category_id "
					+ "WHERE ci.menu_id = ? AND ci.category_id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query); 
			ps.setInt(1, menuId);
			ps.setInt(2, categoryId);

			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new DAOException("CategoryId not found");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
	/**
	 * Check if a category with a specific ID is valid.
	 *
	 * @param categoryId The ID of the category to check.
	 * @throws DAOException If the category ID is not found or if there's a database error.
	 */
	public void isCategoryIdIsValid(int categoryId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT id FROM categories WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query); 
			ps.setInt(1, categoryId);
			
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new DAOException("CategoryId not found");
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
	}

	/**
	 * Create a new category in the database.
	 *
	 * @param category The Category object to be created.
	 * @return The generated ID of the newly created category.
	 * @throws DAOException If a database error occurs during the creation.
	 */
	public int createCategory(Category category) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int generatedId = -1;

		try {
			String query = "INSERT INTO categories(category_name) VALUES(?)"; 
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, category.getCategoryName().trim());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return generatedId;
	}
	
	/**
	 * Retrieve the total price of dishes in a category by menu ID and category ID.
	 *
	 * @param menuId The ID of the menu.
	 * @param categoryId The ID of the category.
	 * @return The total price of dishes in the specified category and menu.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public int getTotalPriceOfTheCategoryByMenuIdAndCategoryId(int menuId, int categoryId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int totalPrice = -1;
		
		try {
			String query = "SELECT SUM(dp.price) FROM dish_price dp JOIN category_dishes cd ON cd.dish_id =  dp.dish_id "
					+ "WHERE dp.end_date IS NULL AND cd.menu_id = ? AND cd.category_id = ? AND cd.status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, menuId);
			ps.setInt(2, categoryId);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
	            totalPrice = rs.getInt(1); 
	        }
			
		}  catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return totalPrice;
	}
	
}
