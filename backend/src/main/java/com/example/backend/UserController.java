package com.example.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    public static void DisplayUserInfo(Integer UserID){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Users");
            ResultSet resultSet = statement.executeQuery();

        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
}
