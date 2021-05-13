package net.safedata.spring.boot.training.solace.channel;

public final class Channels {

    // commands
    public static class Commands {
        public static final String CHARGE_ORDER = "charge_order";
        public static final String ADD_PRODUCT_TO_ORDER = "product_listener/command/add_product_to_order";
        public static final String SHIP_ORDER = "ship_order";
    }

    // events
    public static class Events {
        public static final String CUSTOMER_CREATED = "customer_created";
        public static final String CUSTOMER_UPDATED = "customer_updated";
        public static final String ORDER_CHARGED = "order_charged";
        public static final String ORDER_CREATED = "order_created";
        public static final String ORDER_UPDATED = "solace-example/event/order_updated";
        public static final String ORDER_NOT_CHARGED = "order_not_charged";
        public static final String ORDER_SHIPPED = "order_shipped";
    }

    // queries
    public static class Queries {
        public static final String FIND_ORDER = "find_order";
    }

    // deny instantiation
    private Channels() {}
}
