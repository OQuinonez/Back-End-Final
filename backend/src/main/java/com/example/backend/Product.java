package com.example.backend;

public class Product {
    public Integer ItemID;
    public String ItemName;
    public String Category;
    public Double Price;
    public Integer Quantity;
    public String PicAddress;

    public Product(){}

    public Product(Integer ItemId, String ItemName, String Category, Double Price, Integer Quantity, String PicAddress){
        this.ItemID = ItemId;
        this.ItemName = ItemName;
        this.Category = Category;
        this.Price = Price;
        this.Quantity = Quantity;
        this.PicAddress = PicAddress;
    }
}
