package net.safedata.spring.data.jdbc.controller;

import net.safedata.spring.data.jdbc.domain.entity.Product;
import net.safedata.spring.data.jdbc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productService.findAll();
    }
}
