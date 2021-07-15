package reactive.reactor.lambdaBasics;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class BasicPublisher {

  public static void main(String[] args) {

    //.subscribeOn(Schedulers.boundedElastic())

    Mono.defer(() -> Mono.error(new RuntimeException("Error")))
        //.doOnNext(System.out.println("OK"))
        .log()
        .doOnError(e -> e.getMessage().equals("Error"), e -> System.err.println("Error handle"))
        .subscribe(System.out::println);
    //Flux.just("hello",  "flux");
  }

  private static String getValue() {
    return "A";
  }

}

