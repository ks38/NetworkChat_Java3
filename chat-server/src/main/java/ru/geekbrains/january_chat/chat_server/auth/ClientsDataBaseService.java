package ru.geekbrains.january_chat.chat_server.auth;

import ru.geekbrains.january_chat.chat_server.error.ChangeNickException;
import ru.geekbrains.january_chat.chat_server.error.WrongCredentialsException;

import java.sql.*;

public class ClientsDataBaseService {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement getClientStatement;
    private static PreparedStatement changeNickStatement;
    public static ClientsDataBaseService instance;

    private static final String DB_CONNECTION = "jdbc:sqlite:db/users.db";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "login TEXT UNIQUE NOT NULL," +
            "password TEXT NOT NULL," +
            "username TEXT UNIQUE NOT NULL);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS users;";
    private static final String INIT_DB = "INSERT INTO users " +
            "(login, password, username)" + "VALUES" +
            "('login1', 'password1', 'user1')," +
            "('login2', 'password2', 'user2')," +
            "('login3', 'password3', 'user3')," +
            "('login4', 'password4', 'user4');";
    private static final String GET_USERNAME = "SELECT username FROM users" +
            " WHERE login = ? AND password = ?;";
    private static final String CHANGE_USERNAME = "UPDATE users SET" +
            " username = ? WHERE login = ?;";


    private ClientsDataBaseService() {
        try {
            connect();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        createDB();
    }

    public static ClientsDataBaseService getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new ClientsDataBaseService();
        return instance;
    }

    private void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(DB_CONNECTION);
        System.out.println("Connecting to database...");
        getClientStatement = connection.prepareStatement(GET_USERNAME);
        changeNickStatement = connection.prepareStatement(CHANGE_USERNAME);
    }

    private void createDB() {
        try {
            statement = connection.createStatement();
            statement.execute(DROP_TABLE);
            statement.execute(CREATE_TABLE);
            statement.execute(INIT_DB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String changeNick(String login, String newNick) {
        try {
            changeNickStatement.setString(1, newNick);
            changeNickStatement.setString(2, login);
            if (changeNickStatement.executeUpdate() > 0) {
                return newNick;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ChangeNickException("Something goes wrong when you tried to change nick...");
    }

    public String getClientsNameByLoginPass(String login, String password) {
        try {
            getClientStatement.setString(1, login);
            getClientStatement.setString(2, password);
            ResultSet res = getClientStatement.executeQuery();
            if (res.next()) {
                String result = res.getString("username");
                res.close();
                System.out.printf("Login is: %s\n", result);
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new WrongCredentialsException("User not found!");
    }

    public void closeConnection() {
        try {
            if (getClientStatement != null) {
                getClientStatement.close();
            }
            if (changeNickStatement != null) {
                changeNickStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
