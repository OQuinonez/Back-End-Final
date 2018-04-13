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
                        User(resultSet.getInt("UserId"), resultSet.getString("UserName"),
                        resultSet.getString("UAddress"),
                        resultSet.getString("Email"),
                        resultSet.getString("HashCode"),
                        resultSet.getString("session_key")));
            }
            con.close();
            return allUsers;
        }
        catch (SQLException e){
            return null;
        }
    }

    public static User CreatingUser(String UserName, String UAddress, String Email, String pass_word, String session_key){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO Users (UserName, UAddress, Email, pass_word, sessionKey) VALUES (?, ?, ?, ?, ?) RETURNING *");
            statement.setString(1, UserName);
            statement.setString(2,UAddress);
            statement.setString(3,Email);
            statement.setString(4, pass_word);
            statement.setString(5, session_key);
            ResultSet result = statement.executeQuery();
            result.next();
            return new User(result.getInt("UserID"), result.getString("UserName"),
                    result.getString("UAddress"), result.getString("Email"), result.getString("pass_word"), result.getString("sessionkey"));
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<User> allUsers() {
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Users");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> users = new ArrayList<User>();
            while (resultSet.next()) {
                allUsers().add(new User(resultSet.getInt("UserId"),
                        resultSet.getString("UserName"),
                        resultSet.getString("UAddress"),
                        resultSet.getString("Email"),
                        resultSet.getString("pass_word"),
                        resultSet.getString("session_key")));
            }
            con.close();
            System.out.println(allUsers().get(1).UserName);
            return allUsers();
        } catch (SQLException e) {
            return null;
        }
    }

    public static User isUser(String sessionKey, String Email, String pass_word){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement(
                    "UPDATE Users SET sessionKey = ? WHERE UserName = ? and pass_word = ? returning *"
            );
            statement.setString(1,sessionKey);
            statement.setString(2,Email);
            statement.setString(3,pass_word);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            con.close();
            return new User(resultSet.getInt("UserId"),
                    resultSet.getString("UserName"),
                    resultSet.getString("UAddress"),
                    Email,
                    pass_word,
                    sessionKey);
        }
        catch (SQLException e){
            return null;
        }
    }

    public static boolean updateSessionKey(String Email){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "UPDATE Users SET sessionKey = null WHERE Email = ? RETURNING *");
            preparedStatement.setString(1, Email);
            preparedStatement.executeQuery();
            con.close();
            return true;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Boolean existingUser(String session_key, String email, String password) {
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement("UPDATE users SET sessionKey = ? WHERE users.email = ? and pass_word = ? RETURNING *");
            statement.setString(1, email);
            System.out.println(email);
            System.out.println(password);
            ResultSet resultSet = statement.executeQuery();
            con.close();
            System.out.println("Logged out works!");
            return true;
//                    new User(resultSet.getInt("UserId"),
//                    resultSet.getString("UserName"),
//                    resultSet.getString("UAddress"),
//                    email,
//                    password,
//                    session_key);
        } catch (SQLException e) {
            System.out.println("Logged out unsuccefull");
            System.out.println(e.getMessage());
            return false;
        }
    }

//    public static boolean deleteUser(String Email, String Password){
//        try {
//            Connection con = Connect.LoadDB();
//            PreparedStatement preparedStatement = con.prepareStatement(
//                    "DELETE FROM Users WHERE id = ?"
//            );
//            preparedStatement.setString(1, Email);
//            preparedStatement.setString(2,Password);
//            preparedStatement.execute();
//            con.close();
//            return true;
//        }
//        catch (SQLException e){
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
}
