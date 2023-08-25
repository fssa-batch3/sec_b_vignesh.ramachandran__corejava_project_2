package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.interfaces.UserInterface;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.util.ConnectionUtil;

public class UserDAO implements UserInterface {
	
	
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_EMAIL = "email";
	private static final String COLUMN_PASSWORD = "password";
	private static final String COLUMN_PHONENUMBER = "phone_number";
	private static final String COLUMN_STATUS = "status";
	private static final String COLUMN_ID = "id";

	/**
     * Retrieves a list of all active users from the 'users' table.
     *
     * @return A list of User objects representing all active users.
     * @throws DAOException If there's an issue with the database operation.
     */
	@Override
	public List<User> findAll() throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<User> userList = new ArrayList<>();

		try {

			String query = "SELECT id, name, email, phone_number, password, status FROM users WHERE status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				User newUser = new User();
				newUser.setId(rs.getInt(COLUMN_ID));
				newUser.setName(rs.getString(COLUMN_NAME));
				newUser.setEmail(rs.getString(COLUMN_EMAIL));
				newUser.setPhoneNumber(rs.getLong(COLUMN_PHONENUMBER));
				newUser.setPassword(rs.getString(COLUMN_PASSWORD));
				newUser.setStatus(rs.getBoolean(COLUMN_STATUS));
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
     * Creates a new user in the 'users' table.
     *
     * @param newUser The User object containing user details to be created.
     * @throws DAOException If there's an issue with the database operation.
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
			ps.setLong(3, newUser.getPhoneNumber());
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
     * Updates an existing user's information in the 'users' table.
     *
     * @param id      The ID of the user to be updated.
     * @param newUser The User object containing updated user details.
     * @throws DAOException If there's an issue with the database operation.
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
     * Deactivates a user by changing its status to 0 in the 'users' table.
     *
     * @param id The ID of the user to be deactivated.
     * @throws DAOException If there's an issue with the database operation.
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
     * Checks whether a user ID exists in the 'users' table and is active.
     *
     * @param userId The ID of the user to check.
     * @throws DAOException If there's an issue with the database operation or if the user ID is not valid.
     */
	public void isIdAlreadyExists(int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id FROM users WHERE status = 1 && id =? ";
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
     * Retrieves an active user based on the provided user ID from the 'users' table.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object with the specified user ID.
     * @throws DAOException If there's an issue with the database operation.
     */
	@Override
	public User findById(int id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT id, name, email, phone_number, password, status FROM users WHERE status = 1 && id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(COLUMN_ID));
				user.setName(rs.getString(COLUMN_NAME));
				user.setEmail(rs.getString(COLUMN_EMAIL));
				user.setPassword(rs.getString(COLUMN_PASSWORD));
				user.setPhoneNumber(rs.getLong(COLUMN_PHONENUMBER));
				user.setStatus(rs.getBoolean(COLUMN_STATUS));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return user;
	}

	/**
     * Checks whether an email address already exists in the 'users' table.
     *
     * @param email The email address to check.
     * @throws DAOException If there's an issue with the database operation or if the email already exists.
     */
	public void isEmailAlreadyExists(String email) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT email FROM users WHERE email = ?";
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
     * Retrieves an active user based on the provided email address from the 'users' table.
     *
     * @param email The email address to retrieve the user.
     * @return The User object with the specified email address.
     * @throws DAOException If there's an issue with the database operation.
     */
	@Override
	public User findByEmail(String email) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT id, name, email, phone_number, password, status FROM users WHERE status = 1 AND email = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(COLUMN_ID));
				user.setName(rs.getString(COLUMN_NAME));
				user.setPhoneNumber(rs.getLong(COLUMN_PHONENUMBER));
				user.setEmail(rs.getString(COLUMN_EMAIL));
				user.setPassword(rs.getString(COLUMN_PASSWORD));
				user.setStatus(rs.getBoolean(COLUMN_STATUS));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
		return user;
	}

	@Override
	public int count() {

		return 0;
	}
	
	/**
     * Changes the status of a user from deactivated (0) to active (1).
     *
     * @param id The ID of the user to be reactivated.
     * @throws DAOException If there's an issue with the database operation.
     */
	public void changeStatus(int id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET status =1 WHERE id = ? AND status = 0";
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
			ConnectionUtil.close(con, ps);
		}
	}

}
