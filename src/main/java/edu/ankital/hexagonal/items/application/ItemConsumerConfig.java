package edu.ankital.hexagonal.items.application;

import edu.ankital.hexagonal.items.application.model.ItemUpdateCommand;
import edu.ankital.hexagonal.items.application.model.QualityCheckCommand;
import edu.ankital.hexagonal.items.core.ports.UpdateItem;
import edu.ankital.hexagonal.items.infrastructure.entity.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class ItemConsumerConfig {
    @Bean
    public Function<ItemUpdateCommand, Item> consumeItemUpdate(UpdateItem updateItem){
        return input -> updateItem.update(input);
    }

    @Bean
    public Function<QualityCheckCommand, Mono<Boolean>> consumeItemUpdateWithReactiveIO(UpdateItem updateItem){
        return input -> updateItem.update(input);
    }
}
