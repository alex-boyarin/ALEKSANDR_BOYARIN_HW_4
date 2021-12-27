package userBanking;

import userBanking.utilConnection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private String userName;
    private String address;

    public User() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUser() {
        String addUser = " INSERT INTO users(user_name, address) VALUES (?,?)";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(addUser)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, address);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
