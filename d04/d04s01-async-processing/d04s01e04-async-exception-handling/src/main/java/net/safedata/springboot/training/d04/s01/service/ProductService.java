package net.safedata.springboot.training.d04.s01.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void voidAsyncCall(final String firstParameter, final String secondParameter) {
        asyncComponent.voidAsyncCall(firstParameter, secondParameter);
    }

    public void getFuture() {
        final Future<String> future = asyncComponent.getFuture();

        try {
            getAndDisplayValue(future, "Future");
        } catch (final ExecutionException | InterruptedException e) {
            handleException(e);
        }
    }

    public void getCompletableFuture() {
        final CompletableFuture<String> completableFuture = asyncComponent.getCompletableFuture();

        try {
            getAndDisplayValue(completableFuture, "CompletableFuture");
        } catch (final ExecutionException | InterruptedException e) {
            handleException(e);
        }
    }

    private void getAndDisplayValue(final Future<String> futureValue, final String className)
            throws ExecutionException, InterruptedException {

        if (futureValue.isDone()) {
            LOGGER.info("The {} value is '{}'", className, futureValue.get());
        } else {
            System.out.println(futureValue.isCancelled());
            System.out.println(futureValue.get());
        }
    }

    private void handleException(final Exception ex) {
        ex.printStackTrace();
    }
}
