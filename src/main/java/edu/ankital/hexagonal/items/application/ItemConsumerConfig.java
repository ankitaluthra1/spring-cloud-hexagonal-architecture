package edu.ankital.hexagonal.items.application;

import edu.ankital.hexagonal.items.core.ports.UpdateItem;
import edu.ankital.hexagonal.items.application.model.Item;
import edu.ankital.hexagonal.items.application.model.ItemUpdateCommand;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ItemConsumerConfig {
    @Bean
    public Function<ItemUpdateCommand, Item> consumeItemUpdate(UpdateItem updateItem){
        return input -> updateItem.update(input);
    }
}
