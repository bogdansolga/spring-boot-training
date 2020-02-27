package net.safedata.springboot.training.d02.s05.controller;

import net.safedata.spring.training.jpa.model.Product;
import net.safedata.springboot.training.d02.s05.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    private final ProductService productService; // database? web service? eMag? file?

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product) {
        productService.create(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable final int id) {
        return productService.get(id);
    }

    @GetMapping("/async/{id}")
    public DeferredResult<Product> getProductAsync(@PathVariable final int id) {
        final CompletableFuture<Product> futureProduct = CompletableFuture.supplyAsync(() -> productService.get(id));

        final DeferredResult<Product> deferredResult = new DeferredResult<>();
        deferredResult.setResult(futureProduct.join());

        return deferredResult;
    }

    @GetMapping
    public List<Product> getAll() {
        return StreamSupport.stream(productService.getAll().spliterator(), false)
                             .collect(Collectors.toList());
    }

    @GetMapping(
            path = {
                    "/{pageNumber}/{resultsNumber}/{sortMode}",
                    "/{pageNumber}/{resultsNumber}"
            }
    )
    public Page<Product> getPage(@PathVariable final int pageNumber,
                                 @PathVariable final int resultsNumber,
                                 @PathVariable final Optional<String> sortMode) {
        return productService.getPage(pageNumber, resultsNumber, sortMode.orElse("asc"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final int id, @RequestBody Product product) {
        productService.update(id, product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final int id) {
        productService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
