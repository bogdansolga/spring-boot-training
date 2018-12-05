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
    public void voidReturningAsyncCall() {
        displayCurrentThreadName("voidReturningAsyncCall");
        LOGGER.info("Displaying a value asynchronously");
    }

    @Async
    public Future<String> asyncMethodReturningAFuture() {
        displayCurrentThreadName("asyncMethodReturningAFuture");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>("Returning a Future async value");
    }

    @Async
    public ListenableFuture<String> asyncMethodReturningAListenableFuture() {
        displayCurrentThreadName("asyncMethodReturningAListenableFuture");
        return new AsyncResult<>("Returning a ListenableFuture async value");
    }

    @Async
    CompletableFuture<String> asyncMethodReturningACompletableFuture() {
        displayCurrentThreadName("asyncMethodReturningACompletableFuture");
        return CompletableFuture.supplyAsync(() -> "Returned by the CompletableFuture");
    }

    private void displayCurrentThreadName(final String exampleName) {
        LOGGER.info("{} - running on the thread '{}'", exampleName, Thread.currentThread().getName());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
