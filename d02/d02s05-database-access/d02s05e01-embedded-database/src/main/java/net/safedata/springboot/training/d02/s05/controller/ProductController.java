package net.safedata.springboot.training.d02.s05.controller;

import net.safedata.spring.training.jpa.model.Product;
import net.safedata.springboot.training.d02.s05.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

/**
 * A Spring {@link RestController} used to showcase the modeling of a REST controller for CRUD operations
 *
 * @author bogdan.solga
 */
@RestController
@RequestMapping(
        path = "/product"
)
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = ""
    )
    public ResponseEntity<?> create(@RequestBody Product product) {
        productService.create(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{id}"
    )
    public Product getProduct(@PathVariable final int id) {
        return productService.get(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/async/{id}"
    )
    public DeferredResult<Product> getProductAsync(@PathVariable final int id) {
        final CompletableFuture<Product> futureProduct = CompletableFuture.supplyAsync(() -> productService.get(id));

        final DeferredResult<Product> deferredResult = new DeferredResult<>();
        deferredResult.setResult(futureProduct.join());

        return deferredResult;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = ""
    )
    public Iterable<Product> getAll() {
        return productService.getAll();
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/{id}"
    )
    public ResponseEntity<?> update(@PathVariable final int id, @RequestBody Product product) {
        productService.update(id, product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable final int id) {
        productService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
