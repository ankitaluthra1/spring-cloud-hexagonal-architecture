package edu.ankital.hexagonal.items.infrastructure;

import edu.ankital.hexagonal.items.application.model.Item;
import edu.ankital.hexagonal.items.core.ports.ItemDatabase;
import edu.ankital.hexagonal.items.infrastructure.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemDatabaseAdapter implements ItemDatabase {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item getItemById(long id) {
        return itemRepository.findById(id).orElseThrow(() ->new ItemNotFoundException("Item with id: "+id+" not present"));
    }

    @Override
    public Item saveOrUpdate(Item item) {
        return itemRepository.save(item);
    }
}
