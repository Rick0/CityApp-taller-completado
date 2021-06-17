package com.uba.fi.bdd.cityapp.repository;

import static com.uba.fi.bdd.cityapp.repository.DatabaseConnection.getConnection;

import com.uba.fi.bdd.cityapp.model.Commune;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommuneRepository extends BaseRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommuneRepository.class);

	public static final String TABLE_NAME = "comunas";

	public List<Commune> getAll() {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement("select id, area, perimetro, listado_barrios from " + TABLE_NAME);

			ResultSet resultSet = preparedStatement.executeQuery();
			List<Commune> communeList = new ArrayList<>();
			while (resultSet.next()) {
				Commune commune = buildCommune(resultSet);
				communeList.add(commune);
			}
			LOGGER.debug("Retrieved {} communes", communeList.size());
			return communeList;
		} catch (SQLException e) {
			LOGGER.error("An error occurred while getting commune data", e);
			throw new RuntimeException(e);
		}
	}

	public Commune get(Integer communeId) {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement("select id, area, perimetro, listado_barrios from " + TABLE_NAME + " where id = ?");
			preparedStatement.setInt(1, communeId);

			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Commune commune = buildCommune(resultSet);
			LOGGER.debug("Retrieved the commune {}", communeId);
			return commune;
		} catch (SQLException e) {
			LOGGER.error("An error occurred while getting commune {} data", communeId, e);
			throw new RuntimeException(e);
		}
	}

	public void add(Commune commune) {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement("insert into " + TABLE_NAME + " values(?, ?, ?, ?)");
			preparedStatement.setInt(1, commune.getId());
			preparedStatement.setDouble(2, commune.getArea());
			preparedStatement.setDouble(3, commune.getPerimeter());
			preparedStatement.setString(4, commune.getNeighbourhoodsList());

			preparedStatement.executeUpdate();
			LOGGER.debug("Commune {} created", commune.getId());
		} catch (SQLException e) {
			LOGGER.error("An error occurred while creating commune {}", commune.getId(), e);
			throw new RuntimeException(e);
		}
	}

	public void update(Commune commune) {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement("update " + TABLE_NAME + " set id = ?, area  = ?, perimetro  = ?, listado_barrios = ? where id = ?");
			preparedStatement.setInt(1, commune.getId());
			preparedStatement.setDouble(2, commune.getArea());
			preparedStatement.setDouble(3, commune.getPerimeter());
			preparedStatement.setString(4, commune.getNeighbourhoodsList());
			preparedStatement.setInt(5, commune.getId());

			preparedStatement.executeUpdate();
			LOGGER.debug("Commune {} updated", commune.getId());
		} catch (SQLException e) {
			LOGGER.error("An error occurred while updating commune {}", commune.getId(), e);
			throw new RuntimeException(e);
		}
	}

	public void delete(Integer communeId) {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement("delete from " + TABLE_NAME + " where id = ?");
			preparedStatement.setInt(1, communeId);

			preparedStatement.executeUpdate();
			LOGGER.debug("Commune {} deleted", communeId);
		} catch (SQLException e) {
			LOGGER.error("An error occurred while deleting commune {}", communeId, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	private static Commune buildCommune(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		double area = resultSet.getDouble("area");
		double perimeter = resultSet.getDouble("perimetro");
		String neighbourhoodsList = resultSet.getString("listado_barrios");

		return new Commune(id, area, perimeter, neighbourhoodsList);
	}

}
