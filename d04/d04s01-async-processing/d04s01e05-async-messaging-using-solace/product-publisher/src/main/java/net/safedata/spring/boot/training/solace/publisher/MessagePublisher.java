package net.safedata.spring.boot.training.solace.publisher;

import net.safedata.spring.boot.training.solace.channel.OutboundChannels;
import net.safedata.spring.boot.training.solace.event.AddProductToOrderCommand;
import net.safedata.spring.boot.training.solace.message.MessageCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(OutboundChannels.class)
public class MessagePublisher {

    private final OutboundChannels outboundChannels;

    @Autowired
    public MessagePublisher(final OutboundChannels outboundChannels) {
        this.outboundChannels = outboundChannels;
    }

    public void publishAddProductToOrderEvent(final AddProductToOrderCommand addProductToOrderCommand) {
        outboundChannels.addProductToOrder()
                        .send(MessageCreator.create(addProductToOrderCommand));
    }
}
