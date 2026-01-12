package com.francis.inventory.services;

import com.francis.inventory.domain.Product;
import com.francis.inventory.exceptions.InvalidStockException;
import com.francis.inventory.exceptions.NotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private final Map<String, Product> products = new HashMap<>();

    // ----------------------------
    // Product management
    // ----------------------------

    public Product createProduct(String id, String name) {
        if (products.containsKey(id)) {
            throw new IllegalArgumentException("Product already exists with id: " + id);
        }

        Product product = new Product(id, name);
        products.put(id, product);
        return product;
    }

    public Product getProduct(String id) {
        Product product = products.get(id);
        if (product == null) {
            throw new NotFoundException("Product not found: " + id);
        }
        return product;
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    // ----------------------------
    // Stock operations
    // ----------------------------

    public void stockIn(String productId, int amount) {
        validateAmount(amount);

        Product product = getProduct(productId);
        product.increaseStock(amount);
    }

    public void stockOut(String productId, int amount) {
        validateAmount(amount);

        Product product = getProduct(productId);

        if (product.getQuantity() < amount) {
            throw new InvalidStockException(
                    "Insufficient stock for product " + productId +
                    ". Available=" + product.getQuantity() +
                    ", requested=" + amount
            );
        }

        product.decreaseStock(amount);
    }

    // ----------------------------
    // Validation
    // ----------------------------

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new InvalidStockException("Stock amount must be greater than zero");
        }
    }
}
