package net.safedata.springboot.training.d04.s01.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
public class AsyncComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncComponent.class);

    @Async
    void voidAsyncCall() {
        displayCurrentThread();
        LOGGER.info("Displaying a value asynchronously");
    }

    @Async
    Future<String> getFuture() {
        displayCurrentThread();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("Returning a Future async value");
    }

    @Async
    ListenableFuture<String> getListenableFuture() {
        displayCurrentThread();
        return new AsyncResult<>("Returning a ListenableFuture async value");
    }

    @Async
    CompletableFuture<String> getCompletableFuture() {
        displayCurrentThread();
        return CompletableFuture.supplyAsync(() -> "Returned by the CompletableFuture");
    }

    private void displayCurrentThread() {
        LOGGER.info("Running on the thread '{}'", Thread.currentThread().getName());
    }
}
