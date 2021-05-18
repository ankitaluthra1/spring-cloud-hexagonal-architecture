package edu.ankital.hexagonal.items.infrastructure;

import edu.ankital.hexagonal.items.core.ports.QualityControlCheck;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class QualityControlCheckWebClientAdapter implements QualityControlCheck {
    WebClient webClient;
    public QualityControlCheckWebClientAdapter(WebClient webClient){
        this.webClient = webClient;
    }

    @Override
    public Mono<Boolean> check(String productName) {
        return webClient.get().uri("/quality-checks")
                .attribute("product-name", productName).retrieve().bodyToMono(Boolean.class);
    }
}
