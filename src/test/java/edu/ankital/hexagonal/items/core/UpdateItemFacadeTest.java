package edu.ankital.hexagonal.items.core;

import edu.ankital.hexagonal.items.application.model.ItemUpdateCommand;
import edu.ankital.hexagonal.items.application.model.QualityCheckCommand;
import edu.ankital.hexagonal.items.core.ports.QualityControlCheck;
import edu.ankital.hexagonal.items.infrastructure.ItemDatabaseAdapter;
import edu.ankital.hexagonal.items.infrastructure.entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateItemFacadeTest {

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(UpdateItemFacadeTest.class);
    }

    @Test
    void shouldUpdateItemWithGivenCommand() {
        //Arrange
        Item inputItem = new Item(1, 10, "input-item");
        Item outputItem = new Item(1, 15, "output-item");
        ItemDatabaseAdapter mockItemDatabase = mock(ItemDatabaseAdapter.class);
        QualityControlCheck qualityControlCheck = mock(QualityControlCheck.class);
        when(mockItemDatabase.getItemById(any())).thenReturn(inputItem);
        when(mockItemDatabase.saveOrUpdate(any())).thenReturn(outputItem);
        ItemUpdateCommand command = new ItemUpdateCommand(5, 1);
        //Act
        UpdateItemFacade updateItemFacade = new UpdateItemFacade(mockItemDatabase, qualityControlCheck);
        Item result = updateItemFacade.update(command);
        //Assert
        assertThat(result).usingRecursiveComparison().isEqualTo(outputItem);
    }

    @Test
    void shouldCallGetItemByIdWithGivenId() {
        //Arrange
        Item inputItem = new Item(1, 10, "input-item");
        Item outputItem = new Item(1, 15, "output-itrm");
        ItemDatabaseAdapter mockItemDatabase = mock(ItemDatabaseAdapter.class);
        QualityControlCheck qualityControlCheck = mock(QualityControlCheck.class);
        when(mockItemDatabase.getItemById(any())).thenReturn(inputItem);
        when(mockItemDatabase.saveOrUpdate(any())).thenReturn(outputItem);
        ItemUpdateCommand command = new ItemUpdateCommand(5, 1);
        //Act
        UpdateItemFacade updateItemFacade = new UpdateItemFacade(mockItemDatabase, qualityControlCheck);
        Item result = updateItemFacade.update(command);
        //Assert
        verify(mockItemDatabase).getItemById(1L);
    }

    @Test
    void shouldCallSaveOrUpdateItemWithGiven() {
        //Arrange
        Item inputItem = new Item(1, 10, "input-item");
        Item outputItem = new Item(1, 15, "output-item");
        ItemDatabaseAdapter mockItemDatabase = mock(ItemDatabaseAdapter.class);
        QualityControlCheck qualityControlCheck = mock(QualityControlCheck.class);
        when(mockItemDatabase.getItemById(any())).thenReturn(inputItem);
        when(mockItemDatabase.saveOrUpdate(any())).thenReturn(outputItem);
        ItemUpdateCommand command = new ItemUpdateCommand(5, 1);
        //Act
        UpdateItemFacade updateItemFacade = new UpdateItemFacade(mockItemDatabase, qualityControlCheck);
        Item result = updateItemFacade.update(command);
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