package edu.ankital.hexagonal.items.application.model;

import java.util.Map;

public class Item {
    long id;
    int quantity;
    Map<String, String> metadata;

    public Item(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Item(long id, int quantity, Map<String, String> metadata) {
        this.id = id;
        this.quantity = quantity;
        this.metadata = metadata;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
