package net.safedata.springboot.training.d04.s01.controller;

import net.safedata.springboot.training.d04.s01.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(
        path = "/product",
        method = RequestMethod.GET
)
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(
            path = "/sync/{id}"
    )
    public Product getProduct(@PathVariable final int id) {
        longRunningOperation();
        return new Product(id, "Tablet");
    }

    @RequestMapping(
            path = "/async/{id}"
    )
    public DeferredResult<ResponseEntity<?>> getAsyncProduct(@PathVariable final int id) {
        final DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        deferredResult.onTimeout(() -> deferredResult.setResult(
                new ResponseEntity<>("The request has timed-out", HttpStatus.REQUEST_TIMEOUT)));

        LOGGER.info("Getting the product with the ID {}...", id);

        CompletableFuture
                .supplyAsync(() -> {
                    LOGGER.info("Performing the long running operation...");
                    longRunningOperation();
                    return new Product(id, "Tablet");
                })
                .whenCompleteAsync((response, error) -> {
                    LOGGER.info("Setting the deferred result");
                    processAsyncResponse(deferredResult, response, error);
                });

        LOGGER.info("Returning the deferred result");

        return deferredResult;
    }

    private void longRunningOperation() {
        try {
            LOGGER.info("Running a long running operation...");
            Thread.sleep(3000);
            // throw new IllegalArgumentException("Oops :)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processAsyncResponse(final DeferredResult<ResponseEntity<?>> deferred, final Object response,
                                      final Throwable exception) {
        if (exception == null) {
            deferred.setResult(ResponseEntity.ok(response));
        } else {
            deferred.setErrorResult(new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }
}
