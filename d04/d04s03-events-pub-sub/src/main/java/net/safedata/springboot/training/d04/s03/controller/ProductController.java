package net.safedata.springboot.training.d04.s03.controller;

import net.safedata.springboot.training.d04.s03.model.Product;
import net.safedata.springboot.training.d04.s03.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "/product",
        method = RequestMethod.GET
)
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(
            path = "/{id}"
    )
    public Product getProduct(@PathVariable final int id) {
        return productService.get(id);
    }

    @RequestMapping(
            path = "/throw/{id}"
    )
    public Product throwing(@PathVariable final int id) {
        return productService.throwingGet(id);
    }
}
