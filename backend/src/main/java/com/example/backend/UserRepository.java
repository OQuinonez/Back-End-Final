package com.example.backend;

import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository {
    public static ArrayList<User> GettingUserAccount(User member){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement(
                    "SELECT * FROM USERS");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> allUsers = new ArrayList<User>();
            while (resultSet.next()){
                allUsers.add(new
                        User(resultSet.getString("UserName"),
                        resultSet.getString("UAddress"),
                        resultSet.getString("Email"),
                        resultSet.getString("HashCode")));
            }
            con.close();
            return allUsers;
        }
        catch (SQLException e){
            return null;
        }
    }

    public static User CreatingUser(String UserName, String UAddress, String Email, String Password){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO Users (" +
                            "UserName, UAddress, Email, Password)" +
                            "VALUES (?, ?, ?, ?)");
            statement.setString(1, UserName);
            statement.setString(2,UAddress);
            statement.setString(3,Email);
            statement.setString(4,Password);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            con.close();
            return new User(UserName,
                    UAddress, Email, Password);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static User isUser(String Email, String Password){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement(
                    "UPDATE Users WHERE Email = ? and password = ? returning *"
            );
            statement.setString(1,Email);
            statement.setString(2,Password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            con.close();
            return new User(resultSet.getString("UserName"),
                    resultSet.getString("UAddress"),
                    resultSet.getString("Email"),
                    resultSet.getString("HashCode"));
        }
        catch (SQLException e){
            return null;
        }
    }

    public static boolean deleteUser(String Email, String Password){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "DELETE FROM Users WHERE id = ?"
            );
            preparedStatement.setString(1, Email);
            preparedStatement.setString(2,Password);
            preparedStatement.execute();
            con.close();
            return true;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
