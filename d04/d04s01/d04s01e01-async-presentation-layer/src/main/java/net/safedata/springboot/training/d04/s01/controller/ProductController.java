package net.safedata.springboot.training.d04.s01.controller;

import net.safedata.springboot.training.d04.s01.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        return new Product(10, "Tablet");
    }

    @RequestMapping(
            path = "/async/{id}"
    )
    public DeferredResult<ResponseEntity<?>> getAsyncProduct(@PathVariable final int id) {
        final DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();

        LOGGER.info("getAsyncProduct - {}", getCurrentThreadName(Thread.currentThread()));

        CompletableFuture
                .supplyAsync(() -> {
                    LOGGER.info("supplyAsync - running on {}", getCurrentThreadName(Thread.currentThread()));
                    return new Product(10, "Tablet");
                })
                .whenCompleteAsync((response, error) -> {
                    LOGGER.info("whenCompleteAsync - running on {}", getCurrentThreadName(Thread.currentThread()));
                    processAsyncResponse(deferredResult, response, error);
                });

        LOGGER.info("Returning on - {}", getCurrentThreadName(Thread.currentThread()));

        return deferredResult;
    }

    private void processAsyncResponse(final DeferredResult<ResponseEntity<?>> deferred, final Object response,
                                      final Throwable error) {
        if (error == null) {
            deferred.setResult(ResponseEntity.ok(response));
        } else {
            deferred.setErrorResult(error);

            /*
            final ResponseEntity<String> responseEntity = new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
            deferred.setResult(responseEntity);
            deferred.onTimeout(() -> deferred.setResult(responseEntity));
            */
        }
    }

    private String getCurrentThreadName(final Thread thread) {
        return thread.getName();
    }
}
