package reactive.reactor.lambdaBasics;

import reactor.core.publisher.FluxSink;
import reactor.core.publisher.ReplayProcessor;

public class ReplayProcessorTest {

  public static void main(String[] args) {

  }

  private static void replayProcessor() {
    ReplayProcessor<Long> data = ReplayProcessor.create(3);
    data.subscribe(t -> System.out.println(t));
    FluxSink<Long> sink = data.sink();
    sink.next(10L);
    sink.next(11L);
    sink.next(12L);
    sink.next(13L);
    sink.next(14L);
    data.subscribe(t -> System.out.println(t));
  }

}
