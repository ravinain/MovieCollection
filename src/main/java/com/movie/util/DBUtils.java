/**
 * package-info.java
 */
package com.movie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.istack.internal.logging.Logger;

/**
 * @author cdacr
 * 
 */
public final class DBUtils {

	/**
	 * It stores the DB connection string.
	 */
	private static final String DB_URL = "jdbc:derby://localhost:1527/"
			+ "MovieDB;create=true;user=ravi;password=nain";
	/**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger(DBUtils.class);

	/**
	 * Method register the driver and return the connection from it.
	 * @return {@link Connection}
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			conn = DriverManager.getConnection(DB_URL);
		} catch (final InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			LOGGER.severe(e.getMessage());
		}
		return conn;
	}

	/**
	 * Method closes the DB resources.
	 * @param rs 
	 * @param ps 
	 * @param stmt 
	 * @param conn 
	 */
	public void closeResources(final ResultSet rs, final PreparedStatement ps,
			final Statement stmt, final Connection conn) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
}
