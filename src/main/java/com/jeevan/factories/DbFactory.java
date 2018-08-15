package com.jeevan.factories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jeevan on 8/15/18.
 */
public class DbFactory {
	private static HikariDataSource ds;

	public static void initialize(String configLocation) {
		HikariConfig config = new HikariConfig(configLocation);
		ds = new HikariDataSource(config);
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void closeConnection(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	public static void close() {
		ds.close();
	}
}
