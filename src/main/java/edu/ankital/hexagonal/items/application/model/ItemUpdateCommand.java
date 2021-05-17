package edu.ankital.hexagonal.items.application.model;

public class ItemUpdateCommand {
    int quantity;
    long itemId;

    public ItemUpdateCommand(int quantity, long itemId) {
        this.quantity = quantity;
        this.itemId = itemId;
    }

    public long getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
