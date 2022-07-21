package database;

import java.sql.*;
import java.util.ArrayList;
import models.Account;
import models.AccountType;
import models.Transaction;
import models.TransactionType;
import models.User;
import models.UserDTO;
import services.ManageNewAccounts;

public class backend {

    public static Statement myStmt;
    public static ResultSet myRs;

    public static String dbUrl = "jdbc:mysql://127.0.0.1:3306/users";
    public static String user = "student";
    public static String pass = "student";
    private static Connection connection;
    public static ManageNewAccounts manage = new ManageNewAccounts();

    public static void main(String[] args) {
    }

    public static Connection getConn() throws SQLException {
        Connection myConn;
        try {
            myConn = DriverManager.getConnection(dbUrl, user, pass);
            return myConn;
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return null;
        }

    }

    public static void RegisterNewUser(User user) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO USERS(name, surname, password, email, IDNumber, age) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getIDNumber());
                statement.setInt(6, user.getAge());
                int result = statement.executeUpdate();
                if (result == 0) {
                    throw new SQLException();
                }

                ResultSet key = statement.getGeneratedKeys();
                int newUserID = 0;
                if (key.next()) {
                    newUserID = key.getInt(1);
                }

                if (newUserID > 0) {
                    statement = connection.prepareStatement(
                            "INSERT INTO ACCOUNTS(balance, userID, accountNumber, accountTypeID) VALUES (?,?,?,?)");
                    statement.setDouble(1, manage.createNewSavingsAccountBalance());
                    statement.setInt(2, newUserID);
                    statement.setInt(3, manage.createNewSavingsAccountNumber());
                    statement.setInt(4, 2);
                    statement.executeUpdate();
                }

            }
        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            connection.close();
        }
    }

    public static User GetUserByEmail(String email) throws SQLException {
        try {
            connection = getConn();
            PreparedStatement userDetails = connection.prepareStatement(
                    "SELECT * FROM USERS WHERE email = ?");
            userDetails.setString(1, email);
            ResultSet userDetailsRs = userDetails.executeQuery();

            if (userDetailsRs.next()) {//Found user in database
                User user = new User();
                user.setId(userDetailsRs.getInt("id"));
                user.setName(userDetailsRs.getString("name"));
                user.setSurname(userDetailsRs.getString("surname"));
                user.setPassword(userDetailsRs.getString("password"));
                user.setEmail(userDetailsRs.getString("email"));
                user.setIDNumber(userDetailsRs.getString("IDNumber"));
                user.setAge(userDetailsRs.getInt("age"));

                PreparedStatement balanceDetails = connection.prepareStatement(
                        "SELECT * FROM ACCOUNTS WHERE userID = ?");
                balanceDetails.setString(1, String.valueOf(user.getId()));
                ResultSet balancesRs = balanceDetails.executeQuery();
                user.accounts = new ArrayList<>();

                while (balancesRs.next()) {
                    Account account = new Account();
                    account.setId(balancesRs.getInt("accountID"));
                    account.setUserID(balancesRs.getInt("userID"));
                    account.setAccountNumber(balancesRs.getInt("accountNumber"));
                    account.setBalance(balancesRs.getDouble("balance"));
                    account.setAccountTypeID(balancesRs.getInt("accountTypeID"));
                    user.accounts.add(account);

                    PreparedStatement accountTypeDetails = connection.prepareStatement(
                            "SELECT * FROM ACCOUNTTYPES WHERE accountTypeID = ?");
                    accountTypeDetails.setString(1, String.valueOf(account.getAccountTypeID()));
                    ResultSet accountTypeRs = accountTypeDetails.executeQuery();
                    while (accountTypeRs.next()) {
                        AccountType type = new AccountType();
                        type.setAccountTypeID(accountTypeRs.getInt("accountTypeID"));
                        type.setName(accountTypeRs.getString("name"));
                        type.setDescription(accountTypeRs.getString("description"));
                        type.setCode(accountTypeRs.getString("code"));
                        account.setAccountType(type);
                    }
                }

                return user;
            } else {//No user found
                return null;
            }
        } catch (Exception exc) {
            System.out.println(exc);
            throw exc;
        } finally {
            connection.close();
        }

    }

    public static void changeUserPassword(String email, String password) throws SQLException {
        try {
            connection = getConn();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE USERS SET password = ? WHERE email = ?");
            statement.setString(1, password);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (Exception exc) {
        } finally {
            connection.close();
        }
    }

    public static ArrayList getAccountTypes() throws SQLException {
        ArrayList<AccountType> accountTypes = new ArrayList<>();
        try {
            connection = getConn();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM AccountTypes");
            ResultSet accountTypesRs = statement.executeQuery();

            while (accountTypesRs.next()) {
                AccountType type = new AccountType();
                type.setName(accountTypesRs.getString("name"));
                type.setCode(accountTypesRs.getString("code"));
                accountTypes.add(type);
            }

        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            connection.close();
        }
        return accountTypes;
    }

    public static ArrayList getTransactionTypes() throws SQLException {
        ArrayList<TransactionType> transactionTypes = new ArrayList<>();
        try {
            connection = getConn();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM TransactionTypes");
            ResultSet transactionTypesRS = statement.executeQuery();

            while (transactionTypesRS.next()) {
                TransactionType type = new TransactionType();
                type.setName(transactionTypesRS.getString("name"));
                type.setCode(transactionTypesRS.getString("code"));
                transactionTypes.add(type);
            }

        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            connection.close();
        }
        return transactionTypes;
    }

    public static void createNewAccountType(AccountType type) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO AccountTypes(name, description, code) VALUES (?,?,?)");
                statement.setString(1, type.getName());
                statement.setString(2, type.getDescription());
                statement.setString(3, type.getCode());
                int result = statement.executeUpdate();
                if (result == 0) {
                    throw new SQLException();
                }

            }
        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            connection.close();
        }
    }

    public static void changeAccountType(AccountType type) throws SQLException {
        try {
            connection = getConn();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE ACCOUNTTYPES SET name = ?, description = ? WHERE code = ?");
            statement.setString(1, type.getName());
            statement.setString(2, type.getDescription());
            statement.setString(3, type.getCode());
            statement.executeUpdate();
        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            connection.close();
        }
    }

    public static void deleteAccountType(String code) throws SQLException {
        try {
            connection = getConn();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM ACCOUNTTYPES WHERE code = ?");
            statement.setString(1, code);
            statement.executeUpdate();
        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            connection.close();
        }
    }

    public static void createNewTransactionType(TransactionType type) throws SQLException {
        try {
            connection = getConn();
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO TRANSACTIONTYPES(name, description, code) VALUES (?,?,?)");
                statement.setString(1, type.getName());
                statement.setString(2, type.getDescription());
                statement.setString(3, type.getCode());
                int result = statement.executeUpdate();
                if (result == 0) {
                    throw new SQLException();
                }

            }
        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            connection.close();
        }
    }

    public static void changeTransactionType(TransactionType type) throws SQLException {
        try {
            connection = getConn();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE TRANSACTIONTYPES SET name = ?, description = ? WHERE code = ?");
            statement.setString(1, type.getName());
            statement.setString(2, type.getDescription());
            statement.setString(3, type.getCode());
            statement.executeUpdate();
        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            connection.close();
        }
    }

    public static void deleteTransactionType(String code) throws SQLException {
        try {
            connection = getConn();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM TRANSACTIONTYPES WHERE code = ?");
            statement.setString(1, code);
            statement.executeUpdate();
        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            connection.close();
        }
    }

    public static boolean checkRegistrationEmailExists(String email) throws SQLException {
        try {
            connection = getConn();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM USERS WHERE EMAIL = ?");
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }

        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            connection.close();
        }
        return false;
    }

    public static boolean transferAmount(UserDTO senderDetails, Transaction transaction, Account senderAccount) throws SQLException {
        try {
            connection = getConn();
            PreparedStatement logTransaction = connection.prepareStatement(
                    "INSERT INTO TRANSACTIONS(senderID, senderAccountNumber, recieverAccountNumber, transactionTimestamp, transactionAmount, transactionTypeID) VALUES (?,?,?,?,?,?)");
            logTransaction.setInt(1, senderDetails.getUserID());
            logTransaction.setInt(2, transaction.getSenderAccountNumber());
            logTransaction.setInt(3, transaction.getRecipientAccountNumber());
            logTransaction.setString(4, transaction.getTransactionTimestamp());
            logTransaction.setDouble(5, transaction.getTransactionAmount());
            logTransaction.setDouble(6, transaction.getTransactionTypeID());
            logTransaction.executeUpdate();

            PreparedStatement lowerBalance = connection.prepareStatement(
                    "UPDATE ACCOUNTS SET balance = balance - ? WHERE accountNumber = ?");
            lowerBalance.setDouble(1, transaction.getTransactionAmount());
            lowerBalance.setInt(2, transaction.getSenderAccountNumber());
            lowerBalance.executeUpdate();

            PreparedStatement increaseBalance = connection.prepareStatement(
                    "UPDATE ACCOUNTS SET balance = balance + ? WHERE accountNumber = ?");
            increaseBalance.setDouble(1, transaction.getTransactionAmount());
            increaseBalance.setInt(2, transaction.getRecipientAccountNumber());
            increaseBalance.executeUpdate();

            return true;

        } catch (Exception exc) {
            System.out.println(exc);
        } finally {
            connection.close();
        }
        return false;
    }
}
