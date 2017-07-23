package net.safedata.springboot.training.d03.s01.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessfulAuthHandler implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SuccessfulAuthHandler.class);

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest httpServletRequest,
                                        final HttpServletResponse httpServletResponse,
                                        final Authentication authentication)
            throws IOException, ServletException {

        String loggedInUser = null;
        final Object authenticationPrincipal = authentication.getPrincipal();
        if (authenticationPrincipal instanceof UserDetails) {
            final UserDetails springSecurityUser = (UserDetails) authenticationPrincipal;
            loggedInUser = springSecurityUser.getUsername();
        } else if (authenticationPrincipal instanceof String) {
            loggedInUser = (String) authenticationPrincipal;
        }

        LOGGER.trace("The current authenticated user is '{}'", loggedInUser);
    }
}
