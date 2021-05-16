package edu.ankital.hexagonal.items.core.ports;

import edu.ankital.hexagonal.items.application.model.Item;

import java.util.Optional;

public interface ItemDatabase {
    Item getItemById(long id);
    Item saveOrUpdate(Item item);
}
