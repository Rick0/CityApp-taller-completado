package com.uba.fi.bdd.cityapp.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);

	/**
	 * 5432 is the default port number for local PostgreSQL,
	 * but we are using pdAdmin's DB, that's why the port number 5433.<p>
	 * Change the port number accordingly to your local PostgreSQL.
	 */
	static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/postgres", "postgres", "postgres");
		} catch (SQLException e) {
			LOGGER.error("An exception occurred while trying to connect to the database", e);
			throw new RuntimeException(e);
		}
	}

}
