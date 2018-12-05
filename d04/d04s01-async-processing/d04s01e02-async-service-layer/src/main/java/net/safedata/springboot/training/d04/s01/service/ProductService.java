package net.safedata.springboot.training.d04.s01.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@SuppressWarnings("unused")
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final AsyncComponent asyncComponent;

    @Autowired
    public ProductService(final AsyncComponent asyncComponent) {
        this.asyncComponent = asyncComponent;
    }

    public void callAllAsyncMethods() {
        // 1st stage - invoking the async methods
        LOGGER.info("Invoking an async method which doesn't have a return object...");
        asyncComponent.voidReturningAsyncCall();

        LOGGER.info("Invoking an async method which returns a Future object...");
        final Future<String> future = asyncComponent.asyncMethodReturningAFuture();

        LOGGER.info("Invoking an async method which returns a ListenableFuture object...");
        final ListenableFuture<String> listenableFuture = asyncComponent.asyncMethodReturningAListenableFuture();

        LOGGER.info("Invoking an async method which returns a CompletableFuture object...");
        final CompletableFuture<String> completableFuture = asyncComponent.asyncMethodReturningACompletableFuture();

        // 2nd stage - getting the returned values
        if (future.isDone()) {
            try {
                final String value = future.get();
                LOGGER.info("The returned future value is '{}'", value);
            } catch (final ExecutionException | InterruptedException e) {
                handleException(e);
            }
        }

        completableFuture.whenCompleteAsync((value, error) -> LOGGER.debug("{}", value));
        completableFuture.join();
    }

    public void asyncMethodReturningAFuture() {
        final Future<String> future = asyncComponent.asyncMethodReturningAFuture();

        try {
            getAndDisplayValue(future);
        } catch (final ExecutionException | InterruptedException e) {
            handleException(e);
        }
    }

    public void asyncMethodReturningAListenableFuture() {
        final ListenableFuture<String> listenableFuture = asyncComponent.asyncMethodReturningAListenableFuture();

        try {
            getAndDisplayValue(listenableFuture);
        } catch (final ExecutionException | InterruptedException e) {
            handleException(e);
        }
    }

    public void asyncMethodReturningACompletableFuture() {
        final CompletableFuture<String> completableFuture = asyncComponent.asyncMethodReturningACompletableFuture();

        try {
            getAndDisplayValue(completableFuture);
        } catch (final ExecutionException | InterruptedException e) {
            handleException(e);
        }
    }

    private void getAndDisplayValue(final Future<String> futureValue)
            throws ExecutionException, InterruptedException {

        if (futureValue.isDone()) {
            final String theValue = futureValue.get();
            System.out.println("The " + futureValue.getClass().getSimpleName() + " value is '" + theValue + "'");
        }
    }

    private void handleException(final Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
    }
}
