package com.rafaelshayashi.reactive.teste;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    @Test
    public void fluxSubscribe(){
        Flux<String> flux = Flux.just("Rafael", "Simionato", "Hayashi")
                .log();

        StepVerifier.create(flux)
                .expectNext("Rafael", "Simionato", "Hayashi")
                .verifyComplete();
    }

    @Test
    public void fluxSubscribeNumbers() {
        Flux<Integer> flux = Flux.range(1, 5)
                .log();

        StepVerifier.create(flux)
                .expectNext(1, 2, 3, 4, 5)
                .verifyComplete();
    }
}
