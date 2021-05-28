package edu.ankital.hexagonal.items.core.ports;


import edu.ankital.hexagonal.items.infrastructure.entity.ItemEntity;

public interface ItemDatabase {
    ItemEntity getItemById(Long id);
    ItemEntity saveOrUpdate(ItemEntity itemEntity);
    void failQualityCheck(String name);
}
