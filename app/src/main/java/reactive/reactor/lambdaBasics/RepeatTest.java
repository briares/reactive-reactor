package reactive.reactor.lambdaBasics;

import java.time.Duration;
import reactor.core.publisher.Mono;
import reactor.retry.Repeat;

public class RepeatTest {
  public static void main(String[] args) {
    //repeatFixedBackoff();
    repeatExponentialBackoff();
    /*
    Mono.defer(() -> Mono.just(Faker.instance().backToTheFuture().character()))
        .repeat(5, () -> true)
        .subscribe("First ")
        .blockLast();
*/
  }

  public static void repeatFixedBackoff() {
    Mono.just("test")
        .repeatWhen(Repeat.times(Long.MAX_VALUE)
            .fixedBackoff(Duration.ofSeconds(1)))
        .take(5)
        .log()
        .blockLast();
  }

  public static void repeatExponentialBackoff() {
    Mono.just("test")
        .repeatWhen(Repeat.times(Long.MAX_VALUE)
            .exponentialBackoff(Duration.ofSeconds(1), Duration.ofSeconds(100)))
        .take(5)
        .log()
        .blockLast();
  }

}
