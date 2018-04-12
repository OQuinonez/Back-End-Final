package com.example.backend;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class ProductController {
    @CrossOrigin()
    @GetMapping("/products")
    public List<Product> All(){
        ArrayList<Product> Items = ProductRepository.allProducts();
        return  Items;
    }

    @CrossOrigin
    @PostMapping("/Sell")
    public Product Selling(@RequestBody Product newItem){
        return ProductRepository.SellProduct(newItem.ItemName,  newItem.Category,  newItem.Price, newItem.Quantity, newItem.PicAddress);
    }

    @CrossOrigin
    @PostMapping("/Buy/{productID}")
    public Boolean buying(@PathVariable Integer productID){
        System.out.println(productID);
        return ProductRepository.deleteProduct(productID);
    }
}
