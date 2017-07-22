package net.safedata.springboot.training.d03.s01.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import static net.safedata.springboot.training.d03.s01.config.Roles.*;

@RestController
@PreAuthorize("isFullyAuthenticated()")
@SuppressWarnings("unused")
public class ProductController {

    @PreAuthorize("hasAnyRole('" + ADMIN_ROLE + "', '" + MANAGER_ROLE + "')")
    public void addProduct() {

    }

    // dynamically retrieving the authenticated user details
    public void passAuthenticatedUser(@AuthenticationPrincipal UserDetails userDetails) {
        final String username = userDetails.getUsername();

        final SecurityContext securityContext = SecurityContextHolder.getContext();
        final UserDetails details = (UserDetails) securityContext.getAuthentication().getPrincipal();
    }
}
