package reactive.reactor.lambdaBasics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import reactive.reactor.lambdaBasics.model.Category;
import reactive.reactor.lambdaBasics.model.Item;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Filtering {
  public static void main(String[] args) {

    Flux.fromIterable(getItems(10))
        .doOnNext(n -> System.out.println("Next 1: " + n))
        .doOnNext(n -> System.out.println("Next 2: " +n ))
        .subscribe(System.out::println);
  }



  public static void scheduler() {
    Scheduler schedulerA = Schedulers.newParallel("Scheduler A");
    Scheduler schedulerB = Schedulers.newParallel("Scheduler B");

    Flux.fromIterable(getItems(10))
        .doOnNext(n -> System.out.println("Next 1: " + n))
        .publishOn(schedulerA)
        .doOnNext(n -> System.out.println("Fourth map: " + Thread.currentThread().getName()))
        .doOnNext(n -> System.out.println("Next 2: " +n ))
        .publishOn(schedulerB)
        .subscribeOn(schedulerA)
        .doOnNext(n -> System.out.println("Fourth map: " + Thread.currentThread().getName()))
        .blockLast();
  }


  private static List<Item> getItems(int numberOfItems) {
    List<Category> categories = getCategories(4);
    List<Item> items = new ArrayList<>();

    for (int i = 0; i < categories.size(); i++) {
      if (i == 3) {
        continue;
      }

      for (int j = 1; j <= numberOfItems; j++) {
        items.add(Item.builder().name("ITEM_" + (j + (i  * numberOfItems))).category(categories.get(i)).price(BigDecimal.valueOf((i + 1) * j)).build());
      }
    }

    return items;
  }

  private static List<Item> getItemsWithError(int numberOfItems) {
    List<Category> categories = getCategories(4);
    List<Item> items = new ArrayList<>();

    for (int i = 0; i < categories.size(); i++) {
      if (i == 3) {
        continue;
      }

      for (int j = 1; j <= numberOfItems; j++) {
        items.add(Item.builder().name("ITEM_" + (j + (i  * numberOfItems))).category(categories.get(i)).price(BigDecimal.valueOf((i + 1) * j)).build());
      }
    }

    return items;
  }


  private static List<Category> getCategories(int number) {
    List<Category> categories = new ArrayList<>();

    for (int i=0; i < number; i++) {
      categories.add(Category.builder().name("CATEGORY_" + (i + 1)).build());
    }

    return categories;
  }

}
