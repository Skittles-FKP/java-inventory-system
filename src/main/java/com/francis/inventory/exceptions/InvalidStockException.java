package com.francis.inventory.exceptions;

public class InvalidStockException extends InventoryException {
    public InvalidStockException(String message) {
        super(message);
    }
}
