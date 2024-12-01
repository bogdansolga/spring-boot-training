package net.safedata.springboot.training.d03.s01.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FailedAuthHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(final HttpServletRequest httpServletRequest,
                                        final HttpServletResponse httpServletResponse,
                                        final AuthenticationException e) {
        // perform failed auth operations - ex: increasing the failed password attempts count, etc
    }
}
