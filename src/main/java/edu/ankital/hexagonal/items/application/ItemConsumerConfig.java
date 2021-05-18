package edu.ankital.hexagonal.items.application;

import edu.ankital.hexagonal.items.application.model.ItemUpdateCommand;
import edu.ankital.hexagonal.items.core.ports.UpdateItem;
import edu.ankital.hexagonal.items.infrastructure.entity.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ItemConsumerConfig {
    @Bean
    public Function<ItemUpdateCommand, Item> consumeItemUpdate(UpdateItem updateItem){
        return input -> updateItem.update(input);
    }

    @Bean
    public Function<ItemUpdateCommand, Item> consumeItemUpdateWithReactiveIO(UpdateItem updateItem){
        return input -> updateItem.update(input);
    }
}
