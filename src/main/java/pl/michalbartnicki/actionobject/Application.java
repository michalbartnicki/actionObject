package pl.michalbartnicki.actionobject;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    @SneakyThrows
    public static void main(String... args) {
        List<Thread> threads = new LinkedList<>();
        new Random(0) // predictable results wanted
            .ints(10, 100, 1000)
            .forEach(i -> {
                Thread thread = invokeAction1FromNewThread(i);
                threads.add(thread);
            });

        for (Thread thread : threads) {
            thread.join();
        }

        Proxy.getInstance().shutdown();
    }

    private static Thread invokeAction1FromNewThread(int i) {
        Thread thread = new Thread(() -> {
            log.info("action " + i);

            Future future = Proxy.getInstance().action1(i);
            while (!future.isDone()) {
                log.info("action " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error("Failed to sleep.");
                }
            }
        });

        thread.start();
        return thread;
    }
}
