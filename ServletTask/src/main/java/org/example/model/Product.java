package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.setName(name);
        this.setPrice(price) ;
    }

    public void setName(String name) {
        if (name != null && name.length() <= 100) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Product name must be 100 characters or less.");
        }
    }

    public void setPrice(double price) {
        if (price >= 0.0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Product price must be non-negative.");
        }
    }
}
