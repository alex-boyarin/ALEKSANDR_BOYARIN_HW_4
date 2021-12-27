package userBanking;

import userBanking.utilConnection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    private int accountId;
    private int amount;

    public Transaction() {
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTransactionForUserAccount() throws SQLException {
        String addTransactionSql = " INSERT INTO transactions (account_id, amount) VALUES (?,?)";
        String balanceSql = " UPDATE accounts SET balance = balance + ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement transactionStatement = null;
        PreparedStatement balanceStatement = null;
        try {
            connection = ConnectionManager.open();
            connection.setAutoCommit(false);
            transactionStatement = connection.prepareStatement(addTransactionSql);
            balanceStatement = connection.prepareStatement(balanceSql);

            transactionStatement.setInt(1, accountId);
            transactionStatement.setInt(2, amount);

            balanceStatement.setInt(1, amount);
            balanceStatement.setInt(2, accountId);

            transactionStatement.executeUpdate();
            balanceStatement.executeUpdate();

            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (transactionStatement != null) {
                transactionStatement.close();
            }
            if (balanceStatement != null) {
                balanceStatement.close();
            }
        }
    }
}
