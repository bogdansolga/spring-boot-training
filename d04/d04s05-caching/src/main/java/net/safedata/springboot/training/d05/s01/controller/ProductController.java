package net.safedata.springboot.training.d05.s01.controller;

import net.safedata.springboot.training.d05.s01.service.ProductService;
import net.safedata.springboot.training.d05.s01.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * A simple Spring {@link RestController}
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
            method = RequestMethod.GET,
            path = "/{id}"
    )
    public ProductDTO getProduct(@PathVariable final int id) {
        return productService.get(id);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/{id}"
    )
    public void removeProduct(@PathVariable final int id) {
        productService.removeProduct(id);
    }
}
