package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=1";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "";
	private static final int MAX_POOL_SIZE = 40;
	private static final DataSource ds;

	static {
		HikariDataSource hikariDataSource = new HikariDataSource();

		hikariDataSource.setDriverClassName(DB_DRIVER);
		hikariDataSource.setJdbcUrl(DB_URL);
		hikariDataSource.setUsername(USERNAME);
		hikariDataSource.setPassword(PASSWORD);
		// poolSize는 WAS 스레드 수를 고려해야 한다. --> 모니터링을 통해서 적정한 poolSize를 정하는 것이 중요하다.
		hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);
		hikariDataSource.setMinimumIdle(MAX_POOL_SIZE);

		ds = hikariDataSource;
	}

	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	public static DataSource getDataSource() {
		return ds;
	}
}
