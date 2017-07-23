package net.safedata.springboot.training.d03.s01.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FailedAuthHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(final HttpServletRequest httpServletRequest,
                                        final HttpServletResponse httpServletResponse,
                                        final AuthenticationException e)
            throws IOException, ServletException {

        // perform failed auth operations - ex: increasing the failed password attempts count, etc
    }
}
