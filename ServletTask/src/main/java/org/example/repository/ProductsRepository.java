package org.example.repository;

import org.example.model.Product;

import java.util.*;

public class ProductsRepository {
    private static ProductsRepository productsRepository;

    private ProductsRepository() {


    }

    public static ProductsRepository getInstance() {
        if (productsRepository == null) {
            productsRepository = new ProductsRepository();
        }
        return productsRepository;
    }


    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public Optional<Product> findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public void addProduct(Product product) {
        if (findProductByName(product.getName()).isPresent()) {
            throw new IllegalArgumentException("Product with this name already exists.");
        }
        products.add(product);
    }

    public void updateProduct(Product product) {
        deleteProduct(product.getName());
        addProduct(product);
    }

    public void deleteProduct(String name) {
        Product productToRemove = null;
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {
            products.remove(productToRemove);
        } else {
            throw new IllegalArgumentException("Sorry, The Product not found.");
        }
    }

    /*

 //We can optimize our solution Avoid duplicate product names: If you switch to using a Map,
 //By using a Map<String, Product>, where the key is the product name, you can perform searches, updates, and deletions in constant time (O(1))..
    // Why Not HashSet?
    // I think a HashSet can work if you override equals() and hashCode() in the Product class to base equality on the product name.
    //You lose direct key-based access, making updates and lookup slightly less intuitive.
    //A HashMap is still cleaner because it allows direct key-value access, which matches Our requirements.
   //If you used a HashSet:
   //You could still prevent duplicates, but only by comparing entire Product objects (not just by the name).
   //You would need to iterate through the set to find products by their name (O(n) complexity), which is inefficient compared to a HashMap.
   //No Direct Key-Based Lookup: You'd have to manually check for duplicates and find products, which leads to additional complexity.
   //
    private Map<String, Product> products = new HashMap<>();

    public Map<String, Product> getProducts() {
        return products;
    }

    public Optional<Product> findProductByName(String name) {
        return Optional.ofNullable(products.get(name));
    }

    public void addProduct(Product product) {
        if (products.containsKey(product.getName())) {
            throw new IllegalArgumentException("Product with this name already exists.");
        }
        products.put(product.getName(), product);
    }

    public void updateProduct(Product product) {
        if (!products.containsKey(product.getName())) {
            throw new IllegalArgumentException("Product not found.");
        }
        products.put(product.getName(), product);
    }

    public void deleteProduct(String name) {
        if (products.remove(name) == null) {
            throw new IllegalArgumentException("Sorry, The Product not found.");
        }
    }
    */


}
