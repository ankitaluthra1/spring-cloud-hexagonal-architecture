package edu.ankital.hexagonal.items.core;

import edu.ankital.hexagonal.items.application.model.Item;
import edu.ankital.hexagonal.items.application.model.ItemUpdateCommand;
import edu.ankital.hexagonal.items.core.ports.ItemDatabase;
import edu.ankital.hexagonal.items.core.ports.UpdateItem;
import org.springframework.stereotype.Component;

@Component
public class UpdateItemFacade implements UpdateItem {
    ItemDatabase itemDatabase;
    public UpdateItemFacade(ItemDatabase itemDatabase) {
        this.itemDatabase = itemDatabase;
    }

    @Override
    public Item update(ItemUpdateCommand itemUpdateCommand) {
        Item item =  itemDatabase.getItemById(itemUpdateCommand.getItemId());
        int quantity = item.getQuantity() + itemUpdateCommand.getQuantity();
        item.setQuantity(quantity);
        return itemDatabase.saveOrUpdate(item);
    }
}
