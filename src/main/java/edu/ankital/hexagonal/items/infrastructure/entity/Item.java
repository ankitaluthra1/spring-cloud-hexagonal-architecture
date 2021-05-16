package edu.ankital.hexagonal.items.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private long id;
    private int quantity;
    private String name;
    private Map<String, String> metadata;

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }
}
