package spring.webflux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Example6_5 {

    public static void main(String[] args) {
        Flux<String> flux =
                Mono.justOrEmpty("Steve")
                        .concatWith(Mono.justOrEmpty("Jobs"));

        flux.subscribe(System.out::println);
    }
}
