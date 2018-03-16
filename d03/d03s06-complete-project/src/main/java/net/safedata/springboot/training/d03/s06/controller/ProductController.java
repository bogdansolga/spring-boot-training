package net.safedata.springboot.training.d03.s06.controller;

import net.safedata.springboot.training.d03.s06.dto.ProductDTO;
import net.safedata.springboot.training.d03.s06.model.Product;
import net.safedata.springboot.training.d03.s06.security.auth.HasManagerRole;
import net.safedata.springboot.training.d03.s06.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static net.safedata.springboot.training.d03.s06.security.auth.Roles.ADMIN_ROLE;

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
            path = "",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<?> create(@RequestBody @Valid ProductDTO productDTO) {
        productService.create(productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ProductDTO getProduct(@PathVariable final int id) {
        return productService.get(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = ""
    )
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/{id}"
    )
    public ResponseEntity<?> update(@PathVariable final int id, @RequestBody ProductDTO productDTO) {
        productService.update(id, productDTO);
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

    // -------------------------------------------------------------------------
    @PreAuthorize("hasRole('" + ADMIN_ROLE + "') AND hasAuthority('WRITE')")
    public void addProduct(final Authentication authentication) {
        // further use the Authentication object, if needed
    }

    @GetMapping(
            path = "/product/{id}"
    )
    public Product getProduct(@PathVariable final int id, final @AuthenticationPrincipal UserDetails userDetails) {
        final String username = userDetails.getUsername();
        System.out.println("The current user is '" + username + "'");
        return new Product(20, "Tablet");
    }

    // dynamically retrieving the authenticated user details
    public void passAuthenticatedUser(final @AuthenticationPrincipal UserDetails userDetails) {
        /* the same details can be obtained using:
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        final UserDetails details = (UserDetails) securityContext.getAuthentication().getPrincipal();
        */

        final String username = userDetails.getUsername();
        // the user details can be further passed to the services
    }

    @Secured("ROLE_ADMIN")
    public void processRequestOrResponseParameters(final HttpServletRequest request, final HttpServletResponse response) {
        // get parameters from the HTTP request, set details in the response
    }

    // recommended to be used when the principal details need to be consumed by an external tool / API
    @GetMapping("/currentUser")
    @HasManagerRole // DRY
    public Principal principal(final Principal principal) {
        return principal;
    }
}
