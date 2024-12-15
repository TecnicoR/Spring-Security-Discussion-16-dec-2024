package com.demosecurity.app.controller;

import com.demosecurity.app.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return Product.createHardcodedProducts();
    }
}
