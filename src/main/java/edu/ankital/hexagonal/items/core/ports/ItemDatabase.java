package edu.ankital.hexagonal.items.core.ports;


import edu.ankital.hexagonal.items.infrastructure.entity.Item;

public interface ItemDatabase {
    Item getItemById(Long id);
    Item saveOrUpdate(Item item);
}
