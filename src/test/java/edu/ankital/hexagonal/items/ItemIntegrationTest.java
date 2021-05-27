package edu.ankital.hexagonal.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ankital.hexagonal.items.application.model.ItemUpdateCommand;
import edu.ankital.hexagonal.items.infrastructure.ItemRepository;
import edu.ankital.hexagonal.items.infrastructure.entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
@TestPropertySource(properties = "spring.cloud.stream.function.definition=consumeItemUpdate")
public class ItemIntegrationTest {
    @Autowired
    InputDestination inputDestination;

    @Autowired
    OutputDestination outputDestination;

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void dbClean(){
        itemRepository.deleteAll();
    }

    @Test
    public void shouldTestEndToEnd() throws IOException {
        Item entity = new Item(1, 10, "name");
        itemRepository.save(entity);

        ItemUpdateCommand itemUpdateCommand = new ItemUpdateCommand("5", "1");
        Message<ItemUpdateCommand> message = new GenericMessage<>(itemUpdateCommand);
        inputDestination.send(message);

        Message<byte[]> output = outputDestination.receive();

        Item item = new ObjectMapper().readValue(output.getPayload(), Item.class);

        assertThat(item.getQuantity()).isEqualTo(15);
    }

}