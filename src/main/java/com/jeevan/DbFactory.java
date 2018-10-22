package com.jeevan;

import com.jeevan.Application;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by jeevan on 8/15/18.
 */
public class DbFactory {
	private static HikariDataSource ds;

	public static void initialize(Class caller, String propsFileInClassPath) throws IOException {
		Properties hikari = new Properties();
		InputStream hikariProps = caller.getClassLoader().getResourceAsStream(propsFileInClassPath);
		hikari.load(hikariProps);
		initialize(hikari);
	}

	public static void initialize(Properties properties) {
		HikariConfig config = new HikariConfig(properties);
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
