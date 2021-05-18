package edu.ankital.hexagonal.items.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "items")
public class Item {
    @Id
    @GeneratedValue
    private long id;
    private int quantity;
    private String name;
    private int qualityAssured = 1;

    public Item() {
    }

    public Item(long id, int quantity, String name) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int isQualityAssured() {
        return qualityAssured;
    }
}
