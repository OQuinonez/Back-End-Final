package com.example.backend;


import com.example.db.Products;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @CrossOrigin()
    @GetMapping("/products")

    public List<Product> all() {
        return Product.all();
    }

    @CrossOrigin()
    @PostMapping("/products/new")
    public Products newProduct(@RequestParam Products product){
        Products.create(product);
        return product;
    }

}
