package net.safedata.spring.boot.training.solace.service;

import net.safedata.spring.boot.training.solace.event.AddProductToOrderCommand;
import net.safedata.spring.boot.training.solace.event.OrderUpdatedEvent;
import net.safedata.spring.boot.training.solace.publisher.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductPublisherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPublisherService.class);

    private static final AtomicLong MESSAGE_ID_GENERATOR = new AtomicLong(71412481);
    private static final AtomicLong CUSTOMER_ID_GENERATOR = new AtomicLong(243);
    private static final AtomicLong ORDER_ID_GENERATOR = new AtomicLong(200);
    private static final Random PRODUCT_ID_GENERATOR = new Random(200);

    private final MessagePublisher messagePublisher;

    @Autowired
    public ProductPublisherService(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    //@Scheduled(fixedDelay = 5000)
    public void publishProductAdding() {
        final long nextMessageId = MESSAGE_ID_GENERATOR.addAndGet(1424124);
        final long nextCustomerId = CUSTOMER_ID_GENERATOR.addAndGet(5);
        final long nextOrderId = ORDER_ID_GENERATOR.addAndGet(125);
        final long theProductId = PRODUCT_ID_GENERATOR.nextInt(81283);

        messagePublisher.publishAddProductToOrderEvent(
                new AddProductToOrderCommand(nextMessageId, nextCustomerId, nextOrderId, theProductId));
        LOGGER.info("Published an order with the messageId {}, for the customerId {}, orderId {} and productId {}",
                nextMessageId, nextCustomerId, nextOrderId, theProductId);
    }

    @Scheduled(fixedDelay = 2000)
    public void publishOrderUpdatedEvents() {
        final long nextMessageId = MESSAGE_ID_GENERATOR.addAndGet(1424124);
        final long nextCustomerId = CUSTOMER_ID_GENERATOR.addAndGet(5);
        final long nextOrderId = ORDER_ID_GENERATOR.addAndGet(125);

        messagePublisher.publishOrderUpdatedEvent(new OrderUpdatedEvent(nextMessageId, nextCustomerId, nextOrderId));
        LOGGER.info("Published an order update event with the messageId {}, for the customerId {} and orderId {}",
                nextMessageId, nextCustomerId, nextOrderId);
    }
}
