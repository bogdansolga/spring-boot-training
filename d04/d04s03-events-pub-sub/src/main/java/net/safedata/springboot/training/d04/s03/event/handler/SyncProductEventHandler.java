package net.safedata.springboot.training.d04.s03.event.handler;

import net.safedata.springboot.training.d04.s03.event.ProductRetrievedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@SuppressWarnings("unused")
public class SyncProductEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncProductEventHandler.class);

    @EventListener(ProductRetrievedEvent.class)
    public void processProduct(final ProductRetrievedEvent productRetrievedEvent) {
        LOGGER.info("The product '{}' was read", productRetrievedEvent.getProductName());

        //TODO uncomment the following line, to see the effect of an exception thrown from a sync event handler
        //throw new IllegalArgumentException("What happens when an exception is thrown from a sync event handler?");
    }

    @TransactionalEventListener(
            classes = ProductRetrievedEvent.class,
            phase = TransactionPhase.AFTER_COMPLETION
    )
    public void processSavedProduct(ProductRetrievedEvent productRetrievedEvent) {
        LOGGER.info("Processing after completion...");
    }

    @TransactionalEventListener(
            classes = ProductRetrievedEvent.class,
            phase = TransactionPhase.AFTER_ROLLBACK
    )
    public void processAfterRollback(ProductRetrievedEvent productRetrievedEvent) {
        LOGGER.info("Processing after rollback...");
    }

    @TransactionalEventListener(
            classes = ProductRetrievedEvent.class,
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void processAfterCommit(ProductRetrievedEvent productRetrievedEvent) {
        LOGGER.info("Processing after commit...");
    }
}
