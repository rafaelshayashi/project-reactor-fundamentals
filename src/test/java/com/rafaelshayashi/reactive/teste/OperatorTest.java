package com.rafaelshayashi.reactive.teste;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class OperatorTest {

    private static final Logger logger = LoggerFactory.getLogger(MonoTest.class);

    @Test
    public void mergeOperator(){
        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.just("c", "d");

        Flux<String> flux = Flux.merge(flux1, flux2)
                .log();

        flux.subscribe(logger::info);

        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext("a", "b", "c", "d")
                .expectComplete()
                .verify();
    }
}
