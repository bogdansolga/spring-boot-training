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
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final AsyncComponent asyncComponent;

    @Autowired
    public ProductService(final AsyncComponent asyncComponent) {
        this.asyncComponent = asyncComponent;
    }

    @SuppressWarnings("unused")
    public void callAllAsyncMethods() {
        LOGGER.info("voidAsyncCall");
        asyncComponent.voidAsyncCall();

        LOGGER.info("getFuture");
        final Future<String> future = asyncComponent.getFuture();

        LOGGER.info("getListenableFuture");
        final ListenableFuture<String> listenableFuture = asyncComponent.getListenableFuture();

        LOGGER.info("getCompletableFuture");
        final CompletableFuture<String> completableFuture = asyncComponent.getCompletableFuture();

        if (future.isDone()) {
            try {
                final String value = future.get();
                LOGGER.info("The returned future value is '{}'", value);
            } catch (final ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void voidAsyncCall() {
        asyncComponent.voidAsyncCall();
    }

    public void getFuture() {
        final Future<String> future = asyncComponent.getFuture();

        try {
            getAndDisplayValue(future);
        } catch (final ExecutionException | InterruptedException e) {
            handleException(e);
        }
    }

    public void getListenableFuture() {
        final ListenableFuture<String> listenableFuture = asyncComponent.getListenableFuture();

        try {
            getAndDisplayValue(listenableFuture);
        } catch (final ExecutionException | InterruptedException e) {
            handleException(e);
        }
    }

    public void getCompletableFuture() {
        final CompletableFuture<String> completableFuture = asyncComponent.getCompletableFuture();

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
        ex.printStackTrace();
    }
}
