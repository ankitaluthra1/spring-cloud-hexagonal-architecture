package edu.ankital.hexagonal.items.core;

import edu.ankital.hexagonal.items.core.ports.ItemDatabase;
import edu.ankital.hexagonal.items.core.ports.UpdateItem;
import edu.ankital.hexagonal.items.application.model.Item;
import edu.ankital.hexagonal.items.application.model.ItemUpdateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UpdateItemDatabaseAdapter implements UpdateItem {
    @Autowired
    ItemDatabase itemDatabase;

    @Override
    public Item update(ItemUpdateCommand itemUpdateCommand) {
        Item item =  itemDatabase.getItemById(itemUpdateCommand.getItemId());
        int quantity = item.getQuantity() + itemUpdateCommand.getQuantity();
        item.setQuantity(quantity);
        return itemDatabase.saveOrUpdate(item);
    }
}
