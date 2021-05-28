package edu.ankital.hexagonal.items.infrastructure;

import edu.ankital.hexagonal.items.infrastructure.entity.ItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {

    @Modifying
    @Query("update items set qualityAssured=$2 where name=$1")
    public void updateQualityAssured(String name, int qualityAssured);
}
