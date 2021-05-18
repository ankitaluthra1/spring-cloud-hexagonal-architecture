package edu.ankital.hexagonal.items.infrastructure;

import edu.ankital.hexagonal.items.core.ports.QualityControlCheck;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class QualityControlCheckWebClientAdapterTest {

    @Test
    void shouldCallWebClientGetMethodWithGivenProductName() {
        WebClient webclient = mock(WebClient.class);
        QualityControlCheckWebClientAdapter adapter = new QualityControlCheckWebClientAdapter(webclient);
        adapter.check("some product name");
    }
}