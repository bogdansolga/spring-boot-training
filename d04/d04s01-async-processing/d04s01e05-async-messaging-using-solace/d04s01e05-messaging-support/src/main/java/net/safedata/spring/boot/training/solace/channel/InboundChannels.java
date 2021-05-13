package net.safedata.spring.boot.training.solace.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface InboundChannels {
    @Input(Channels.Commands.ADD_PRODUCT_TO_ORDER)
    MessageChannel addProductToOrder();

    @Input(Channels.Events.ORDER_UPDATED)
    MessageChannel orderUpdated();
}