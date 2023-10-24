package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.CaterApproval;
import in.fssa.srcatering.model.OrderProduct;
import in.fssa.srcatering.model.OrderStatus;
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.Logger;

public class OrderProductDAO {

	/**
	 * Create one or more order products in the database for a specific order. Each order product corresponds to a dish in the order.
	 *
	 * @param orderProduct The OrderProduct object representing the order product(s) to be created.
	 * @throws DAOException If a database error occurs during the creation.
	 */
	public void create(OrderProduct orderProduct) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "INSERT INTO order_products(order_id, dish_id, price_id, no_of_guest, delivery_date,"
					+ "menu_id, category_id) VALUES(?,?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			// Parse the date string into a LocalDate object
			java.sql.Date deliveryDate = java.sql.Date.valueOf(orderProduct.getDeliveryDate());

			Map<Integer, Integer> dishIdPriceIdMap = orderProduct.getDishIdPriceIdMap();

			for (Map.Entry<Integer, Integer> entry : dishIdPriceIdMap.entrySet()) {

				int dishId = entry.getKey();
				int priceId = entry.getValue();

				ps.setInt(1, orderProduct.getOrderId());
				ps.setInt(2, dishId);
				ps.setInt(3, priceId);
				ps.setInt(4, orderProduct.getNoOfGuest());
				ps.setDate(5, deliveryDate);
				ps.setInt(6, orderProduct.getMenuId());
				ps.setInt(7, orderProduct.getCategoryId());

				ps.executeUpdate();
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Update the order status and optionally set the cancel date and reason for a specific order product within an order.
	 *
	 * @param status The new OrderStatus to set.
	 * @param orderId The ID of the order.
	 * @param menuId The ID of the menu related to the order product.
	 * @param categoryId The ID of the category related to the order product.
	 * @param cancelDateTime The cancellation date (optional, set to null if not canceling).
	 * @param cancelReason The reason for the cancellation (optional, set to null if not canceling).
	 * @throws DAOException If a database error occurs during the update.
	 */
	public void updateOrderStatusAndCancelDate(OrderStatus status, int orderId, int menuId, int categoryId,
		LocalDateTime cancelDateTime, String cancelReason) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query;

			if (status == OrderStatus.CANCELLED) {
				
				// Update order status and set cancel date
				query = "UPDATE order_products SET order_status = ?, cancel_date = ?, cancel_reason = ? WHERE order_id = ? AND menu_id = ? AND category_id = ?";
				con = ConnectionUtil.getConnection();
				ps = con.prepareStatement(query);
				ps.setString(1, status.name());
				java.sql.Timestamp sqlcanceltime = java.sql.Timestamp.valueOf(cancelDateTime);
				ps.setTimestamp(2, sqlcanceltime);
				ps.setString(3, cancelReason);
				ps.setInt(4, orderId);
				ps.setInt(5, menuId);
				ps.setInt(6, categoryId);
				
			} else {
				// Update order status only
				query = "UPDATE order_products SET order_status = ? WHERE order_id = ? AND menu_id = ? AND category_id = ?";
				con = ConnectionUtil.getConnection();
				ps = con.prepareStatement(query);
				ps.setString(1, status.name());
				ps.setInt(2, orderId);
				ps.setInt(3, menuId);
				ps.setInt(4, categoryId);
				
			}

			ps.executeUpdate();

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * Update the caterer's approval status and reason (if rejected) for a specific order product within an order.
	 *
	 * @param caterApproval The new CaterApproval status to set.
	 * @param reason The reason for rejection (if rejected, otherwise null).
	 * @param orderId The ID of the order.
	 * @param menuId The ID of the menu related to the order product.
	 * @param categoryId The ID of the category related to the order product.
	 * @throws DAOException If a database error occurs during the update.
	 */
	public void updateCaterApprovalByOrderIdAndMenuIdAndCategoryId(CaterApproval caterApproval, String reason,
			int orderId, int menuId, int categoryId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query;

			if (caterApproval == CaterApproval.REJECTED) {
				// Update order status and set cancel date
				query = "UPDATE order_products SET cater_approval = ?, reject_reason = ? WHERE order_id = ? AND menu_id = ? AND category_id =?";
			} else {
				// Update order status only
				query = "UPDATE order_products SET cater_approval = ? WHERE order_id = ? AND menu_id = ? AND category_id =?";
			}

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, caterApproval.name());

			if (caterApproval == CaterApproval.REJECTED) {
				ps.setString(2, reason);
				ps.setInt(3, orderId);
				ps.setInt(4, menuId);
				ps.setInt(5, categoryId);
			} else {
				ps.setInt(2, orderId);
				ps.setInt(3, menuId);
				ps.setInt(4, categoryId);
			}

			ps.executeUpdate();

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Retrieve a set of all order products associated with a specific order.
	 *
	 * @param orderId The ID of the order to retrieve order products from.
	 * @return A Set of OrderProduct objects representing all order products of the specified order.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public Set<OrderProduct> findAllOrderProductsByOrderId(int orderId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<OrderProduct> orderProductList = new HashSet<>();

		Map<Integer, Integer> dishIdPriceIdMap = new HashMap<>();

		try {
			String query = "SELECT order_id, dish_id, price_id, no_of_guest, delivery_date, order_status, menu_id, category_id, "
					+ "cancel_date,cancel_reason FROM order_products WHERE order_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, orderId);
			rs = ps.executeQuery();

			while (rs.next()) {
				OrderProduct orderProduct = new OrderProduct();
				orderProduct.setOrderId(rs.getInt("order_id"));

				dishIdPriceIdMap.put(rs.getInt("dish_id"), rs.getInt("price_id"));
				orderProduct.setDishIdPriceIdMap(dishIdPriceIdMap);

				orderProduct.setNoOfGuest(rs.getInt("no_of_guest"));

				// Convert SQL Date to LocalDate
				Date sqlDate = rs.getDate("delivery_date");
				orderProduct.setDeliveryDate(sqlDate.toLocalDate());

				String statusStr = rs.getString("order_status");
				OrderStatus orderStatus = OrderStatus.valueOf(statusStr);
				orderProduct.setOrderStatus(orderStatus);

				orderProduct.setMenuId(rs.getInt("menu_id"));
				orderProduct.setCategoryId(rs.getInt("category_id"));

				if (rs.getTimestamp("cancel_date") == null) {
					orderProduct.setCancelDate(null);
				} else {
					// Convert SQL Timestamp to LocalDateTime
					Timestamp timestamp = rs.getTimestamp("cancel_date");
					LocalDateTime cancelDate = timestamp.toLocalDateTime();

					orderProduct.setCancelDate(cancelDate);
					orderProduct.setCancelReason(rs.getString("cancel_reason"));
				}

				orderProductList.add(orderProduct);

			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return orderProductList;
	}

	/**
	 * Retrieve an order product by its order ID, menu ID, and category ID.
	 *
	 * @param orderId The ID of the order.
	 * @param menuId The ID of the menu related to the order product.
	 * @param categoryId The ID of the category related to the order product.
	 * @return The OrderProduct object representing the requested order product.
	 * @throws DAOException If a database error occurs during the retrieval or if the order product is not found.
	 */
	public OrderProduct findOrderProductByOrderIdAndMenuIdAndCategoryId(int orderId, int menuId, int categoryId)
			throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Map<Integer, Integer> dishIdPriceIdMap = new HashMap<>();

		OrderProduct orderProduct = null;

		try {
			String query = "SELECT order_id, dish_id, price_id, no_of_guest, delivery_date, order_status, menu_id, category_id, "
					+ "cancel_date, cancel_reason, cater_approval, reject_reason FROM order_products WHERE order_id = ? AND menu_id = ? AND category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, orderId);
			ps.setInt(2, menuId);
			ps.setInt(3, categoryId);

			rs = ps.executeQuery();

			while (rs.next()) {
				orderProduct = new OrderProduct();
				orderProduct.setOrderId(rs.getInt("order_id"));
				dishIdPriceIdMap.put(rs.getInt("dish_id"), rs.getInt("price_id"));
				orderProduct.setDishIdPriceIdMap(dishIdPriceIdMap);

				orderProduct.setNoOfGuest(rs.getInt("no_of_guest"));

				// Convert SQL Date to LocalDate
				Date sqlDate = rs.getDate("delivery_date");
				orderProduct.setDeliveryDate(sqlDate.toLocalDate());

				String statusStr = rs.getString("order_status");
				OrderStatus orderStatus = OrderStatus.valueOf(statusStr);
				orderProduct.setOrderStatus(orderStatus);

				orderProduct.setMenuId(rs.getInt("menu_id"));
				orderProduct.setCategoryId(rs.getInt("category_id"));

				if (rs.getTimestamp("cancel_date") == null) {
					orderProduct.setCancelDate(null);
				} else {
					// Convert SQL Timestamp to LocalDateTime
					Timestamp timestamp = rs.getTimestamp("cancel_date");
					LocalDateTime cancelDate = timestamp.toLocalDateTime();

					orderProduct.setCancelDate(cancelDate);
					orderProduct.setCancelReason(rs.getString("cancel_reason"));
				}

				String caterApprovalStr = rs.getString("cater_approval");
				CaterApproval caterApproval = CaterApproval.valueOf(caterApprovalStr);
				orderProduct.setCaterApproval(caterApproval);

				orderProduct.setRejectReason(rs.getString("reject_reason"));
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return orderProduct;
	}

	/**
	 * 
	 * @param orderId
	 * @return
	 * @throws DAOException
	 */
//	public Map<Integer, Integer> findOrderedMenuIdAndCategoryIdByOrderId(int orderId) throws DAOException {
//		
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		Map<Integer, Integer> menuIdCategoryIdMap = new HashMap<>();
//		
//		try {
//			String query = "SELECT menu_id, category_id FROM order_products WHERE order_id = ?";
//			con = ConnectionUtil.getConnection();
//			ps = con.prepareStatement(query);
//			
//			ps.setInt(1, orderId);
//			
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				
//				menuIdCategoryIdMap.put(rs.getInt("menu_id"), rs.getInt("category_id"));
//			}
//			
//		} catch (SQLException e) {
//			Logger.error(e);
//			throw new DAOException(e.getMessage());
//		} finally {
//			ConnectionUtil.close(con, ps, rs);
//		}
//		return menuIdCategoryIdMap;
//
//	}

	/**
	 * Retrieve a set of ordered menu IDs and category IDs for a specific order.
	 *
	 * @param orderId The ID of the order to retrieve ordered menus and categories from.
	 * @return A Set of Map Entries representing menu ID and category ID pairs.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public Set<Map.Entry<Integer, Integer>> findOrderedMenuIdAndCategoryIdByOrderId(int orderId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Map.Entry<Integer, Integer>> menuIdCategoryIdList = new HashSet<>();

		try {
			String query = "SELECT menu_id, category_id FROM order_products WHERE order_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, orderId);

			rs = ps.executeQuery();

			while (rs.next()) {
				int menuId = rs.getInt("menu_id");
				int categoryId = rs.getInt("category_id");
				menuIdCategoryIdList.add(new AbstractMap.SimpleEntry<>(menuId, categoryId));
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return menuIdCategoryIdList;
	}

	/**
	 * Retrieve a list of all order IDs in the database.
	 *
	 * @return A List of order IDs representing all orders in the database.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public List<Integer> findAllOrderIds() throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Integer> orderIds = new ArrayList<>();

		try {
			String query = "SELECT id FROM orders";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {
				orderIds.add(rs.getInt("id"));
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return orderIds;

	}

}
