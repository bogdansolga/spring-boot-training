package net.safedata.springboot.training.d04.s03.event.handler;

import net.safedata.springboot.training.d04.s03.event.ProductRetrievedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class SyncProductEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncProductEventHandler.class);

    @Async
    @EventListener
    public void processProduct(final ProductRetrievedEvent productRetrievedEvent) {
        LOGGER.info("The product '{}' was read", productRetrievedEvent.getProductName());
    }

    @TransactionalEventListener(
            phase = TransactionPhase.AFTER_COMPLETION
    )
    public void processSavedProduct(ProductRetrievedEvent productRetrievedEvent) {
        LOGGER.info("Executing the saved event...");
    }
}
