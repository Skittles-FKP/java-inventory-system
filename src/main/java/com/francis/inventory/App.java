package com.francis.inventory;

import com.francis.inventory.services.InventoryService;
import com.francis.inventory.domain.Product;

public class App {

    public static void main(String[] args) {

        InventoryService inventoryService = new InventoryService();

        System.out.println("=== Java Inventory Management System Demo ===");

        // Create product
        Product laptop = inventoryService.createProduct("P100", "Laptop");
        System.out.println("Created product: " + laptop);

        // Stock in
        inventoryService.stockIn("P100", 10);
        System.out.println("Stocked IN 10 units");

        // Stock out
        inventoryService.stockOut("P100", 3);
        System.out.println("Stocked OUT 3 units");

        // Final state
        Product finalProduct = inventoryService.getProduct("P100");
        System.out.println("Final product state: " + finalProduct);

        // Demonstrate error handling
        try {
            inventoryService.stockOut("P100", 100);
        } catch (Exception ex) {
            System.out.println("Expected error: " + ex.getMessage());
        }

        System.out.println("=== Demo complete ===");
    }
}
