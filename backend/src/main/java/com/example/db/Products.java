package com.example.db;

import java.util.List;

public class Products {
    public static List<Products> all() {
        //Connect
        //SELECT * FROM PRODUCTS
         return fetchall();
    }

    public static void create(Products p) {
        // connect
        // prepare statement
        // INSERT INTO ....
        // Execute
    }

}
