package net.safedata.springboot.training.d03s03.aspect.profiling;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class Profiler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Profiler.class);

    private static final boolean IS_TRACE_ENABLED = LOGGER.isTraceEnabled();

    private static final long BYTES_IN_MB = 1048576;

    @Around("@annotation(Profiled)")
    public Object profileMethodExecutionTime(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            final long start = IS_TRACE_ENABLED ? System.currentTimeMillis() : 0L;
            final StringBuffer buffer = new StringBuffer();
            if (IS_TRACE_ENABLED) {
                buffer.append(pjp.getSignature().getDeclaringTypeName()) // package name
                      .append(".")
                      .append(pjp.getSignature().getName()); // class name
            }

            final Object retVal = pjp.proceed();
            if (IS_TRACE_ENABLED) {
                LOGGER.trace("{}, {}", buffer.append(": ")
                                             .append(System.currentTimeMillis() - start)
                                             .append(" ms"),
                        Arrays.asList(pjp.getArgs()));
            }

            return retVal;
        } catch (final Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @Around("@annotation(MemoryProfiling)")
    public Object profileMemory(final ProceedingJoinPoint pjp) throws Throwable {
        final Signature signature = pjp.getSignature();
        final String signatureName = signature.getName();
        try {
            long usedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / BYTES_IN_MB;
            LOGGER.info("JVM memory in use before '{}': {} MB", signatureName, usedMemory);

            Object retVal = pjp.proceed();

            usedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / BYTES_IN_MB;
            LOGGER.info("JVM memory in use after '{}': {} MB", signatureName, usedMemory);

            return retVal;
        } catch (final Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }
}
