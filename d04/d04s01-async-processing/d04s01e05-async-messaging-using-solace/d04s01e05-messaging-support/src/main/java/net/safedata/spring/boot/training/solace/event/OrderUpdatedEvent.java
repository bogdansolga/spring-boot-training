package net.safedata.spring.boot.training.solace.event;

import net.safedata.spring.boot.training.solace.channel.Channel;
import net.safedata.spring.boot.training.solace.channel.MessageDetails;
import net.safedata.spring.boot.training.solace.channel.Service;
import net.safedata.spring.boot.training.solace.message.AbstractDomainEvent;

@MessageDetails(
        publisher = Service.ORDER_SERVICE,
        subscribers = Service.PRODUCT_SERVICE,
        channel = Channel.ORDER_UPDATED
)
public class OrderUpdatedEvent extends AbstractDomainEvent {

    private final long customerId;
    private final long orderId;

    public OrderUpdatedEvent(long messageId, long customerId, long orderId) {
        super(messageId);
        this.customerId = customerId;
        this.orderId = orderId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getOrderId() {
        return orderId;
    }

    @Override
    public String getName() {
        return "OrderUpdated";
    }
}
