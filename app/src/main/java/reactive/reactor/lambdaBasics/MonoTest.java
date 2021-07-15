package reactive.reactor.lambdaBasics;

import reactor.core.publisher.Mono;

public class MonoTest {

  public static void main(String[] args) {
    Mono<String> howManyMonos = Mono.just("hello mono")
        .map(String::toUpperCase)
        .map(s -> s.concat(", done"));

    //howManyMonos.subscribe(System.out::println);
  }

}
