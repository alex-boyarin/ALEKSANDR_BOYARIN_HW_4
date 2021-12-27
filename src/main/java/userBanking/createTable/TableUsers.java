package userBanking.createTable;

import userBanking.utilConnection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableUsers {

    public TableUsers() {
        createTableUsers();
    }

    private void createTableUsers() {
        String tableUsers = """
                CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                user_name VARCHAR(50) NOT NULL,
                address VARCHAR(255)
                )""";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(tableUsers)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
