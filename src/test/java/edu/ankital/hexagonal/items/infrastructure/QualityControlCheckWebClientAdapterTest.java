package edu.ankital.hexagonal.items.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = { TestConfig.class })
class QualityControlCheckWebClientAdapterTest {

    @Autowired
    QualityControlCheckWebClientAdapter qualityControlCheckWebClientAdapter;

    public static MockWebServer mockWebServer;
    public static ObjectMapper objectMapper;


    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        objectMapper = new ObjectMapper();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @Disabled("not working")
    void shouldCallWebClientGetMethodWithGivenProductName() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(true)));
        Mono<Boolean> result = qualityControlCheckWebClientAdapter.check("some product name");

        result.block();

        RecordedRequest recordedRequest = mockWebServer.takeRequest(1, TimeUnit.SECONDS);

        assertThat(recordedRequest.getMethod()).isEqualTo("GET");
        assertThat(recordedRequest.getPath()).isEqualTo("/quality-checks");
    }
}

@Configuration
@Import({ QualityControlCheckWebClientAdapter.class, WebClientConfig.class })
class TestConfig {
}