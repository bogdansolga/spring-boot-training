package net.safedata.springboot.training.d02.s03.controller;

import net.safedata.springboot.training.d02.s03.dto.ProductDTO;
import net.safedata.springboot.training.d02.s03.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid ProductDTO productDTO) {
        productService.create(productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ProductDTO getProduct(@PathVariable final int id) {
        return productService.get(id);
    }

    @GetMapping(
            path = "/byId/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductDTO> getProductUsingResponseEntity(@PathVariable final int id) {
        return ResponseEntity.ok(productService.get(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final int id, @RequestBody ProductDTO productDTO) {
        productService.update(id, productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final int id) {
        productService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
