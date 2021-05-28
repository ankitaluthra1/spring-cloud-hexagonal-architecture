package edu.ankital.hexagonal.items.core;

import edu.ankital.hexagonal.items.application.model.QualityCheckCommand;
import edu.ankital.hexagonal.items.core.model.Item;
import edu.ankital.hexagonal.items.core.model.ItemUpdateObject;
import edu.ankital.hexagonal.items.core.ports.QualityControlCheck;
import edu.ankital.hexagonal.items.infrastructure.ItemDatabaseAdapter;
import edu.ankital.hexagonal.items.infrastructure.entity.ItemEntity;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateItemEntityFacadeTest {

    @Test
    public void shouldUpdateItemWithGivenCommand() {
        //Arrange
        ItemEntity inputItemEntity = new ItemEntity(1, 10, "input-item");
        ItemEntity outputItemEntity = new ItemEntity(1, 15, "output-item");
        ItemDatabaseAdapter mockItemDatabase = mock(ItemDatabaseAdapter.class);
        QualityControlCheck qualityControlCheck = mock(QualityControlCheck.class);
        when(mockItemDatabase.getItemById(any())).thenReturn(inputItemEntity);
        when(mockItemDatabase.saveOrUpdate(any())).thenReturn(outputItemEntity);
        ItemUpdateObject itemUpdateObject = new ItemUpdateObject(1, 5);
        //Act
        UpdateItemFacade updateItemFacade = new UpdateItemFacade(mockItemDatabase, qualityControlCheck);
        Item result = updateItemFacade.update(itemUpdateObject);
        //Assert
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getQuantity()).isEqualTo(15);
    }

    @Test
    void shouldCallGetItemByIdWithGivenId() {
        //Arrange
        ItemEntity inputItemEntity = new ItemEntity(1, 10, "input-item");
        ItemEntity outputItemEntity = new ItemEntity(1, 15, "output-itrm");
        ItemDatabaseAdapter mockItemDatabase = mock(ItemDatabaseAdapter.class);
        QualityControlCheck qualityControlCheck = mock(QualityControlCheck.class);
        when(mockItemDatabase.getItemById(any())).thenReturn(inputItemEntity);
        when(mockItemDatabase.saveOrUpdate(any())).thenReturn(outputItemEntity);
        ItemUpdateObject itemUpdateObject = new ItemUpdateObject(1, 5);
        //Act
        UpdateItemFacade updateItemFacade = new UpdateItemFacade(mockItemDatabase, qualityControlCheck);
        updateItemFacade.update(itemUpdateObject);
        //Assert
        verify(mockItemDatabase).getItemById(1L);
    }

    @Test
    void shouldCallSaveOrUpdateItemWithGiven() {
        //Arrange
        ItemEntity inputItemEntity = new ItemEntity(1, 10, "input-item");
        ItemEntity outputItemEntity = new ItemEntity(1, 15, "output-item");
        ItemDatabaseAdapter mockItemDatabase = mock(ItemDatabaseAdapter.class);
        QualityControlCheck qualityControlCheck = mock(QualityControlCheck.class);
        when(mockItemDatabase.getItemById(any())).thenReturn(inputItemEntity);
        when(mockItemDatabase.saveOrUpdate(any())).thenReturn(outputItemEntity);
        ItemUpdateObject itemUpdateObject = new ItemUpdateObject(1, 5);
        //Act
        UpdateItemFacade updateItemFacade = new UpdateItemFacade(mockItemDatabase, qualityControlCheck);
        updateItemFacade.update(itemUpdateObject);
        //Assert
        verify(mockItemDatabase).getItemById(1L);
    }

    @Test
    void shouldCallUpdateQualityCheckForGivenItemUpdateCommand() {
        ItemDatabaseAdapter mockItemDatabase = mock(ItemDatabaseAdapter.class);
        QualityControlCheck qualityControlCheck = mock(QualityControlCheck.class);
        doNothing().when(mockItemDatabase).failQualityCheck(any());
        when(qualityControlCheck.check(any())).thenReturn(Mono.just(false));
        QualityCheckCommand command = new QualityCheckCommand("some product name");
        UpdateItemFacade updateItemFacade = new UpdateItemFacade(mockItemDatabase, qualityControlCheck);

        updateItemFacade.update(command).block();

        verify(mockItemDatabase, times(1)).failQualityCheck("some product name");
    }
}