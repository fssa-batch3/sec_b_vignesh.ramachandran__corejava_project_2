package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.Menu;
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.Logger;

public class MenuDAO {

	private static final String MENUNAME = "menu_name";

	/**
	 * Retrieves a list of all menus from the 'menus' table.
	 *
	 * @return A list of Menu objects representing all menus.
	 * @throws DAOException If there's an issue with the database operation.
	 */
	public Set<Menu> findAllActiveMenus() throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Menu> menuList = new TreeSet<>(); 
 
		try {
			String query = "SELECT m.id, m.menu_name, m.description,m.image,cd.status FROM menus m JOIN category_dishes cd "
					+ "ON m.id = cd.menu_id WHERE cd.status=1 ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery(); 

			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setMenuName(rs.getString(MENUNAME));
				menu.setDescription(rs.getString("description"));
				menu.setImage(rs.getString("image"));
				menu.setStatus(rs.getBoolean("status"));
				menuList.add(menu);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return menuList;
	}

	public Set<Menu> findAllMenus() throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Menu> menuList = new TreeSet<>();

		try {
			String query = "SELECT id, menu_name, description, image FROM menus";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setMenuName(rs.getString(MENUNAME));
				menu.setDescription(rs.getString("description"));
				menu.setImage(rs.getString("image"));
				menuList.add(menu);
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return menuList;
	}

	/**
	 * Creates a new menu in the database using the provided Menu object.
	 *
	 * @param menu The Menu object representing the menu to be created.
	 * @throws DAOException If a database error occurs during the creation.
	 */
	public void create(Menu menu) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO menus(menu_name, description, image) VALUES(?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, menu.getMenuName().trim());
			ps.setString(2, menu.getDescription().trim());
			ps.setString(3, menu.getImage().trim());

			ps.executeUpdate();

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * Updates an existing menu in the database using the provided Menu object.
	 *
	 * @param menu The Menu object representing the menu with updated information.
	 * @throws DAOException If a database error occurs during the update.
	 */
	public void update(Menu menu) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE menus SET description = ?, image = ? WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, menu.getDescription().trim());
			ps.setString(2, menu.getImage().trim());
			ps.setInt(3, menu.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
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

			String query = "SELECT id, menu_name,image, description FROM menus WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, menuId);

			rs = ps.executeQuery();

			if (rs.next()) {
				newMenu = new Menu();
				newMenu.setId(rs.getInt("id"));
				newMenu.setMenuName(rs.getString(MENUNAME));
				newMenu.setDescription(rs.getString("description"));
				newMenu.setImage(rs.getString("image"));
			}

		} catch (SQLException e) {
			Logger.error(e);
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
	 * @throws DAOException If there's an issue with the database operation or if
	 *                      the menu ID is not found.
	 */
	public void isMenuIdIsValid(int menuId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM menus WHERE id=?";
			con = ConnectionUtil.getConnection(); 
			ps = con.prepareStatement(query);

			ps.setInt(1, menuId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("MenuId not found");
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	/**
	 * Retrieves a list of all menu names from the database.
	 *
	 * @return A List of String objects containing all menu names.
	 * @throws DAOException If a database error occurs during the retrieval.
	 */
	public List<String> findAllMenuNames() throws DAOException {
		List<String> menuNames = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT menu_name FROM menus";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {
				menuNames.add(rs.getString(MENUNAME).trim().toLowerCase());
			}

		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return menuNames;

	}

}
