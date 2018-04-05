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
    public void voidAsyncCall(final String firstParameter, final String secondParameter) {
        displayCurrentThreadName();
        sleepALittle();

        LOGGER.info("Displaying a value asynchronously");

        //throw new IllegalArgumentException("An exception thrown from an async call");
    }

    @Async("defaultExecutor")
    public Future<String> getFuture() {
        displayCurrentThreadName();
        sleepALittle();

        return new AsyncResult<>("Returning a Future async value");
    }

    @Async
    public CompletableFuture<String> getCompletableFuture() {
        displayCurrentThreadName();
        sleepALittle();

        return CompletableFuture.supplyAsync(() -> {
            //throw new RuntimeException("From CompletableFuture");
            return "something";
        }, defaultExecutor);
    }

    private void displayCurrentThreadName() {
        LOGGER.info("Running on the thread '{}'", Thread.currentThread().getName());
    }

    private void sleepALittle() {
        try {
            LOGGER.debug("Simulating a long running operation...");
            Thread.sleep(1500);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
