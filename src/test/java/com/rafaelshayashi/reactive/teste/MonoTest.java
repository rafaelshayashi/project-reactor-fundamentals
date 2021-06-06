package com.rafaelshayashi.reactive.teste;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

    private static final Logger logger = LoggerFactory.getLogger(MonoTest.class);

    @Test
    public void itsAlive() {
        logger.info("Testing everything is ok");
    }

    @Test
    public void monoSubscribe() {
        String name = "Anakin Skywalker";
        Mono<String> stringMono = Mono.just(name);

        stringMono.subscribe();

        StepVerifier.create(stringMono)
                .expectNext(name)
                .verifyComplete();
    }

    @Test
    public void monoSubscribeConsumerError() {
        String name = "Anakin Skywalker";
        Mono<Object> mono = Mono.just(name)
                .map(s -> {
                    throw new RuntimeException("Something bad happen");
                });

        StepVerifier.create(mono)
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void monoSubscribeConsumerComplete() {
        String name = "Anaking Skywalker";
        Mono<String> mono = Mono.just(name)
                .map(String::toUpperCase);

        mono.subscribe(
                s -> logger.info("name [{}]", s),
                s -> logger.error("Somenthing bad happen"),
                () -> logger.info("It's completed"));

        StepVerifier.create(mono)
                .expectNext(name.toUpperCase())
                .verifyComplete();
    }

    @Test
    public void monoOfNull() {
        String name = null;
        Mono<String> stringMono = Mono
                .just(name)
                .log();

        stringMono.subscribe();
    }
}
