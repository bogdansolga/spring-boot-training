package net.safedata.springboot.training.d03.s01.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PostLogoutHandler implements LogoutHandler {

    @Override
    public void logout(final HttpServletRequest httpServletRequest,
                       final HttpServletResponse httpServletResponse,
                       final Authentication authentication) {
        // perform any post-logout operations
    }
}
