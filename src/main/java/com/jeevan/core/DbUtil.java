package com.jeevan.core;

import com.jeevan.DbFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeevan on 11/2/18.
 */
public class DbUtil {

	public static void DeleteFromTables(String... tables) throws SQLException {
		try (Connection conn = DbFactory.getConnection()) {
			try (Statement stmt = conn.createStatement()) {
				for (String table : tables) {
					String query = "DELETE FROM " + table;
					stmt.executeUpdate(query);
				}
			}
		}
	}

	public static void ExecInsertQuery(String query, List<Object> args) throws SQLException {
		if (args == null) {
			args = new ArrayList<>();
		}

		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				for (int i = 1; i <= args.size(); i++) {
					stmt.setObject(i, args.get(i - 1));
				}

				stmt.executeUpdate();
			}
		}
	}
}
