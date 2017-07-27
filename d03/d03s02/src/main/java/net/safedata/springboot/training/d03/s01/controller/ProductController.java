package net.safedata.springboot.training.d03.s01.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static net.safedata.springboot.training.d03.s01.config.Roles.*;

@RestController
@PreAuthorize("isFullyAuthenticated()")
@SuppressWarnings("unused")
public class ProductController {

    @PreAuthorize("hasAnyRole('" + ADMIN_ROLE + "', '" + MANAGER_ROLE + "')")
    public void addProduct(final Authentication authentication) {
        // further use the Authentication object, if needed
    }

    // dynamically retrieving the authenticated user details
    public void passAuthenticatedUser(@AuthenticationPrincipal UserDetails userDetails) {
        /* the same details can be obtained using:
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        final UserDetails details = (UserDetails) securityContext.getAuthentication().getPrincipal();
        */

        final String username = userDetails.getUsername();
        // the user details can be further passed to the services
    }

    // recommended to be used when the principal details need to be consumed by an external tool / API
    @GetMapping("/currentUser")
    public Principal principal(final Principal principal) {
        return principal;
    }
}
