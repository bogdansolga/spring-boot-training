package net.safedata.spring.boot.training.solace.event;

import net.safedata.spring.boot.training.solace.channel.Channel;
import net.safedata.spring.boot.training.solace.channel.MessageDetails;
import net.safedata.spring.boot.training.solace.channel.Service;
import net.safedata.spring.boot.training.solace.message.AbstractCommand;

import java.util.Objects;

@MessageDetails(
        publisher = Service.ORDER_SERVICE,
        subscribers = Service.PRODUCT_SERVICE,
        channel = Channel.ADD_PRODUCT_TO_ORDER
)
public class AddProductToOrderCommand extends AbstractCommand {

    private final long customerId;
    private final long orderId;
    private final long productId;

    public AddProductToOrderCommand(final long messageId, final long customerId, long orderId, final long productId) {
        super(messageId);
        this.customerId = customerId;
        this.orderId = orderId;
        this.productId = productId;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddProductToOrderCommand that = (AddProductToOrderCommand) o;
        return customerId == that.customerId && orderId == that.orderId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, orderId, productId);
    }
}
