package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.model.Menu;
import in.fssa.srcatering.util.ConnectionUtil;

public class MenuDAO {
	
	public List<Menu> findAll(){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Menu> menuList = new ArrayList<Menu>();
				
		try {
			String query = "SELECT * FROM menus";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setMenu_name(rs.getString("menu_name"));
				menuList.add(menu);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return menuList;	
	}
	
	
	public Menu findById(int menu_id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Menu newMenu = null;
		
		try {
			
			String query = "SELECT * FROM category_dish WHERE menu_id = ? && status =1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, menu_id);
			
			rs = ps.executeQuery();
			
			if(!rs.next()) {
				throw new RuntimeException("menu_id not found");
			}
			
			query = "SELECT * FROM menus WHERE id =?";
			ps = con.prepareStatement(query);
			
			ps.setInt(1, menu_id);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				newMenu = new Menu();
				newMenu.setId(rs.getInt("menu_id"));
				newMenu.setMenu_name(rs.getString("menu_name"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return newMenu;
		
	}

}
