package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.Dish;
import in.fssa.srcatering.model.QuantityUnit;
import in.fssa.srcatering.util.ConnectionUtil;

public class DishDAO {

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
	
	
	public Dish findByDishId(int dish_id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Dish dish = null;
		
		try {
			dish = new Dish();
			String query = "SELECT * FROM dishes WHERE id = ?";
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
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return dish;
	}
	
	
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
