package net.safedata.springboot.training.d02.s03.exercise.controller;

import net.safedata.spring.training.domain.bootstrap.ProductsSetup;
import net.safedata.spring.training.domain.model.Product;
import net.safedata.springboot.training.d02.s03.exercise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// TODO add the needed annotations
@RestController
@RequestMapping("/product")
public class ProductController {

    // importance evaluation - knowledge topics:
    //  - beans wiring:     10/10
    //  - web annotations: 7-8/10
    //  - business logic:   15/10

    private final List<Product> products = new ArrayList<>(ProductsSetup.getRandomProducts());
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping                // we use POST for creating objects
    public String createProduct(@RequestBody Product product) {
        products.add(product);
        return "The product was created";
    }

    @GetMapping("/{productId}") // we use GET for reading objects, we use path variable for path values
    public Product getById(@PathVariable long productId) {
        return products.stream()
                       .filter(item -> item.getId() == productId)
                       .findFirst()
                       .orElseThrow();
    }

    @PutMapping("/{productId}") // we use PUT for updating objects, we use path variable for path values
    public String updateProduct(@PathVariable long productId, @RequestBody Product updatedProduct) {
        // TODO find product by ID, replace with the updatedProduct
        return "The product with the ID " + productId + " was updated";
    }

    @DeleteMapping("/{productId}")  // we use DELETE for deleting objects
    public String deleteProduct(@PathVariable long productId) {
        // TODO find product by ID, delete it
        return "The product with the ID " + productId + " was deleted";
    }

    //TODO implement the main CRUD operations
    //  x create a Product, in an in-memory collection (in the ProductRepository)
    //  x read a Product by ID, optionally all the Products
    //  - search for a Product, by name
    //  - update a Product, by ID
    //  - delete a Product, by ID

    // optionally (/ ideally) - also implement the:
    //  - ProductService
    //  - ProductRepository
}
