package reactive.reactor.lambdaBasics;

import reactor.core.publisher.Mono;

public class HandlingErrors {

  public static void main(String[] args) {
    //fallbackErrorWithValue("KO");
    fallbackErrorWithMethod("OK");
  }

  public static void fallbackErrorWithValue(String input) {
    Mono.defer(() -> getPublisherWithError(input))
        .onErrorReturn(Exception1.class, "First fallback value")
        .onErrorReturn(RuntimeException.class, "Fallback value")
        .subscribe(System.out::println);
  }

  public static void fallbackErrorWithMethod(String input) {
    Mono.defer(() -> getPublisherWithError(input))
        .onErrorResume(Exception1.class, e -> fallbackMethod())
        .subscribe(System.out::println);
  }


  private static Mono<String> getPublisherWithError(String input) {
    if ("OK".equals(input)) {
      return Mono.just("OK");
    }

    return Mono.error(new Exception1("My error"));
  }

  public static Mono<String> fallbackMethod() {
    return Mono.just("fallback");
  }
}

class Exception1 extends RuntimeException {
  public Exception1(String message) {
    super(message);
  }
}
