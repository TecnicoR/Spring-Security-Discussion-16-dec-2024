package com.demosecurity.app.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Product {

    private UUID id;
    private String name;

    // Private method to create 10 hardcoded Product objects
    public static List<Product> createHardcodedProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            products.add(Product.builder()
                    .id(UUID.randomUUID())
                    .name("Product " + i)
                    .build());
        }
        return products;
    }

}
