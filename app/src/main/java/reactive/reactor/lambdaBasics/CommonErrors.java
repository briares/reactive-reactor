package reactive.reactor.lambdaBasics;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import utils.TimerUtil;

public class CommonErrors {

  public static void main(String[] args) {

    // thenAccept() example
    CompletableFuture.supplyAsync(() -> {
      return "Done";
    }, Executors.newFixedThreadPool(3)).thenAccept(value -> {
      System.out.println("Got result " + value);
    });

    //TimerUtil.waitSeconds(3);
  }
}
