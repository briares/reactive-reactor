package reactive.reactor.lambdaBasics;

import java.time.Duration;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import utils.TimerUtil;

public class ColdPublisher {
  public static void main(String[] args) {
    //coldPublisherExample();
    hotPublisherExample();
  }

  public static void coldPublisherExample() {
    Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1));
    TimerUtil.waitSeconds(2);
    System.out.println("Before A subscription");
    intervalFlux.subscribe(i -> System.out.println(String.format("Subscriber A, value: %d", i)));
    TimerUtil.waitSeconds(2);
    System.out.println("Before B subscription");
    intervalFlux.subscribe(i -> System.out.println(String.format("Subscriber B, value: %d", i)));
    TimerUtil.waitSeconds(5);
  }

  public static void hotPublisherExample() {
    Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1));
    ConnectableFlux<Long> intervalCF = intervalFlux.publish();
    intervalCF.connect();
    TimerUtil.waitSeconds(2);
    intervalCF.subscribe(i -> System.out.println(String.format("Subscriber A, value: %d", i)));
    TimerUtil.waitSeconds(2);
    intervalCF.subscribe(i -> System.out.println(String.format("Subscriber B, value: %d", i)));
    TimerUtil.waitSeconds(2);
  }
}
