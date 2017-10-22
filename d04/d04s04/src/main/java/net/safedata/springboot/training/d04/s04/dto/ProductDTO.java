package net.safedata.springboot.training.d04.s04.dto;

import net.safedata.springboot.training.d04.s04.model.Product;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO (Data Transfer Object) used to serialize / de-serialize {@link Product} objects
 *
 * @author bogdan.solga
 */
public class ProductDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private int id;

    private String productName;

    public ProductDTO() {
    }

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
