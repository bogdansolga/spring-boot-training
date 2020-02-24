package net.safedata.springboot.training.d04.s01.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CustomAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAsyncUncaughtExceptionHandler.class);

    @Override
    public void handleUncaughtException(final Throwable throwable, final Method method,
                                        final Object... params) {
        LOGGER.warn("'{}' in '{}' with the params '{}'", throwable.getClass().getSimpleName(),
                method.getName(), Arrays.asList(params));
        LOGGER.warn(throwable.getMessage());

        // may include additional exception reporting actions - email sending, ELK stack notification
    }
}
