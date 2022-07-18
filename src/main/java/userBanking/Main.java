package userBanking;

import userBanking.createTable.TableAccounts;
import userBanking.createTable.TableTransactions;
import userBanking.createTable.TableUsers;

import java.util.Scanner;
/* Написать программу на Java, предоставляющую следующую функциональность:
1 Регистрацию нового пользователя
2 Добавление аккаунта новому пользователю
3 Пополнение существующего аккаунта
4 Снятие средств с существующего аккаунта.

 Ограничения:
1 Каждый пользователь может иметь сколько угодно аккаунтов в разных валютах
2 Пользователь может иметь только 1 аккаунт в конкретной валюте
3 Размер транзакции не может превышать 100’000’000
4 Баланс аккаунта не может быть отрицательным или превышать 2’000’000’000
5 Поле адрес является необязательном к заполнению при регистрации
6 Дробная часть чисел ограничена 3 знаками.

 Дополнительные условия:
1 Необходимо использовать JDBC
2 Необходимо использовать драйвер для PostgreSQL
3 Для помощи в написании и проверки,
 что действительно происходит в базе, рекомендуется использовать pgAdmin
4 В таблицу транзакций пополнения записываются как положительные числа, снятия как отрицательные.
 При этом пользователь в обоих случаях вводит положительные числа.
 */

public class Main {
    private static TableUsers tableUsers;
    private static TableAccounts tableAccounts;
    private static TableTransactions tableTransactions;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        tableUsers = new TableUsers();
        tableAccounts = new TableAccounts();
        tableTransactions = new TableTransactions();
        while (true) {
            System.out.println("""
                    Меню:
                    1. Добавить клиента в базу данных.
                    2. Добавить счет клиенту.
                    3. Пополнение средств аккаунта.
                    4. Снятие средств с аккаунта.
                    5. Список всех клиентов.
                    0. ВЫХОД.
                    """);
            int choice = scanner.nextInt();
            if (choice == 1) {
                ConsoleUtil.addUser(scanner, System.out);
            }
            if (choice == 2) {
                ConsoleUtil.addAccount(scanner, System.out);
            }
            if (choice == 3) {
                ConsoleUtil.balanceReplenishment(scanner, System.out);
            }
            if (choice == 4) {
                ConsoleUtil.withdrawalOfFunds(scanner, System.out);
            }
            if (choice == 5) {
                ConsoleUtil.getUsers();
            }
            if (choice == 0) {
                break;
            }
        }
    }
}
