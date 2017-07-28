package net.safedata.springboot.training.d04.s03.event;

public class ProductRetrievedEvent {

    private final String productName;

    public ProductRetrievedEvent(final String productName) {
        this.productName = productName;
    }

    public final String getProductName() {
        return productName;
    }
}
