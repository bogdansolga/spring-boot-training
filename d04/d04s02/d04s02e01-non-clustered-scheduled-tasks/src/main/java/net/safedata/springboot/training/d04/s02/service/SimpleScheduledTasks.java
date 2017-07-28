package net.safedata.springboot.training.d04.s02.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SimpleScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleScheduledTasks.class);

    @Scheduled(cron = "2 * * * * MON-FRI") // every two minutes, from Monday to Friday
    public void cronTask() {
        LOGGER.info("[cronTask] Running at {}...", new Date());
    }

    @Scheduled(fixedDelay = 10000) // 10 seconds
    public void fixedDelayTask() {
        LOGGER.info("[fixedDelayTask] Running at {}...", new Date());
    }

    @Scheduled(fixedRate = 10000) // every 10 seconds
    public void fixedRateTask() {
        LOGGER.info("[fixedRateTask] Running at {}...", new Date());
    }

    @Scheduled(fixedDelayString = "10000") // 10 seconds
    public void initialDelayTask() {
        LOGGER.info("[initialDelayTask] Running at {}...", new Date());
    }
}
