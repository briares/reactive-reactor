package reactive.reactor.lambdaBasics;

import java.util.function.Consumer;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import utils.TimerUtil;

public class JavaStreamsVsReactor {
  public static void main(String[] args) throws InterruptedException {
    javaStream();
    //javaFluxSubscribe();
    //javaFluxSubscribeOn();
    //test();
    System.out.println("done -> main");

    //TimerUtil.waitSeconds(8);
  }

  private static String longRunningOperation(String source) {
    System.out.println("Before -> longRunningOperation for source: " + source);
    TimerUtil.waitSeconds(1);
    System.out.println("Done -> longRunningOperation for source: " + source);
    return source.toUpperCase();
  }

  private static void javaStream() {
    Stream stream = Stream.of("T01", "T02", "T03", "T04")
        .map(JavaStreamsVsReactor::longRunningOperation);
    stream.forEach(System.out::println);
    System.out.println("done -> javaStream");
  }

  private static void javaFluxSubscribe() {
    Stream<String> stringStream = Stream.of("T01", "T02", "T03", "T04");
    Flux<String> fluxString = Flux.fromStream(stringStream)
        .map(JavaStreamsVsReactor::longRunningOperation);
    fluxString.subscribe(System.out::println);

    System.out.println("done -> javaFluxSubscribe");
  }

  private static void javaFluxSubscribeOn() {
    // asynchronous in other thread than main
    Consumer<String> consumer = s -> System.out.println(s + " : " + Thread.currentThread().getName());
    Stream<String> stringStream = Stream.of("T01", "T02", "T03", "T04");
    Flux.fromStream(stringStream)
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(System.out::println);

    System.out.println("done -> javaFluxSubscribeOn");
  }

  private static void test() {
    Consumer<Integer> consumer = s -> System.out.println(s + " : " + Thread.currentThread().getName());

    Flux.range(1, 5)
        .doOnNext(consumer)
        .map(i -> {
          System.out.println("Inside map the thread is " + Thread.currentThread().getName());
          return i * 10;
        })
        .publishOn(Schedulers.newElastic("First_PublishOn()_thread"))
        .doOnNext(consumer)
        .publishOn(Schedulers.newElastic("Second_PublishOn()_thread"))
        .doOnNext(consumer)
        .subscribeOn(Schedulers.newElastic("subscribeOn_thread"))
        .subscribe();
  }

}
