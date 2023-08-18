package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.interfacee.UserInterface;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.util.ConnectionUtil;

public class UserDAO implements UserInterface {

	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	@Override
	public List<User> findAll() throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<User> userList = new ArrayList<User>();

		try {

			String query = "SELECT * FROM users WHERE status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				User newUser = new User();
				newUser.setId(rs.getInt("id"));
				newUser.setName(rs.getString("name"));
				newUser.setEmail(rs.getString("email"));
				newUser.setPhone_number(rs.getLong("phone_number"));
				newUser.setPassword(rs.getString("password"));
				newUser.setStatus(rs.getBoolean("status"));
				userList.add(newUser);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return userList;
	}

	/**
	 * 
	 * @param new_User
	 * @throws DAOException
	 */
	@Override
	public void create(User newUser) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO users(name, email, phone_number, password) VALUES (?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, newUser.getName().trim());
			ps.setString(2, newUser.getEmail().trim());
			ps.setLong(3, newUser.getPhone_number());
			ps.setString(4, newUser.getPassword());

			ps.executeUpdate();

			System.out.println("User has been created sucessfully");

		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate entry")) {
				throw new DAOException("Duplicate constraint");
			} else {
				e.printStackTrace();
				throw new DAOException(e.getMessage());
			}
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @param id
	 * @param newUser
	 * @throws DAOException
	 */
	@Override
	public void update(int id, User newUser) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "UPDATE users SET name=?, password=? WHERE id =?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getPassword());
			ps.setInt(3, id);

			int rowUpdated = ps.executeUpdate();

			if (rowUpdated > 0) {
				System.out.println("User with ID " + id + " updated successfully.");
			} else {
				System.out.println("No user found with ID " + id + ". Nothing updated.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}

	/**
	 * 
	 * @throws DAOException
	 */
	@Override
	public void delete(int id) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "UPDATE users SET status =0 WHERE id = ? AND status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("User with ID " + id + " has been deactivated.");
			} else {
				System.out.println("No user found with ID " + id + ". Nothing changed.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

	}

	/**
	 * 
	 * @param userId
	 * @throws DAOException
	 */
	public void isIdAlreadyExists(int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM users WHERE status = 1 && id =? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, userId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("Invaid UserId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

	}

	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	@Override
	public User findById(int id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT * FROM users WHERE status = 1 && id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setPhone_number(rs.getLong("phone_number"));
				user.setStatus(rs.getBoolean("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		System.out.println(user);
		return user;
	}

	/**
	 * 
	 * @param email
	 * @throws DAOException
	 */
	public void isEmailAlreadyExists(String email) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM users WHERE email = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				throw new DAOException("Email already exists");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

	}

	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	@Override
	public User findByEmail(String email) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT * FROM users WHERE status = 1 AND email = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPhone_number(rs.getLong("phone_number"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getBoolean("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
		System.out.println(user);
		return user;
	}

	@Override
	public int count() {

		return 0;
	}

}
