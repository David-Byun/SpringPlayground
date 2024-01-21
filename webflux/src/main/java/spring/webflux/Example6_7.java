package spring.webflux;

import reactor.core.publisher.Flux;

public class Example6_7 {
    public static void main(String[] args) {
        Flux.concat(
                        Flux.just("Mercury", "Venus"),
                        Flux.just("Mars", "Jupiter"),
                        Flux.just("Uranus", "Neptune"))
                .collectList()
                .subscribe(planets -> System.out.println(planets));
    }
}
