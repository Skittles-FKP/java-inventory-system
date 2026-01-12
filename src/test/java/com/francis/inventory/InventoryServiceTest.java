package com.francis.inventory;

import com.francis.inventory.domain.Product;
import com.francis.inventory.exceptions.InvalidStockException;
import com.francis.inventory.exceptions.NotFoundException;
import com.francis.inventory.services.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        inventoryService = new InventoryService();
    }

    @Test
    void createProduct_shouldCreateProductWithZeroStock() {
        Product product = inventoryService.createProduct("P1", "Laptop");

        assertEquals("P1", product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals(0, product.getQuantity());
    }

    @Test
    void stockIn_shouldIncreaseQuantity() {
        inventoryService.createProduct("P1", "Laptop");

        inventoryService.stockIn("P1", 10);

        Product product = inventoryService.getProduct("P1");
        assertEquals(10, product.getQuantity());
    }

    @Test
    void stockOut_shouldDecreaseQuantity() {
        inventoryService.createProduct("P1", "Laptop");
        inventoryService.stockIn("P1", 10);

        inventoryService.stockOut("P1", 4);

        Product product = inventoryService.getProduct("P1");
        assertEquals(6, product.getQuantity());
    }

    @Test
    void stockOut_shouldThrowExceptionWhenInsufficientStock() {
        inventoryService.createProduct("P1", "Laptop");

        InvalidStockException ex = assertThrows(
                InvalidStockException.class,
                () -> inventoryService.stockOut("P1", 1)
        );

        assertTrue(ex.getMessage().contains("Insufficient stock"));
    }

    @Test
    void stockIn_shouldRejectZeroOrNegativeAmount() {
        inventoryService.createProduct("P1", "Laptop");

        assertThrows(InvalidStockException.class,
                () -> inventoryService.stockIn("P1", 0));

        assertThrows(InvalidStockException.class,
                () -> inventoryService.stockIn("P1", -5));
    }

    @Test
    void getProduct_shouldThrowExceptionIfNotFound() {
        assertThrows(NotFoundException.class,
                () -> inventoryService.getProduct("UNKNOWN"));
    }
}
