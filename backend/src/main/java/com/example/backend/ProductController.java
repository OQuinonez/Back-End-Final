package com.example.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @CrossOrigin()
    @GetMapping("/products")
    public List<Product> All(){
        System.out.println("Repo UNDER HERE");
        ArrayList<Product> Items = ProductRepository.allProducts();
        System.out.println(Items);
        return  Items;
    }
}
