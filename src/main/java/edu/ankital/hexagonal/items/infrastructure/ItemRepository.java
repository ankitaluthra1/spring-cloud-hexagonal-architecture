package edu.ankital.hexagonal.items.infrastructure;

import edu.ankital.hexagonal.items.application.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
