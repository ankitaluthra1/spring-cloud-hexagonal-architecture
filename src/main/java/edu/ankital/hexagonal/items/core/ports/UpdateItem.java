package edu.ankital.hexagonal.items.core.ports;

import edu.ankital.hexagonal.items.application.model.ItemUpdateCommand;
import edu.ankital.hexagonal.items.application.model.QualityCheckCommand;
import edu.ankital.hexagonal.items.infrastructure.entity.Item;
import reactor.core.publisher.Mono;

public interface UpdateItem {
    public Item update(ItemUpdateCommand itemUpdateCommand);
    public Mono<Void> update(QualityCheckCommand itemUpdateCommand);
}
