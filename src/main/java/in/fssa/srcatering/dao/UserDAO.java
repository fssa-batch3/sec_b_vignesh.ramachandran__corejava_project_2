package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.interfaces.UserInterface;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.Logger;
import in.fssa.srcatering.util.PasswordUtil;

public class UserDAO implements UserInterface {
	
	
	private static final String USERNAME = "name";
	private static final String USEREMAIL = "email";
	private static final String USERPASSWORD = "password";
	private static final String USERPHONENUMBER = "phone_number";
	private static final String USERSTATUS = "status";
	private static final String USERID = "id";

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
				newUser.setId(rs.getInt(USERID));
				newUser.setName(rs.getString(USERNAME));
				newUser.setEmail(rs.getString(USEREMAIL));
				newUser.setPhoneNumber(rs.getLong(USERPHONENUMBER));
				newUser.setPassword(PasswordUtil.decodePassword(rs.getString(USERPASSWORD)));
				newUser.setStatus(rs.getBoolean(USERSTATUS));
				userList.add(newUser);
			}

		} catch (SQLException e) {
			Logger.error(e);
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

			System.out.println("User has been created successfully");

		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate entry")) {
				throw new DAOException("Duplicate constraint");
			} else {
				Logger.error(e);
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

			ps.setString(1, newUser.getName().trim());
			ps.setString(2, newUser.getPassword());
			ps.setInt(3, id);

			int rowUpdated = ps.executeUpdate();

			if (rowUpdated > 0) {
				System.out.println("User with ID " + id + " updated successfully.");
			} else {
				System.out.println("No user found with ID " + id + ". Nothing updated.");
			}

		} catch (SQLException e) {
			Logger.error(e);
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
			String query = "UPDATE users SET status = 0 WHERE id = ? AND status = 1";
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
			Logger.error(e);
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
			String query = "SELECT id FROM users WHERE id = ? AND status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, userId);
			
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new DAOException("Invaid UserId");
			}

		} catch (SQLException e) {
			Logger.error(e);
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
				user.setId(rs.getInt(USERID));
				user.setName(rs.getString(USERNAME));
				user.setEmail(rs.getString(USEREMAIL));
				user.setPassword(PasswordUtil.decodePassword(rs.getString(USERPASSWORD)));
				user.setPhoneNumber(rs.getLong(USERPHONENUMBER));
				user.setStatus(rs.getBoolean(USERSTATUS));
			}
		} catch (SQLException e) {
			Logger.error(e);
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

			ps.setString(1, email.trim());
			rs = ps.executeQuery();

			if (rs.next()) {
				throw new DAOException("Email already exists");
			}

		} catch (SQLException e) {
			Logger.error(e);
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
	 * @throws ValidationException 
     */
	@Override
	public User findByEmail(String email) throws DAOException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT id, name, email, phone_number, password, status FROM users WHERE email = ? AND status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email.trim());
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(USERID));
				user.setName(rs.getString(USERNAME));
				user.setPhoneNumber(rs.getLong(USERPHONENUMBER));
				user.setEmail(rs.getString(USEREMAIL));
				user.setPassword(PasswordUtil.decodePassword(rs.getString(USERPASSWORD)));
				user.setStatus(rs.getBoolean(USERSTATUS));
			} else {
				throw new ValidationException("Invalid Email");
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		}  finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
		return user;
	}
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @throws DAOException
	 */
	public void passwordChecker(String email, String password) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT password FROM users WHERE email = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			if(rs.next() && (!rs.getString(USERPASSWORD).equals(password))) {
					throw new DAOException("Invalid Login Credentials");
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
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
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

}
