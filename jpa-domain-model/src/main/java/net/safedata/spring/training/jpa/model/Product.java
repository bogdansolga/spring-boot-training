package net.safedata.spring.training.jpa.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import java.util.Objects;

@Entity(name = "Product")
public class Product extends AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "product_sequence_generator")
    @SequenceGenerator(name = "product_sequence_generator", sequenceName = "product_sequence", allocationSize = 1)
    private int id;

    @Column(name = "name", unique = true, nullable = false, insertable = true, updatable = false, length = 50)
    private String name;

    @Column(name = "price", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    private double price;

    @ManyToOne(targetEntity = Section.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id")
    private Section section;

    protected Product() {}
    
    public Product(final String name) {
        this.name = name;
    }

    public Product(final int id, final String name) {
    	this.id = id;
        this.name = name;
    }

    public Product(final String name, final Section section) {
        this.name = name;
        this.section = section;
    }

    public Product(final String name, final double price) {
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(section, product.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, section);
    }

    @Override
    public String toString() {
        return id + ", " + name + " [" + price + "]";
    }
}
