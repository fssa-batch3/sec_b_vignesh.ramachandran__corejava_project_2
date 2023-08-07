package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.interfacee.UserInterface;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.util.ConnectionUtil;

public class UserDAO implements UserInterface {

	@Override
	public List<User> findAll() {
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
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return userList;
	}

	@Override
	public void create(User newUser) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO users(name, email, phone_number, password) VALUES (?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getEmail());
			ps.setLong(3, newUser.getPhone_number());
			ps.setString(4, newUser.getPassword());

			ps.executeUpdate();

			System.out.println("User has been created sucessfully");

		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate entry")) {
				throw new RuntimeException("Duplicate constraint");
			} else {
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	@Override
	public void update(int id, User newUser) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			String query = "UPDATE users SET name=?, password=? WHERE id =?";
			ps = con.prepareStatement(query);

			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getEmail());
			ps.setInt(3, id);

			int rowUpdated = ps.executeUpdate();

			if (rowUpdated > 0) {
				System.out.println("User with ID " + id + " updated successfully.");
			} else {
				System.out.println("No user found with ID " + id + ". Nothing updated.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

	}

	@Override
	public void delete(int id) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "UPDATE users SET status =0 WHERE id = ?";
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
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

	}

	@Override
	public User findById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT * FROM uses WHERE status = 1 && id = ?";
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
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			String query = "SELECT * FROM users WHERE is_active = 1 AND email = ?";
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
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return user;
	}

	@Override
	public int count() {
		
		return 0;
	}

}
