package edu.ankital.hexagonal.items.infrastructure;

import edu.ankital.hexagonal.items.core.ports.ItemDatabase;
import edu.ankital.hexagonal.items.infrastructure.entity.Item;
import edu.ankital.hexagonal.items.infrastructure.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ItemDatabaseAdapter implements ItemDatabase {

    ItemRepository itemRepository;

    public ItemDatabaseAdapter(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() ->new ItemNotFoundException("Item with id: "+id+" not present"));
    }

    @Override
    public Item saveOrUpdate(Item item) {
        return itemRepository.save(item);
    }
}
