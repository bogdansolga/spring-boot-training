package net.safedata.spring.training.jpa.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Manager")
public class Manager extends AbstractEntity {

    @Id
    private int id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "StoreManager",
            joinColumns = {
                    // navigating from the 'StoreManager' to the 'Manager'
                    @JoinColumn(name = "manager_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    // navigating from the 'StoreManager' to the 'Store'
                    @JoinColumn(name = "store_id", referencedColumnName = "id")
            }
    )
    private Set<Store> stores;

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

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        Manager manager = (Manager) o;
        return id == manager.id &&
                Objects.equals(name, manager.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
