package edu.ankital.hexagonal.items.core.ports;

import edu.ankital.hexagonal.items.application.model.ItemUpdateCommand;
import edu.ankital.hexagonal.items.infrastructure.entity.Item;

public interface UpdateItem {
    public Item update(ItemUpdateCommand itemUpdateCommand);
}
