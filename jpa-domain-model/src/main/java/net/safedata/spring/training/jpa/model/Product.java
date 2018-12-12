package net.safedata.spring.training.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

@Entity(name = "Product")
@Table(
        name = "Product",
        //schema = "spring_training",
        uniqueConstraints = {
                @UniqueConstraint(name = "uniqueName", columnNames = "name")
        },
        indexes = {
                @Index(name = "nameIndex", columnList = "name")
        }
)
@NamedQueries({
        @NamedQuery(
                name = "Product.byStore",
                query = "SELECT product " +
                        "FROM Product product " +
                        "WHERE product.section.store.id = :storeId"
        ),

        @NamedQuery(
                name = "Product.getAll",
                query = "SELECT product " +
                        "FROM Product product"
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Product.productById",
                query = "SELECT * " +
                        "FROM Product p " +
                        "WHERE p.id = :productId"
        )
})
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
    @JoinColumn(name = "sectionId")
    private Section section;

    protected Product() {}

    public Product(final String name) {
        this.name = name;
    }

    public Product(final String name, final Section section) {
        this.name = name;
        this.section = section;
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
