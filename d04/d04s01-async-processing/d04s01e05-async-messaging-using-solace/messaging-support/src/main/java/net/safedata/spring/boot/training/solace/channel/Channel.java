package net.safedata.spring.boot.training.solace.channel;

public enum Channel {

    // Customer channels
    CUSTOMER_CREATED(Channels.Events.CUSTOMER_CREATED),
    CUSTOMER_UPDATED(Channels.Events.CUSTOMER_UPDATED),

    // Order channels
    ADD_PRODUCT_TO_ORDER(Channels.Commands.ADD_PRODUCT_TO_ORDER),
    FIND_ORDER(Channels.Queries.FIND_ORDER),
    ORDER_CHARGED(Channels.Events.ORDER_CHARGED),
    ORDER_CREATED(Channels.Events.ORDER_CREATED),
    ORDER_SHIPPED(Channels.Events.ORDER_SHIPPED),
    ORDER_NOT_CHARGED(Channels.Events.ORDER_NOT_CHARGED),
    SHIP_ORDER(Channels.Commands.SHIP_ORDER)
    ;

    private final String channelName;

    Channel(final String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }
}
