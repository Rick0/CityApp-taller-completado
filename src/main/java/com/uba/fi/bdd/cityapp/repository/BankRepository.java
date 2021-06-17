package com.uba.fi.bdd.cityapp.repository;

import static com.uba.fi.bdd.cityapp.repository.DatabaseConnection.getConnection;

import com.uba.fi.bdd.cityapp.model.Bank;
import com.uba.fi.bdd.cityapp.model.Bank;
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
public class BankRepository extends BaseRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(BankRepository.class);

	public static final String TABLE_NAME = "bancos";

	public List<Bank> getAll() {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement("select id, nombre from bancos");
			ResultSet resultSet = preparedStatement.executeQuery();

			List<Bank> bankList = new ArrayList<>();
			while (resultSet.next()) {
				Bank bank = buildBank(resultSet);
				bankList.add(bank);
			}
			LOGGER.debug("Retrieved {} banks", bankList.size());
			return bankList;
		} catch (SQLException e) {
			LOGGER.error("An error occurred while getting bank data", e);
			throw new RuntimeException(e);
		}
	}

	public Bank get(Integer bankId) {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement("select id, nombre from " + TABLE_NAME + " where id = ?");
			preparedStatement.setInt(1, bankId);

			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Bank bank = buildBank(resultSet);
			LOGGER.debug("Retrieved the bank {}", bankId);
			return bank;
		} catch (SQLException e) {
			LOGGER.error("An error occurred while getting bank {} data", bankId, e);
			throw new RuntimeException(e);
		}
	}

	public void add(Bank bank) {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement("insert into " + TABLE_NAME + " values(?, ?)");
			preparedStatement.setInt(1, bank.getId());
			preparedStatement.setString(2, bank.getName());

			preparedStatement.executeUpdate();
			LOGGER.debug("Bank {} created", bank.getId());
		} catch (SQLException e) {
			LOGGER.error("An error occurred while creating bank {}", bank.getId(), e);
			throw new RuntimeException(e);
		}
	}

	public void update(Bank bank) {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement("update " + TABLE_NAME + " set id = ?, nombre = ? where id = ?");
			preparedStatement.setInt(1, bank.getId());
			preparedStatement.setString(2, bank.getName());
			preparedStatement.setInt(3, bank.getId());

			preparedStatement.executeUpdate();
			LOGGER.debug("Bank {} updated", bank.getId());
		} catch (SQLException e) {
			LOGGER.error("An error occurred while updating bank {}", bank.getId(), e);
			throw new RuntimeException(e);
		}
	}

	public void delete(Integer bankId) {
		try (Connection conn = getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement("delete from " + TABLE_NAME + " where id = ?");
			preparedStatement.setInt(1, bankId);

			preparedStatement.executeUpdate();
			LOGGER.debug("Bank {} deleted", bankId);
		} catch (SQLException e) {
			LOGGER.error("An error occurred while deleting bank {}", bankId, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	private static Bank buildBank(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String name = resultSet.getString("nombre");

		return new Bank(id, name);
	}

}
