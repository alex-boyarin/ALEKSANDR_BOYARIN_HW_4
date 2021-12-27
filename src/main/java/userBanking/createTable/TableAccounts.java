package userBanking.createTable;

import userBanking.utilConnection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableAccounts {

    public TableAccounts() {
        createTableAccounts();
    }

    private void createTableAccounts() {
        String tableAccounts = """
                CREATE TABLE IF NOT EXISTS accounts (
                id SERIAL PRIMARY KEY,
                user_id INTEGER REFERENCES users(id),
                balance INTEGER  CHECK (balance >= 0 AND balance <= 2000000000),
                currency VARCHAR(10)
                )""";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(tableAccounts)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
