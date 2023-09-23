package in.fssa.srcatering.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.Review;
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.Logger;

public class ReviewDAO {
	
	/**
	 * 
	 * @param review
	 * @throws DAOException
	 */
	public void create(Review review) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO reviews(user_id, order_id, menu_id, category_id, star, feedback) VALUES (?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, review.getUserId());
			ps.setInt(2, review.getOrderId());
			ps.setInt(3, review.getMenuId());
			ps.setInt(4, review.getCategoryId());
			ps.setInt(5, review.getStar());
			ps.setString(6, review.getFeedback());
			
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
	 * @param orderId
	 * @param menuId
	 * @param categoryId
	 * @return
	 * @throws DAOException
	 */
	public Review findReviewByOrderIdAndMenuIdAndCategoryId(int orderId, int menuId, int categoryId) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Review review = null;
		
		try {
			String query = "SELECT id, user_id, order_id, menu_id, category_id, star, feedback FROM reviews WHERE order_id = ? AND menu_id = ? "
					+ "AND category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, orderId);
			ps.setInt(2, menuId);
			ps.setInt(3, categoryId);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				review = new Review();
				review.setId(rs.getInt("id"));
				review.setUserId(rs.getInt("user_id"));
				review.setOrderId(rs.getInt("order_id"));
				review.setMenuId(rs.getInt("menu_id"));
				review.setCategoryId(rs.getInt("category_id"));
				review.setStar(rs.getInt("star"));
				review.setFeedback(rs.getString("feedback"));
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return review;
	}
	
	
	/**
	 * 
	 * @param menuId
	 * @param categoryId
	 * @return
	 * @throws DAOException
	 */
	public List<Review> findAllReviewsByMenuIdAndCategoryId(int menuId, int categoryId) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Review> reviewList = new ArrayList<>();
		
		try {
			String query = "SELECT id, user_id, order_id, menu_id, category_id, star, feedback FROM reviews WHERE menu_id = ?"
					+ "AND category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menuId);
			ps.setInt(2, categoryId);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Review review = new Review();
				review.setId(rs.getInt("id"));
				review.setUserId(rs.getInt("user_id"));
				review.setOrderId(rs.getInt("order_id"));
				review.setMenuId(rs.getInt("menu_id"));
				review.setCategoryId(rs.getInt("category_id"));
				review.setStar(rs.getInt("star"));
				review.setFeedback(rs.getString("feedback"));
				reviewList.add(review);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return reviewList;
	
	}

}
