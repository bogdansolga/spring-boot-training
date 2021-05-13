package net.safedata.spring.boot.training.solace.service;

import net.safedata.spring.boot.training.solace.event.AddProductToOrderCommand;
import net.safedata.spring.boot.training.solace.event.OrderUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ProductListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductListenerService.class);

    @Async
    public void addProductToOrder(AddProductToOrderCommand addProductToOrderCommand) {
        LOGGER.info("Received an '{}' event for the customerId {}, orderId {} and productId {}...",
                addProductToOrderCommand.getName(), addProductToOrderCommand.getCustomerId(),
                addProductToOrderCommand.getOrderId(), addProductToOrderCommand.getProductId());
    }

    @Async
    public void handleOrderUpdatedEvent(OrderUpdatedEvent orderUpdatedEvent) {
        LOGGER.info("Received an '{}' event for the customerId {} and orderId {}",
                orderUpdatedEvent.getName(), orderUpdatedEvent.getCustomerId(),
                orderUpdatedEvent.getOrderId());
    }
}
