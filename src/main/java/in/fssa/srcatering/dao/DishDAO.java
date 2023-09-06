package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.model.QuantityUnit;
import in.fssa.srcatering.util.ConnectionUtil;

public class DishDAO {

	/**
	 * Creates a new dish entry in the 'dishes' table.
	 *
	 * @param dishName     The name of the dish.
	 * @param quantity     The quantity of the dish.
	 * @param quantityUnit The unit of measurement for the quantity.
	 * @return The generated ID of the newly created dish.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public int create(String dishName, int quantity, QuantityUnit quantityUnit) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int generatedId = -1;

		try {
			String query = "INSERT INTO dishes(dish_name, quantity, quantity_unit) VALUES(?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dishName);
			ps.setInt(2, quantity);
			ps.setString(3, quantityUnit.name());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return generatedId;
	}

	/**
	 * Updates the quantity and quantity unit of a dish in the 'dishes' table.
	 *
	 * @param dish The Dish object containing updated information.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public void update(Dish dish) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE dishes SET quantity = ?, quantity_unit =? WHERE id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, dish.getQuantity());
			ps.setString(2, dish.getQuantityUnit().name());
			ps.setInt(3, dish.getId());
			ps.executeUpdate();
			System.out.println("Dish updated sucessfully");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Checks whether a dish ID exists in the 'dishes' table.
	 *
	 * @param dishId The ID of the dish to check.
	 * @throws DAOException If there's an issue with the database operation or if
	 *                      the dish ID is not found.
	 */
	public void isDishIdIsValid(int dishId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM dishes WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dishId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("DishId not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

	}

	/**
	 * Retrieves a Dish object based on the provided dish ID by joining the
	 * 'dishes', 'category_dish', and 'dish_price' tables.
	 *
	 * @param dishId The ID of the dish to retrieve.
	 * @return The Dish object with the specified dish ID.
	 * @throws DAOException        If there's an issue with the database operation
	 *                             or data validation.
	 * @throws ValidationException If there's an issue with data validation.
	 */
	public Dish findByDishId(int dishId) throws DAOException, ValidationException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Dish dish = null;

		try {
			dish = new Dish();
			String query = "SELECT d.id, d.dish_name, d.quantity, d.quantity_unit, cd.menu_id, cd.category_id,cd.status,dp.price "
					+ " FROM dishes d JOIN category_dishes cd ON d.id = cd.dish_id "
					+ "JOIN dish_price dp ON d.id = dp.dish_id WHERE d.id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dishId);

			rs = ps.executeQuery();

			if (rs.next()) {

				dish.setId(rs.getInt("id"));
				dish.setDishName(rs.getString("dish_name"));
				dish.setQuantity(rs.getInt("quantity"));

				String quantityUnitString = rs.getString("quantity_unit");
				QuantityUnit quantityUnit = QuantityUnit.valueOf(quantityUnitString);

				dish.setQuantityUnit(quantityUnit);
				dish.setMenuId(rs.getInt("menu_id"));
				dish.setCategoryId(rs.getInt("category_id"));
				dish.setStatus(rs.getInt("status"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return dish;
	}

	/**
	 * Retrieves a list of Dish objects containing information about all active
	 * dishes.
	 *
	 * @return A list of Dish objects representing all active dishes.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public Set<Dish> findAllDishesByMenuIdCategoryId(int menuId, int categoryId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Dish> dishes = new TreeSet<>();

		try {
			String query = "SELECT d.id, d.dish_name, d.quantity, d.quantity_unit, cd.menu_id, cd.category_id, dp.price,cd.status "
					+ "FROM dishes d JOIN category_dishes cd ON d.id = cd.dish_id "
					+ "JOIN dish_price dp ON d.id = dp.dish_id WHERE dp.end_date IS NULL AND cd.menu_id=? AND cd.category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menuId);
			ps.setInt(2, categoryId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Dish dish = new Dish();
				dish.setId(rs.getInt("id"));
				dish.setMenuId(rs.getInt("menu_id"));
				dish.setCategoryId(rs.getInt("category_id"));
				dish.setDishName(rs.getString("dish_name"));
				dish.setQuantity(rs.getInt("quantity"));

				String quantityUnitString = rs.getString("quantity_unit");
				QuantityUnit quantityUnit = QuantityUnit.valueOf(quantityUnitString);

				dish.setQuantityUnit(quantityUnit);
				dish.setDishPrice(rs.getInt("price"));
				dish.setStatus(rs.getInt("status"));

				dishes.add(dish);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return dishes;
	}

	/**
	 * 
	 * @param menuId
	 * @param categoryId
	 * @return
	 * @throws DAOException
	 */
	public Set<Dish> findAllActiveDishesByMenuIdAndCategoryId(int menuId, int categoryId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Dish> dishes = new TreeSet<>();

		try {
			String query = "SELECT d.id, d.dish_name, d.quantity, d.quantity_unit, cd.menu_id, cd.category_id, dp.price,cd.status "
					+ "FROM dishes d JOIN category_dishes cd ON d.id = cd.dish_id "
					+ "JOIN dish_price dp ON d.id = dp.dish_id WHERE dp.end_date IS NULL AND cd.menu_id=? AND cd.category_id = ? AND cd.status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menuId);
			ps.setInt(2, categoryId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Dish dish = new Dish();
				
				dish.setId(rs.getInt("id"));
				dish.setMenuId(rs.getInt("menu_id"));
				dish.setCategoryId(rs.getInt("category_id"));
				dish.setDishName(rs.getString("dish_name"));
				dish.setQuantity(rs.getInt("quantity"));

				String quantityUnitString = rs.getString("quantity_unit");
				QuantityUnit quantityUnit = QuantityUnit.valueOf(quantityUnitString);

				dish.setQuantityUnit(quantityUnit);
				dish.setDishPrice(rs.getInt("price"));
				dish.setStatus(rs.getInt("status"));

				dishes.add(dish);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return dishes;
	}

	/**
	 * Deletes a dish from the database based on the provided dish ID.
	 *
	 * @param dishId The ID of the dish to be deleted.
	 * @throws DAOException If a database error occurs during the deletion.
	 */
	void deleteByDishId(int dishId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "DELETE FROM dishes WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, dishId);

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Dish deleted successfully");
			} else {
				System.out.println("No rows deleted");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

}
