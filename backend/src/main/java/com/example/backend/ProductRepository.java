package com.example.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepository {
    public static ArrayList<Product> allProducts() {
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Products");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Product> items = new ArrayList<Product>();
            while (resultSet.next()) {
                allProducts().add(new Product(resultSet.getString("ItemName"),
                        resultSet.getString("Category"),
                        resultSet.getDouble("Price"),
                        resultSet.getInt("Quantity"),
                        resultSet.getString("PicAddress")));
            }
            con.close();
            System.out.println(allProducts().get(1).ItemName);
            return allProducts();
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean deleteUser(String Email, String Password){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "DELETE FROM Products WHERE id = ?"
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
