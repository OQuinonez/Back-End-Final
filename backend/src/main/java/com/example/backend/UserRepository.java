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

    public static User isUser(String Email, String pass_word){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement(
                    "UPDATE Users WHERE Email = ? and pass_word = ? returning *"
            );
            statement.setString(1,Email);
            statement.setString(2,pass_word);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            con.close();
            return new User(resultSet.getInt("UserId"),resultSet.getString("UserName"),
                    resultSet.getString("UAddress"),
                    resultSet.getString("Email"),
                    resultSet.getString("HashCode"),
                    resultSet.getString("session_key"));
        }
        catch (SQLException e){
            return null;
        }
    }

    public static User existingUser(String email, String password, String session_key) {
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement("UPDATE student SET session_key = ? WHERE email = ? and p_word = ? RETURNING *");
            statement.setString(1, session_key);
            statement.setString(2, email);
            statement.setString(3, password);
            System.out.println(email);
            System.out.println(password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            con.close();
            resultSet.getString(email);
            resultSet.getString(password);
            return new User(resultSet.getInt("UserId"),resultSet.getString("UserName"),
                    resultSet.getString("UAddress"),
                    resultSet.getString("Email"),
                    resultSet.getString("HashCode"),
                    resultSet.getString("session_key"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
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
