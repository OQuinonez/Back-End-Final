package com.example.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static ArrayList<Product> allProducts() {
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Products Order by ItemId DESC");
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            ArrayList<Product> items = new ArrayList<Product>();
            while (resultSet.next()) {
                items.add(new Product(
                        resultSet.getInt("ItemID"),
                        resultSet.getString("ItemName"),
                        resultSet.getString("Category"),
                        resultSet.getDouble("Price"),
                        resultSet.getInt("Quantity"),
                        resultSet.getString("PicAddress")
                ));
            }
//            con.close();
            System.out.println(items);
            System.out.println("It some how works");
//            System.out.println((items.get(1).ItemName));
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There was an error somewhere...");
            return null;
        }
    }

    public static Product SellProduct(String ItemName, String Category, Double Price, Integer Quantity, String PicAddress){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO Products (ItemName, Category, Price, Quantity, PicAddress) VALUES (?, ?, ?, ?, ?) RETURNING *");
            statement.setString(1, ItemName);
            statement.setString(2,Category);
            statement.setDouble(3,Price);
            statement.setInt(4, Quantity);
            statement.setString(5, PicAddress);
            ResultSet result = statement.executeQuery();
            result.next();
            return new Product(result.getInt("ItemID"), result.getString("ItemName"),
                    result.getString("Category"), result.getDouble("Price"), result.getInt("Quantity"), result.getString("PicAddress"));
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean deleteProduct(Integer ItemID){
        try {
            Connection con = Connect.LoadDB();
            PreparedStatement preparedStatement = con.prepareStatement(
                    "DELETE FROM Products WHERE ItemID = ?"
            );
            preparedStatement.setInt(1, ItemID);
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
