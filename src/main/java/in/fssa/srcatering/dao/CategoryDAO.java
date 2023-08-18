package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Category;
import in.fssa.srcatering.model.Menu;
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.IntUtil;

public class CategoryDAO {

	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<Category> findAll() throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Category> categoryList = new ArrayList<Category>();

		try {
			String query = "SELECT * FROM categories";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setCategory_name(rs.getString("category_name"));
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
	 * 
	 * @param category_id
	 * @return
	 * @throws DAOException
	 */
	public Category findById(int category_id) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Category newCategory = null;

		try {

			String query = "SELECT * FROM categories WHERE id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, category_id);

			rs = ps.executeQuery();

			if (rs.next()) {
				newCategory = new Category();
				newCategory.setId(rs.getInt("id"));
				newCategory.setCategory_name(rs.getString("category_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		System.out.println(newCategory);
		return newCategory;

	}
	
	
	/**
	 * 
	 * @param category_id
	 * @throws DAOException
	 */
	public void isCategoryIdIsValid(int category_id)throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM categories WHERE id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, category_id);
			
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
	
	
//	public Set<Category> findByMenuIdCategoryId(int menu_id, int category_id) throws ValidationException{
//		
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		Set<Category> categoryList = new HashSet()<Category>();
//		try {
//			
//			String query = "SELECT * FROM CategoryDish WHERE menu_id= ?"
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		
//		return null;
//		
//	}

}
