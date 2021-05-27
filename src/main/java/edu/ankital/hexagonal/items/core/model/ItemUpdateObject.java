package edu.ankital.hexagonal.items.core.model;

public class ItemUpdateObject {
    long itemId;
    int quantity;

    public ItemUpdateObject(long id, int quantity) {
        this.itemId = id;
        this.quantity = quantity;
    }

    public long getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
