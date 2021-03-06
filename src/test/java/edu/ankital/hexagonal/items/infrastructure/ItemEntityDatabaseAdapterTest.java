package edu.ankital.hexagonal.items.infrastructure;

import edu.ankital.hexagonal.items.infrastructure.entity.ItemEntity;
import edu.ankital.hexagonal.items.infrastructure.exceptions.ItemNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItemEntityDatabaseAdapterTest {

    @Test
    void shouldGetItemById() {
        ItemRepository itemRepository = mock(ItemRepository.class);
        ItemEntity mockItemEntity = new ItemEntity(1L, 10, "some-item");
        when(itemRepository.findById(any())).thenReturn(Optional.of(mockItemEntity));
        ItemDatabaseAdapter itemDatabaseAdapter = new ItemDatabaseAdapter(itemRepository);

        ItemEntity result = itemDatabaseAdapter.getItemById(1L);

        assertThat(result).usingRecursiveComparison().isEqualTo(mockItemEntity);
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhenItemIsNotPresent() {
        ItemRepository itemRepository = mock(ItemRepository.class);
        when(itemRepository.findById(any())).thenReturn(Optional.empty());
        ItemDatabaseAdapter itemDatabaseAdapter = new ItemDatabaseAdapter(itemRepository);

        Exception exception = assertThrows(ItemNotFoundException.class, () -> {
            itemDatabaseAdapter.getItemById(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("Item with id: 1 not present");
    }

    @Test
    void shouldCallSaveWithGivenItem() {
        ItemRepository itemRepository = mock(ItemRepository.class);
        ItemEntity mockItemEntity = new ItemEntity(1L, 10, "some-item");
        when(itemRepository.save(any())).thenReturn(mockItemEntity);
        ItemDatabaseAdapter itemDatabaseAdapter = new ItemDatabaseAdapter(itemRepository);

       itemDatabaseAdapter.saveOrUpdate(mockItemEntity);

       verify(itemRepository).save(mockItemEntity);

    }
}