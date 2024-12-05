package net.safedata.springboot.training.d04.s01.controller;

import net.safedata.springboot.training.d04.s01.model.Product;
import net.safedata.springboot.training.d04.s01.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@RequestMapping(
        path = "/product",
        method = RequestMethod.GET
)
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final Executor executor;
    private final ProductService productService;

    @Autowired
    public ProductController(Executor executor, ProductService productService) {
        this.executor = executor;
        this.productService = productService;
    }

    @RequestMapping(
            path = "/sync/{id}"
    )
    public Product getProduct(@PathVariable final int id) {
        return new Product(id, "Tablet");
    }

    @RequestMapping(
            path = "/async/{id}"
    )
    public DeferredResult<ResponseEntity<?>> getAsyncProduct(@PathVariable final int id) {
        LOGGER.info("Returning the product with the ID {}...", id);

        final DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        deferredResult.onTimeout(() -> deferredResult.setResult(
                new ResponseEntity<>("The request has timed-out", HttpStatus.REQUEST_TIMEOUT)));

        CompletableFuture.supplyAsync(() -> productService.getById(id), executor)
                         .whenCompleteAsync((response, error) ->
                                 processAsyncResponse(deferredResult, response, error), executor);

        LOGGER.info("Returning the result");
        return deferredResult;
    }

    private void processAsyncResponse(final DeferredResult<ResponseEntity<?>> deferred, final Product product,
                                      final Throwable exception) {
        if (exception == null) {
            deferred.setResult(ResponseEntity.ok(product));
        } else {
            deferred.setErrorResult(new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
