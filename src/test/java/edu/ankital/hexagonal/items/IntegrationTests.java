package edu.ankital.hexagonal.items;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
public class IntegrationTests {

    @Test
    private void performTest(int messageInput) {
        Message<Integer> message = new GenericMessage<>(messageInput);
        inputDestination.send(message);

        Message<byte[]> output = outputDestination.receive();
        assertThat(output).as("Failed to receive a message").isNotNull();

        BigInteger primeNumber = new ObjectMapper().readValue(output.getPayload(), BigInteger.class);

        assertThat(primeNumber.isProbablePrime(10)).isTrue();
    }

}
