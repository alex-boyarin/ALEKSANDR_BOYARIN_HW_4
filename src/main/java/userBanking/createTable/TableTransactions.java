package userBanking.createTable;

import userBanking.utilConnection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableTransactions {
    public TableTransactions() {
        createTableTransactions();
    }

    private void createTableTransactions() {
        String tableTransactions = """
                CREATE TABLE IF NOT EXISTS transactions(
                id SERIAL PRIMARY KEY,
                account_id INTEGER REFERENCES accounts(id),
                amount INTEGER NOT NULL CHECK (amount <= 100000000)
                )""";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(tableTransactions)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
