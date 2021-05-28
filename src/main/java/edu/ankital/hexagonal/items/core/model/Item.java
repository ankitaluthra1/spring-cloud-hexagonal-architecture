package edu.ankital.hexagonal.items.core.model;

import edu.ankital.hexagonal.items.infrastructure.entity.ItemEntity;

public class Item {
    private long id;
    private int quantity;

    public Item() {
    }

    private Item(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public static Item from(ItemEntity itemEntity) {
        return new Item(itemEntity.getId(), itemEntity.getQuantity());
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
