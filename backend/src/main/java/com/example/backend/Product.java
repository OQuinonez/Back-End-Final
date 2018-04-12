package com.example.backend;

public class Product {
    public String ItemName;
    public String Category;
    public Double Price;
    public Integer Quantity;
    public String PicAddress;

    public Product(){}

    public Product(String ItemName, String Category, Double Price, Integer Quantity, String PicAddress){
        this.ItemName = ItemName;
        this.Category = Category;
        this.Price = Price;
        this.Quantity = Quantity;
        this.PicAddress = PicAddress;
    }
}
