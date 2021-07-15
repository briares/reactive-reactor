package reactive.reactor.lambdaBasics;

import com.github.javafaker.Faker;
import com.github.javafaker.Superhero;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class BackPressureTest {

  public static void main(String[] args) {
    Flux.range(1,150)
        .map(a ->Faker.instance().superhero())
        .subscribe(new SuperheroSubscription());

  }
}

class SuperheroSubscription implements Subscriber<Superhero> {
  private Subscription s;
  int counter;

  @Override
  public void onSubscribe(Subscription subscription) {
    System.out.println("onSubscribe");
    this.s = subscription;
    System.out.println("Requesting 10 emissions");
    s.request(10);
  }

  @Override
  public void onNext(Superhero item) {
    System.out.println("onNext " + item.name());
    counter++;
    if (counter % 10 == 0) {
      System.out.println("Requesting 10 emissions");
      s.request(10);
    }
  }

  @Override
  public void onError(Throwable throwable) {
    System.err.println("onError");
  }

  @Override
  public void onComplete() {
    System.out.println("onComplete");
  }

}
