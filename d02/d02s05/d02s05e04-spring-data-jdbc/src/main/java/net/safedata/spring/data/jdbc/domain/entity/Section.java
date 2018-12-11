package net.safedata.spring.data.jdbc.domain.entity;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Section extends AbstractEntity {

    @Id
    private int id;
    private String name;
    //private Set<Product> products;

    public Section(final String name) {
        this.name = name;

        //this.products = new HashSet<>(products.length);
        //Collections.addAll(this.products, products);
    }

    public Section() {
    }

    public int getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public Optional<Set<Product>> getProducts() {
        return Optional.ofNullable(products);
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;
        Section that = (Section) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
