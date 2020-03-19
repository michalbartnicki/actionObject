package pl.michalbartnicki.actionobject;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Proxy {
    private static final Proxy INSTANCE = new Proxy();

    public static Proxy getInstance() {
        return INSTANCE;
    }

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final ActionServant servant = new ActionServant();

    private Proxy() {
    }

    public Future action1(int timeout) {
        MethodRequest request = new Action1MethodRequest(servant, timeout);

        if (!request.canExecute()) {
            return CompletableFuture.completedFuture(null);
        }

        return executor.submit(request::execute);
    }

    public Future action2() {
        return null;
    }

    public void shutdown() {
        log.info("SHUTDOWN");
        executor.shutdown();
    }
}
