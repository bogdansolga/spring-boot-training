package net.safedata.springboot.training.d04.s04.model;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    public Product() {
    }

    public Product(int id, String name) {
        this.id = id; this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
