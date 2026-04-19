package utilities;

import java.sql.*;

public class ReusableMethods {
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

    public static void getMyConnection(){
        String url = ConfigReader.getProperty("url");
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createSelectStatement(){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeSelectStatement(String calisacakQuery){
        createSelectStatement();
        try {
            resultSet = statement.executeQuery(calisacakQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    public static void closeMyConnection(){

        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}