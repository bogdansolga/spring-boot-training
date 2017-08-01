package net.safedata.springboot.training.d04.s01.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

@Component
@SuppressWarnings("unused")
public class AsyncComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncComponent.class);

    private final Executor defaultExecutor;

    @Autowired
    public AsyncComponent(final Executor defaultExecutor) {
        this.defaultExecutor = defaultExecutor;
    }

    @Async
    void voidAsyncCall(final String firstParameter, final String secondParameter) {
        displayCurrentThread();
        LOGGER.info("Displaying a value asynchronously");

        throw new RuntimeException("An exception thrown from an async call");
    }

    @Async("defaultExecutor")
    Future<String> getFuture() {
        displayCurrentThread();
        return new AsyncResult<>("Returning a Future async value");
    }

    @Async
    CompletableFuture<String> getCompletableFuture() {
        displayCurrentThread();
        return CompletableFuture.supplyAsync(() -> {
            //throw new RuntimeException("From CompletableFuture");
            return "something";
        }, defaultExecutor);
    }

    private void displayCurrentThread() {
        LOGGER.info("Running on the thread '{}'", Thread.currentThread().getName());
    }
}
