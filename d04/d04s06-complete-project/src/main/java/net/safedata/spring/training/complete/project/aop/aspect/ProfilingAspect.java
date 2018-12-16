package net.safedata.spring.training.complete.project.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class ProfilingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilingAspect.class);

    private static final boolean IS_TRACE_ENABLED = LOGGER.isTraceEnabled();

    private static final long BYTES_IN_MB = 1048576;

    @Around("@annotation(net.safedata.spring.training.complete.project.aop.profiling.ExecutionTimeProfiling)")
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

    @Around("@annotation(net.safedata.spring.training.complete.project.aop.profiling.MemoryProfiling)")
    public Object profileMemory(final ProceedingJoinPoint pjp) throws Throwable {
        final Runtime runtime = Runtime.getRuntime();

        final Signature signature = pjp.getSignature();
        final String methodName = signature.getName();
        try {
            long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / BYTES_IN_MB;
            LOGGER.info("JVM memory in use before '{}': {} MB", methodName, usedMemory);

            Object methodReturnValue = pjp.proceed();

            usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / BYTES_IN_MB;
            LOGGER.info("JVM memory in use after '{}': {} MB", methodName, usedMemory);

            return methodReturnValue;
        } catch (final Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }
}
