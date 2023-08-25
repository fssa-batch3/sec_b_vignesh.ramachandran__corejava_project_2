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

            url = System.getenv("DATABASE_HOSTNAME");
            userName = System.getenv("DATABASE_USERNAME");
            passWord = System.getenv("DATABASE_PASSWORD");

//        	url = "jdbc:mysql://164.52.216.41:3306/vignesh_ramachandran_corejava_project";
//        	userName = "tF1BRRcYo1rI";
//        	passWord = "ba81ad1d-f04e-48e2-b565-70309e45e575";
        

		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
		}
	}

}
