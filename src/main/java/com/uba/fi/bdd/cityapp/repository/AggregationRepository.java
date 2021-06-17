package com.uba.fi.bdd.cityapp.repository;

import static com.uba.fi.bdd.cityapp.repository.DatabaseConnection.getConnection;

import com.uba.fi.bdd.cityapp.model.Bank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AggregationRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(AggregationRepository.class);

	public Map<String, Integer> getAggregationQuery1() {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"select barrio, count(1) " +
					"from cajeros_automaticos " +
					"group by barrio " +
					"order by 2 desc");

			ResultSet resultSet = preparedStatement.executeQuery();
			Map<String, Integer> atmPerNeighbourhoodMap = new LinkedHashMap<>();
			while (resultSet.next()) {
				atmPerNeighbourhoodMap.put(resultSet.getString("barrio"), resultSet.getInt("count"));
			}
			LOGGER.debug("Retrieved {} records which satisfy the requirement", atmPerNeighbourhoodMap.size());
			return atmPerNeighbourhoodMap;
		} catch (SQLException e) {
			LOGGER.error("An error occurred while getting aggregation bank data", e);
			throw new RuntimeException(e);
		}
	}

	public List<String> getAggregationQuery2() {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(
					"select distinct ca.banco " +
					"from cajeros_automaticos ca " +
					"where ca.comuna = 1 " +
					"group by ca.banco " +
					"having count(distinct barrio) = (select count(1) " +
					"from barrios " +
					"where comuna = 1)");

			ResultSet resultSet = preparedStatement.executeQuery();
			List<String> bankList = new ArrayList<>();
			while (resultSet.next()) {
				bankList.add(resultSet.getString("banco"));
			}
			LOGGER.debug("Retrieved {} banks which satisfy the requirement", bankList.size());
			return bankList;
		} catch (SQLException e) {
			LOGGER.error("An error occurred while getting aggregation bank data", e);
			throw new RuntimeException(e);
		}
	}

}
