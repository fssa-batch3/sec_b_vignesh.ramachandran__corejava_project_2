package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.Menu;
import in.fssa.srcatering.util.ConnectionUtil;

public class MenuDAO {

	/**
     * Retrieves a list of all menus from the 'menus' table.
     *
     * @return A list of Menu objects representing all menus.
     * @throws DAOException If there's an issue with the database operation.
     */
	public List<Menu> findAll() throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Menu> menuList = new ArrayList<>();

		try {
			String query = "SELECT id, menu_name, description FROM menus";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setMenuName(rs.getString("menu_name"));
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
     * Retrieves a menu based on the provided menu ID from the 'menus' table.
     *
     * @param menuId The ID of the menu to retrieve.
     * @return The Menu object with the specified menu ID.
     * @throws DAOException If there's an issue with the database operation.
     */
	public Menu findById(int menuId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Menu newMenu = null;

		try {

			String query = "SELECT id, menu_name, description FROM menus WHERE id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menuId);

			rs = ps.executeQuery();

			if (rs.next()) {
				newMenu = new Menu();
				newMenu.setId(rs.getInt("id"));
				newMenu.setMenuName(rs.getString("menu_name"));
				newMenu.setDescription(rs.getString("description"));
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
     * Checks whether a menu ID exists in the 'menus' table.
     *
     * @param menuId The ID of the menu to check.
     * @throws DAOException If there's an issue with the database operation or if the menu ID is not found.
     */
	public static void isMenuIdIsValid(int menuId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM menus WHERE id =? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menuId);
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
