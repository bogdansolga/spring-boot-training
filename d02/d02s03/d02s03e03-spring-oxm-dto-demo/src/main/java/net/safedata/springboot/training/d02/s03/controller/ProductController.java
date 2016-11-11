package net.safedata.springboot.training.d02.s03.controller;

import net.safedata.springboot.training.d02.s03.dto.ProductDTO;
import net.safedata.springboot.training.d02.s03.model.Product;
import net.safedata.springboot.training.d02.s03.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    /**
     * Creates a {@link Product} from the referenced {@link ProductDTO}
     *
     * @param productDTO the {@link Product} to be created
     *
     * @return a {@link ResponseEntity} with the appropriate {@link HttpStatus}
     */
    @RequestMapping(
            method = RequestMethod.POST,
            path = ""
    )
    public ResponseEntity create(@RequestBody @Valid ProductDTO productDTO) {
        productService.create(productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Reads the {@link Product} with the specified id
     *
     * @param id the id of the requested {@link Product}
     *
     * @return the serialized {@link Product}
     */
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{id}",
            produces = {
                    "application/vnd.crossover.product+json"
            }
    )
    public ProductDTO getProduct(@PathVariable final int id, final HttpServletRequest request,
                                 final HttpServletResponse response) {
        return productService.get(id);
    }

    /**
     * Reads all the existing {@link Product}s
     *
     * @return the serialized {@link Product}s
     */
    @RequestMapping(
            method = RequestMethod.GET,
            path = ""
    )
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    /**
     * Updates the {@link Product} with the specified ID with the details from the referenced {@link Product}
     *
     * @param id the ID of the updated {@link Product}
     * @param productDTO the new {@link Product} details
     *
     * @return a {@link ResponseEntity} with the appropriate {@link HttpStatus}
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/{id}"
    )
    public ResponseEntity update(@PathVariable final int id, @RequestBody ProductDTO productDTO) {
        productService.update(id, productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Deletes the {@link Product} with the specified ID
     *
     * @param id the ID of the deleted {@link Product}
     *
     * @return a {@link ResponseEntity} with the appropriate {@link HttpStatus}
     */
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/{id}"
    )
    public ResponseEntity delete(@PathVariable final int id) {
        productService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
