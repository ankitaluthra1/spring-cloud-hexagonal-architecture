package edu.ankital.hexagonal.items.application.model;

public class ItemUpdateCommand {
    String quantity;
    String itemId;

    public ItemUpdateCommand(String quantity, String itemId) {
        this.quantity = quantity;
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getQuantity() {
        return quantity;
    }
}
