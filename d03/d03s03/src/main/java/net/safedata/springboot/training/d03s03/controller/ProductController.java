package net.safedata.springboot.training.d03s03.controller;

import net.safedata.springboot.training.d03s03.model.Product;
import net.safedata.springboot.training.d03s03.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            method = RequestMethod.GET,
            path = ""
    )
    public List<Product> getAll() {
        return productService.getProducts();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{id}"
    )
    public Product get(@PathVariable final int id) {
        return productService.getProduct(id);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = ""
    )
    public ResponseEntity<?> save(@RequestBody final Product product) {
        productService.saveProduct(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
