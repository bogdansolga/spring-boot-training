package net.safedata.springboot.training.d04.s03.model;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private final int id;
    private final String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public final int getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
