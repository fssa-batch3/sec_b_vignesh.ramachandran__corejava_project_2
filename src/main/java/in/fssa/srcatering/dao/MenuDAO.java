package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Menu;
import in.fssa.srcatering.util.ConnectionUtil;

public class MenuDAO {

	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<Menu> findAll() throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Menu> menuList = new ArrayList<Menu>();

		try {
			String query = "SELECT * FROM menus";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setMenu_name(rs.getString("menu_name"));
				menu.setDescription(rs.getString("description"));
				menuList.add(menu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return menuList;
	}

	/**
	 * 
	 * @param menu_id
	 * @return
	 * @throws DAOException
	 */
	public Menu findById(int menu_id) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Menu newMenu = null;

		try {

			String query = "SELECT * FROM menus WHERE id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menu_id);

			rs = ps.executeQuery();

			if (rs.next()) {
				newMenu = new Menu();
				newMenu.setId(rs.getInt("id"));
				newMenu.setMenu_name(rs.getString("menu_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return newMenu;

	}

	/**
	 * 
	 * @param menu_id
	 * @throws DAOException
	 */
	public static void IsMenuIdIsValid(int menu_id) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM menus WHERE id =? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menu_id);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("MenuId not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

	}

}
