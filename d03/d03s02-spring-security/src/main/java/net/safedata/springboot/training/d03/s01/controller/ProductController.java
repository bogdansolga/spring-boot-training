package net.safedata.springboot.training.d03.s01.controller;

import net.safedata.springboot.training.d03.s01.config.HasManagerRole;
import net.safedata.springboot.training.d03.s01.config.Roles;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safedata.springboot.training.d03.s01.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

import static net.safedata.springboot.training.d03.s01.config.Roles.*;

@RestController
@PreAuthorize("isAuthenticated()") // it's just an example :)
@SuppressWarnings("unused")
public class ProductController {

    @PreAuthorize(
            "hasRole('" + ADMIN_ROLE + "') " +
            "AND authentication.details.userId == 25 " + // an equality check
            "AND hasAuthority('WRITE')"
    )
    @GetMapping("/product")
    public void addProduct(final Authentication authentication, @RequestBody Product product) {
        UserDetails userDetails  = (UserDetails) authentication.getPrincipal();
        System.out.println("The user details: " + userDetails);
        // further use the Authentication object, if needed
    }

    @PreFilter("filterObject.id == authentication.details.userId")
    public void filterProducts(final List<Product> products) {
        // using the filtered products, afterwards
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

    @Secured({Roles.ADMIN_ROLE})
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
