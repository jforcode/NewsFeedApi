package com.jeevan;

import com.jeevan.DbFactory;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jeevan on 8/15/18.
 */
public class DbFactoryTest {
	@Test
	public void dbConnectivityTest() throws SQLException, IOException {
		DbFactory.initialize(getClass(), "hikari.properties");
		try (Connection conn = DbFactory.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement("SELECT NOW()")) {
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					System.out.println(rs.getString(1));
				}
			}
		}
	}
}
