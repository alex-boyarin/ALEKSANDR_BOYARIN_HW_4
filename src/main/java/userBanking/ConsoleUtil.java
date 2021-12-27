package userBanking;

import userBanking.utilConnection.ConnectionManager;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class ConsoleUtil {
    private static User user = new User();
    private static Account account = new Account();
    private static Transaction transaction = new Transaction();

    public static void addUser(Scanner scanner, PrintStream out) {
        String choice;
        do {
            out.print("Введите имя: ");
            user.setUserName(scanner.next());
            out.print("Введите адрес: ");
            user.setAddress(scanner.next());
            user.setUser();
            out.print("Добавить клиента <y> <n> ? ");
            choice = scanner.next();
        } while (choice.equals("y"));
    }

    public static void addAccount(Scanner scanner, PrintStream out) {
        String choice;
        do {
            out.print("введите ID клиента: ");
            account.setUserId(scanner.nextInt());
            out.print("Введите количество валюты: ");
            account.setBalance(scanner.nextInt());
            out.print("Введите тип валюты: byn, rub, usd, eur. ");
            account.setCurrency(scanner.next());
            account.setAccountForUser();
            out.print("Добавить счет <y> <n> ? ");
            choice = scanner.next();
        } while (choice.equals("y"));
    }

    public static void withdrawalOfFunds(Scanner scanner, PrintStream out) {
        String choice;
        do {
            out.print("введите ID аккаунта: ");
            transaction.setAccountId(scanner.nextInt());
            out.print("Введите сумму : ");
            transaction.setAmount(-scanner.nextInt());
            try {
                transaction.setTransactionForUserAccount();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.print("Добавить транзакцию <y> <n> ? ");
            choice = scanner.next();
        } while (choice.equals("y"));
    }

    public static void balanceReplenishment(Scanner scanner, PrintStream out) {
        String choice;
        do {
            out.print("введите ID аккаунта: ");
            transaction.setAccountId(scanner.nextInt());
            out.print("Введите сумму : ");
            transaction.setAmount(scanner.nextInt());
            try {
                transaction.setTransactionForUserAccount();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.print("Добавить транзакцию <y> <n> ? ");
            choice = scanner.next();
        } while (choice.equals("y"));
    }

    public static void getUsers() {
        String getUsersSql = """
                SELECT id , balance, currency,
                (SELECT user_name FROM users WHERE users.id = accounts.user_id)
                FROM accounts
                ORDER BY user_name ;\s
                """;
        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getUsersSql);
            System.out.println("Name     | Acc. Id |  Currency |  Balance\n-----------------------------------------");
            while (resultSet.next()) {
                System.out.println(resultSet.getObject("user_name") + "    |  " +
                                   resultSet.getObject("id") + "      |  " +
                                   resultSet.getObject("currency") + "      |  " +
                                   resultSet.getObject("balance"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
