package in.fssa.srcatering.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.StringUtil;

public class UserValidator {

	public static void validate(User newUser) throws ValidationException {

		if (newUser == null) {
			throw new ValidationException("Invalid user Input");
		}

		StringUtil.rejectIfInvalidString(newUser.getName(), "Name");
		StringUtil.rejectIfInvalidString(newUser.getEmail(), "Email");
		StringUtil.rejectIfInvalidString(newUser.getPassword(), "Password");
		StringUtil.rejectIfInvalidPhoneNumber(newUser.getPhone_number());
		StringUtil.rejectIfInvalidEmail(newUser.getEmail());
		StringUtil.rejectIfIvalidPassword(newUser.getPassword());
	}

	public static void isIdValid(int userId) throws ValidationException,RuntimeException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM users WHERE status = 1 && id =? ";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			ps.setInt(1, userId);

			if (!rs.next()) {
				throw new ValidationException("Invaid userId");
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
	
	public static void isEmailAlreadyExists(String email) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM user WHERE email = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				throw new ValidationException("Email already exists");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
	}

}
