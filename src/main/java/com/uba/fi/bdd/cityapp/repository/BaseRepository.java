package com.uba.fi.bdd.cityapp.repository;

import static com.uba.fi.bdd.cityapp.repository.DatabaseConnection.getConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class BaseRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseRepository.class);

	public final int getCount() {
		try (Connection conn = getConnection()) {
			final Statement stmt = conn.createStatement();
			final ResultSet resultSet = stmt.executeQuery("select count(*) from " + getTableName());

			resultSet.next();
			final int rowsCount = resultSet.getInt(1);
			LOGGER.debug("The table '{}' has {} rows", getTableName(), rowsCount);
			return rowsCount;
		} catch (SQLException e) {
			LOGGER.error("An error occurred while getting ATM data", e);
			throw new RuntimeException(e);
		}
	}

	public abstract String getTableName();

}
