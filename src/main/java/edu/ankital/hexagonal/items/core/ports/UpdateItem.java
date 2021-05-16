package edu.ankital.hexagonal.items.core.ports;

import edu.ankital.hexagonal.items.application.model.Item;
import edu.ankital.hexagonal.items.application.model.ItemUpdateCommand;

public interface UpdateItem {
    public Item update(ItemUpdateCommand itemUpdateCommand);
}
