package reactive.reactor.lambdaBasics;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.util.Scanner;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;

public class BlockhoundTest {

  public static void main(String[] args) throws IOException {
    // Example.java
    BlockHound.install();
    basicTest();
    //openSocket (8302);

  }

  private static void basicTest() {

    Mono.delay(Duration.ofSeconds(1))
        .doOnNext(it -> {
          try {
            Thread.sleep(100);
          }
          catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        })
        .block();
  }

  private static void openSocket(int port) throws IOException {
    int num,temp;
    String s;
    ServerSocket s1=new ServerSocket(port);
    Socket ss=s1.accept();
    Scanner sc=new Scanner(ss.getInputStream());
    s=ss.toString();
    num =sc.nextInt();
    temp=num*num;
    PrintStream p=new PrintStream(ss.getOutputStream());
    p.println(temp);
    System.out.println("Server started.. ");
  }

}
