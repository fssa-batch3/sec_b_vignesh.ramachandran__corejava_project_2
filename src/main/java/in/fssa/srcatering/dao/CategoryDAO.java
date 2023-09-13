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

public class CategoryDAO {

	private static final String CATEGORYNAME = "category_name";

	/**
	 * 
	 * @return
	 * @throws DAOException
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
				category.setMenu_id(rs.getInt("menu_id"));
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
	 * 
	 * @param menuId
	 * @return
	 * @throws DAOException
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
				category.setMenu_id(rs.getInt("menu_id"));
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
	 * 
	 * @param menuId
	 * @return
	 * @throws DAOException
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
				category.setMenu_id(rs.getInt("menu_id"));
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
	 * Retrieves a list of all category names from the database.
	 *
	 * @return A List of String objects containing all category names.
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
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return categoryNames;
	}

	/**
	 * 
	 * @param menu_id
	 * @param category_id
	 * @return
	 * @throws DAOException
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
				category.setMenu_id(rs.getInt("menu_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return category;
	}

	/**
	 * 
	 * @param categoryName
	 * @return
	 * @throws DAOException
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

			ps.setString(1, categoryName);
			rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return id;
	}

	/**
	 * Checks whether a category with the specified ID exists in the database.
	 *
	 * @param categoryId The ID of the category to check.
	 * @throws DAOException If there's an issue with the database operation or if
	 *                      the category ID is not found.
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
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}
	
	
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
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
	}

	/**
	 * 
	 * @param category
	 * @return
	 * @throws DAOException
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

			ps.setString(1, category.getCategoryName());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}

			System.out.println("Category created sucessfully");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return generatedId;
	}
	
	/**
	 * 
	 * @param menuId
	 * @param categoryId
	 * @return
	 * @throws DAOException
	 */
	public int getTotalPriceOfTheCategoryByMenuIdAndCategoryId(int menuId, int categoryId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int totalPrice = -1;
		
		try {
			String query = "SELECT SUM(dp.price) FROM dish_price dp JOIN category_dishes cd ON cd.dish_id =  dp.dish_id "
					+ "WHERE dp.end_date IS NULL AND cd.menu_id = ? AND cd.category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, menuId);
			ps.setInt(2, categoryId);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
	            totalPrice = rs.getInt(1); 
	        }
			
		}  catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return totalPrice;
	}
	
}
