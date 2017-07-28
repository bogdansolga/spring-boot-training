package net.safedata.springboot.training.d04.s02.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SimpleTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTask.class);

    public void performScheduledTask() {
        LOGGER.info("Performing the scheduled task on {}...", new Date());
    }
}
