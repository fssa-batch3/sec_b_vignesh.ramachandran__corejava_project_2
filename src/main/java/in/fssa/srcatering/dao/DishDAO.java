package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.model.QuantityUnit;
import in.fssa.srcatering.service.DishPriceService;
import in.fssa.srcatering.util.ConnectionUtil;

public class DishDAO {

	/**
     * Creates a new dish entry in the 'dishes' table.
     *
     * @param dish_name The name of the dish.
     * @param quantity The quantity of the dish.
     * @param quantity_unit The unit of measurement for the quantity.
     * @return The generated ID of the newly created dish.
     * @throws DAOException If there's an issue with the database operation.
     */
	public int create(String dish_name, int quantity, QuantityUnit quantity_unit) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int generatedId = -1;

		try {
			String query = "INSERT INTO dishes(dish_name, quantity, quantity_unit) VALUES(?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dish_name);
			ps.setInt(2, quantity);
			ps.setString(3, quantity_unit.name());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps,rs);
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
			ps.setString(2, dish.getQuantity_unit().name());
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
     * @param dish_id The ID of the dish to check.
     * @throws DAOException If there's an issue with the database operation or if the dish ID is not found.
     */
	public void isDishIdIsValid(int dish_id) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM dishes WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dish_id);
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
     * Retrieves a Dish object based on the provided dish ID by joining the 'dishes', 'category_dish', and 'dish_price' tables.
     *
     * @param dish_id The ID of the dish to retrieve.
     * @return The Dish object with the specified dish ID.
     * @throws DAOException If there's an issue with the database operation or data validation.
     * @throws ValidationException If there's an issue with data validation.
     */
	public Dish findByDishId(int dish_id) throws DAOException, ValidationException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Dish dish = null;
		
		try {
			dish = new Dish();
			String query = "SELECT * FROM dishes d JOIN category_dish cd ON d.id = cd.dish_id WHERE d.id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dish_id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				dish.setId(rs.getInt("id"));
				dish.setDish_name(rs.getString("dish_name"));
				dish.setQuantity(rs.getInt("quantity"));
				
				String quantityUnitString = rs.getString("quantity_unit");
				QuantityUnit quantityUnit = QuantityUnit.valueOf(quantityUnitString);
				
				dish.setQuantity_unit(quantityUnit);
				dish.setMenu_id(rs.getInt("menu_id"));
				dish.setCategory_id(rs.getInt("category_id"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return dish;
	}
	
	/**
     * Retrieves a list of Dish objects containing information about all active dishes.
     *
     * @return A list of Dish objects representing all active dishes.
     * @throws DAOException If there's an issue with the database operation.
     */
	public List<Dish> findAllDishes() throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Dish> dishes = new ArrayList<Dish>();
		
		try {
			String query = "SELECT d.id, d.dish_name, d.quantity, d.quantity_unit, cd.menu_id, cd.category_id, dp.price "
					+ "FROM dishes d JOIN category_dish cd ON d.id = cd.dish_id "
					+ "JOIN dish_price dp ON d.id = dp.dish_id WHERE cd.status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Dish dish = new Dish();
				dish.setId(rs.getInt("id"));
				dish.setMenu_id(rs.getInt("menu_id"));
				dish.setCategory_id(rs.getInt("category_id"));
				dish.setDish_name(rs.getString("dish_name"));
				dish.setQuantity(rs.getInt("quantity"));
				
				String quantityUnitString = rs.getString("quantity_unit");
				QuantityUnit quantityUnit = QuantityUnit.valueOf(quantityUnitString);
				
				dish.setQuantity_unit(quantityUnit);
				dish.setDish_price(rs.getInt("price"));
				
				dishes.add(dish);
				//System.out.println("sr"+dish);
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return dishes;
	}
	
	


//	public List<Integer> findByDishName(String dish_name){
//		
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		List<Integer> dishIds = new ArrayList<Integer>();
//		
//		try {
//			String query = "SELECT * FROM dishes WHERE dish_name = ?";
//			con = ConnectionUtil.getConnection();
//			ps = con.prepareStatement(query);
//			ps.setString(1, dish_name);
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				dishIds.add(rs.getInt("id"));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			throw new RuntimeException(e);
//		}catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}finally {
//			ConnectionUtil.close(con, ps, rs);
//		}
//		return dishIds;
//	}

}
