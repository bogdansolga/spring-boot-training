package net.safedata.springboot.training.d04.s03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppInitMonitorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppInitMonitorService.class);

    @EventListener(ApplicationReadyEvent.class)
    public void onAppInit() {
        LOGGER.info("The app has successfully started!");
    }
}
