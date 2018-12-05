package net.safedata.spring.training.complete.project.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FailedAuthHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(final HttpServletRequest httpServletRequest,
                                        final HttpServletResponse httpServletResponse,
                                        final AuthenticationException e) {
        // perform failed auth operations - ex: increasing the failed password attempts count, etc
    }
}
