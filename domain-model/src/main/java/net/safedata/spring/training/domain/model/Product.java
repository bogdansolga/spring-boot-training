package net.safedata.spring.training.domain.model;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Optional;

public class Product extends AbstractEntity {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("####.##");

    private final int id;
    private final String name;
    private final double price;
    private final Discount discount;

    public Product(final int id, final String name, final double price, final Discount discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public Product(final int id, final String name, final double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Optional<Discount> getDiscount() {
        return Optional.ofNullable(discount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product: {");

        sb.append("id: ").append(id);
        sb.append(", name: '").append(name).append('\'');
        sb.append(", \tprice: ").append(DECIMAL_FORMAT.format(price));
        Optional.ofNullable(discount)
                .ifPresent(value -> sb.append(", discount: ")
                                      .append(value.getValue())
                                      .append(" ")
                                      .append(value.getDiscountType().name().toLowerCase()));
        sb.append('}');

        return sb.toString();
    }
}
