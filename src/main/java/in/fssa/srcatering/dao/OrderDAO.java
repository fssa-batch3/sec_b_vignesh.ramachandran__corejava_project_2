package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.Order;
import in.fssa.srcatering.util.ConnectionUtil;

public class OrderDAO {

	/**
	 * 
	 * @param order
	 * @throws DAOException
	 */
	public void create(Order order) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "INSERT INTO orders(user_id, menu_id, category_id, price_id, order_date, delivery_date,"
					+ "order_status,no_of_guest,total_cost VALUES(?,?,?,?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, order.getUser_id());
			ps.setInt(2, order.getMenu_id());
			ps.setInt(3, order.getCategory_id());
			
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}

}
