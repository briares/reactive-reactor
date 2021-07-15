package reactive.reactor.lambdaBasics;

import java.util.stream.Stream;
import utils.TimerUtil;

public class LazyBehavior {
  public static void main(String[] args)  {
    Stream stream = Stream.of("hello", "world")
                          .map(LazyBehavior::longRunningOperation);
    //stream.forEach(System.out::println);
    System.out.println("done");
  }

  private static String longRunningOperation(String source) {
    TimerUtil.waitSeconds(2);
    System.out.println("Done -> longRunningOperation");
    return source.toUpperCase();
  }
}
