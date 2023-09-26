package in.fssa.srcatering.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		String url;
		String userName;
		String passWord;

//		url = System.getenv("DATABASE_HOSTNAME");
//		userName = System.getenv("DATABASE_USERNAME");
//		passWord = System.getenv("DATABASE_PASSWORD");
		
		url = "jdbc:mysql://localhost:3306/java_project";
		userName = "root";
		passWord = "123456"; 

		Connection con = null; 
 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);

		} catch (ClassNotFoundException | SQLException e) {
			Logger.error(e);
			throw new SQLException(e.getMessage());
		}
		return con;

	}

	/**
	 * Closes a database connection and a prepared statement.
	 *
	 * @param connection The database connection to close.
	 * @param ps         The prepared statement to close.
	 */
	public static void close(Connection connection, PreparedStatement ps) {

		try {

			if (ps != null) {
				ps.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			Logger.error(e);
			Logger.debug(e.getMessage());
		}
	}

	/**
	 * Closes a database connection, a prepared statement, and a result set.
	 *
	 * @param connection The database connection to close.
	 * @param ps         The prepared statement to close.
	 * @param rs         The result set to close.
	 */
	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}

			if (ps != null) {
				ps.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			Logger.error(e);
			Logger.debug(e.getMessage());
		}
	}

}
