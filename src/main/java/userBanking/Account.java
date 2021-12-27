package userBanking;

import userBanking.utilConnection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Account {
    private int userId;
    private int balance;
    private String currency;

    public Account() {

    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAccountForUser() {
        String addAccount = " INSERT INTO accounts(user_id, balance, currency) VALUES (?,?,?)";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(addAccount)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, balance);
            preparedStatement.setString(3, currency);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
