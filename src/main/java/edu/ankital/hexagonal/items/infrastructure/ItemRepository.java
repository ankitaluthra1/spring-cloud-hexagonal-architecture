package edu.ankital.hexagonal.items.infrastructure;

import edu.ankital.hexagonal.items.infrastructure.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ItemRepository extends CrudRepository<Item, Long> {
}
