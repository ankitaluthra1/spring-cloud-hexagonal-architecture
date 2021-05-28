package edu.ankital.hexagonal.items.core.ports;

import edu.ankital.hexagonal.items.application.model.QualityCheckCommand;
import edu.ankital.hexagonal.items.core.model.Item;
import edu.ankital.hexagonal.items.core.model.ItemUpdateObject;
import edu.ankital.hexagonal.items.infrastructure.entity.ItemEntity;
import reactor.core.publisher.Mono;

public interface UpdateItem {
    Item update(ItemUpdateObject itemUpdateObject);
    Mono<Boolean> update(QualityCheckCommand itemUpdateCommand);
}
