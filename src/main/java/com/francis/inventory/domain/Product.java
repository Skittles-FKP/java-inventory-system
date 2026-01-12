package com.francis.inventory.domain;

public class Product {

    private final String id;
    private final String name;
    private int quantity;

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
        this.quantity = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseStock(int amount) {
        quantity += amount;
    }

    public void decreaseStock(int amount) {
        quantity -= amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
