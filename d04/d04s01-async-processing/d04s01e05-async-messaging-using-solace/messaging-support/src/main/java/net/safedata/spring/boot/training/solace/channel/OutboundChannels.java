package net.safedata.spring.boot.training.solace.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutboundChannels {
    @Output(Channels.Commands.ADD_PRODUCT_TO_ORDER)
    MessageChannel addProductToOrder();
}