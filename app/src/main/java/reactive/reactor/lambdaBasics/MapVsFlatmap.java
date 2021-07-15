package reactive.reactor.lambdaBasics;

import java.util.stream.Stream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MapVsFlatmap {

  public static void main(String[] args) {
    //mapExample();
    //flatmapExample();
    mapStreamsExample();
  }

  public static void mapExample() {
    Flux.range(0, 1000)
        .map(i -> i++)
        .subscribe(x -> System.out.println(x));
  }

  public static void flatmapExample() {
    Flux.range(1, 1000)
        //.log()
        .flatMap(value ->
                Mono.just(Integer.valueOf(value))
                    .subscribeOn(Schedulers.boundedElastic()))
        //.log()
        .subscribe(value -> {
          System.out.println("Consumed: " + value);
        });

  }

  public static void mapStreamsExample() {
    Stream.of("One", "Two")
        .parallel()
        .filter(s -> s.contains("o"))
        .forEach(System.out::println);
  }

}
