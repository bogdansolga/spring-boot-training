package net.safedata.spring.training.complete.project.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Aspect for logging execution of controller endpoints
 *
 * @author bogdan.solga
 */
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    private static final boolean DEBUG_ENABLED = LOGGER.isDebugEnabled();

    /*
    @Autowired
    private EmailsService emailsService;    // if needed
    */

    @Pointcut("within(net.safedata.spring.training.complete.project.controller..*)")
    public void loggingPointcut() {}

    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "exception")
    public void logAfterThrowing(final JoinPoint joinPoint, final Throwable exception) {
        final Signature signature = joinPoint.getSignature();
        final String signatureName = signature.getName();
        final String declaringTypeName = signature.getDeclaringTypeName();
        final Throwable exceptionCause = exception.getCause();
        LOGGER.warn("{} in {}.{}(), with cause '{}'", exception.getClass().getSimpleName(), declaringTypeName,
                signatureName, exceptionCause);
    }

    @Around("loggingPointcut()")
    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Signature signature = joinPoint.getSignature();
        final String signatureName = signature.getName();
        final String runningClass = signature.getDeclaringTypeName();
        final Object[] parameters = joinPoint.getArgs();

        if (DEBUG_ENABLED) {
            LOGGER.debug("[In]: {}.{}() with argument[s] '{}'", runningClass, signatureName, Arrays.toString(parameters));
        }

        try {
            final Object result = joinPoint.proceed();
            if (DEBUG_ENABLED) {
                LOGGER.debug("[Out]: {}.{}() with result '{}'", runningClass, signatureName, result);
            }

            return result;
        } catch (final Exception exception) {
            sendExceptionViaEmail((MethodSignature) signature, parameters);
            throw exception;
        }
    }

    private void sendExceptionViaEmail(final MethodSignature methodSignature, final Object[] parameters) {
        final Map<String, Object> parametersMap = new HashMap<>(parameters.length);

        final AtomicInteger atomicInteger = new AtomicInteger(0);
        Arrays.stream(methodSignature.getParameterNames())
              .forEach(name -> parametersMap.put(name, parameters[atomicInteger.getAndIncrement()]));
        //emailsService.sendException(signatureName, exception, parametersMap);
    }
}
