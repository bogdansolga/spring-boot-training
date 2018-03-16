package net.safedata.springboot.training.d03.s06.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PostLogoutHandler implements LogoutHandler {

    @Override
    public void logout(final HttpServletRequest httpServletRequest,
                       final HttpServletResponse httpServletResponse,
                       final Authentication authentication) {
        // perform any post-logout operations
    }
}
