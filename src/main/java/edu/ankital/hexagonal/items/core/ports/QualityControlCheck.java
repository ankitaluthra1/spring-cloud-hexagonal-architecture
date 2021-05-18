package edu.ankital.hexagonal.items.core.ports;

import reactor.core.publisher.Mono;

public interface QualityControlCheck {
     Mono<Boolean> check(String productName);
}
