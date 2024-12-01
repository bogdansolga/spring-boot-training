package net.safedata.springboot.training.d02.s03.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * A DTO (Data Transfer Object) used to serialize / deserialize {@link net.safedata.spring.training.domain.model.Product} objects
 *
 * @author bogdan.solga
 */
public class ProductDTO implements Serializable {

    private int id;

    private String optionalValue;

    @NotNull(message = "The name cannot be null")
    @Pattern(regexp = "\\d", message = "Does not match the pattern")
    @Size(min = 10, max = 50, message = "Nope")
    private String productName;

    public ProductDTO() {}

    public ProductDTO(final int id, final String productName) {
        this.id = id; this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Optional<String> getOptionalValue() {
        return Optional.ofNullable(optionalValue);
    }

    public void setOptionalValue(String optionalValue) {
        this.optionalValue = optionalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO)) return false;
        ProductDTO that = (ProductDTO) o;
        return id == that.id &&
                Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName);
    }
}
