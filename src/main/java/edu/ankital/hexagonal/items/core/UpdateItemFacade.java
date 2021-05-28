package edu.ankital.hexagonal.items.core;

import edu.ankital.hexagonal.items.application.model.QualityCheckCommand;
import edu.ankital.hexagonal.items.core.model.Item;
import edu.ankital.hexagonal.items.core.model.ItemUpdateObject;
import edu.ankital.hexagonal.items.core.ports.ItemDatabase;
import edu.ankital.hexagonal.items.core.ports.QualityControlCheck;
import edu.ankital.hexagonal.items.core.ports.UpdateItem;
import edu.ankital.hexagonal.items.infrastructure.entity.ItemEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateItemFacade implements UpdateItem {
    ItemDatabase itemDatabase;
    QualityControlCheck qualityControlCheck;

    public UpdateItemFacade(ItemDatabase itemDatabase, QualityControlCheck qualityControlCheck) {
        this.itemDatabase = itemDatabase;
        this.qualityControlCheck = qualityControlCheck;
    }

    @Override
    public Item update(ItemUpdateObject itemUpdateObject) {
        ItemEntity itemEntity =  itemDatabase.getItemById(itemUpdateObject.getItemId());
        int quantity = itemEntity.getQuantity() + itemUpdateObject.getQuantity();
        itemEntity.setQuantity(quantity);
        return Item.from(itemDatabase.saveOrUpdate(itemEntity));
    }

    @Override
    public Mono<Boolean> update(QualityCheckCommand itemUpdateCommand) {
        Mono<Boolean> qualityCheckFailedItems = qualityControlCheck.check(itemUpdateCommand.getProductName());
        return qualityCheckFailedItems.flatMap(check -> {
            if(!check){
                return Mono.fromRunnable(() -> itemDatabase.failQualityCheck(itemUpdateCommand.getProductName())).then(Mono.just(true));
            }
            return Mono.just(true);
        });
    }
}
