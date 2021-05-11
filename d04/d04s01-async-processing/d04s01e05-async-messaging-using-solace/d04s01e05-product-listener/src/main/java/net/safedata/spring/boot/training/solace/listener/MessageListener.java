package net.safedata.spring.boot.training.solace.listener;

import net.safedata.spring.boot.training.solace.channel.Channels;
import net.safedata.spring.boot.training.solace.channel.InboundChannels;
import net.safedata.spring.boot.training.solace.event.AddProductToOrderCommand;
import net.safedata.spring.boot.training.solace.service.ProductListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(InboundChannels.class)
public class MessageListener {

    private final ProductListenerService productListenerService;

    @Autowired
    public MessageListener(final ProductListenerService productListenerService) {
        this.productListenerService = productListenerService;
    }

    @StreamListener(Channels.Commands.ADD_PRODUCT_TO_ORDER)
    public void addProductToOrder(final AddProductToOrderCommand addProductToOrderCommand) {
        productListenerService.addProductToOrder(addProductToOrderCommand);
    }
}
