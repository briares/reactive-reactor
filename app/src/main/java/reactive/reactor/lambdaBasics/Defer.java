package reactive.reactor.lambdaBasics;

import reactor.core.publisher.Mono;
import utils.TimerUtil;

public class Defer {

  public static void main(String[] args) {
    //evaluateOnce();
    evaluateEver();
  }

  public static void evaluateEver() {
    // Evaluate on every subscriber
    Mono<String> publisher = Mono.defer(() -> getMessage("Hello"));
    publisher.subscribe((System.out::println));
    TimerUtil.waitSeconds(2);
    publisher.subscribe((System.out::println));
  }

  public static void evaluateOnce() {
    // Eager evaluation
    Mono<String> publisher = getMessage("Hello");
    publisher.subscribe((System.out::println));
    TimerUtil.waitSeconds(2);
    publisher.subscribe((System.out::println));
  }

  public static Mono<String> getMessage(String str) {
      return Mono.just(String.format("Call to Retrieve Message!! --> %s at: %s ", str, System.currentTimeMillis()));
  }

}
