package reactive.reactor.lambdaBasics;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class FluxManual {

  public static void main(String[] args) {
    Flux<Integer> numberGenerator = Flux.create(x -> {
      System.out.println("Requested Events :" + x.requestedFromDownstream());
      int number = 1;
      while(number < 100) {
        x.next(number);
        number++;
      }
      x.complete();
    });

    //CountDownLatch latch = new CountDownLatch(1);
    numberGenerator
        .onBackpressureDrop(x -> System.out.println("Dropped :"+x))
        .subscribe(new BaseSubscriber<Integer>() {

        });
  }

}
