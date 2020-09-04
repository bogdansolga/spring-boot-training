package net.safedata.spring.training.complete.project.controller;

import net.safedata.spring.training.complete.project.aop.profiling.ExecutionTimeProfiling;
import net.safedata.spring.training.complete.project.aop.profiling.MemoryProfiling;
import net.safedata.spring.training.complete.project.dto.ProductDTO;
import net.safedata.spring.training.complete.project.security.auth.HasManagerRole;
import net.safedata.spring.training.complete.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

import static net.safedata.spring.training.complete.project.security.auth.Roles.ADMIN_ROLE;

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

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
        productService.save(productDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable final int id) {
        return productService.get(id);
    }

    @MemoryProfiling
    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @ExecutionTimeProfiling
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

    // ----------------------- security related endpoints -------------------------------

    @PreAuthorize("hasRole('" + ADMIN_ROLE + "') AND hasAuthority('WRITE')")
    public void addProduct(final Authentication authentication) {
        // further use the Authentication object, if needed
    }

    @GetMapping("/product/{id}")
    public ProductDTO getProduct(@PathVariable final int id, final @AuthenticationPrincipal UserDetails userDetails) {
        final String username = userDetails.getUsername();
        System.out.println("The current user is '" + username + "'");
        return new ProductDTO(20, "Tablet");
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
