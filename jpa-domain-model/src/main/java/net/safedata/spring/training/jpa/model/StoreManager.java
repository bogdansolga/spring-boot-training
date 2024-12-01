package net.safedata.spring.training.jpa.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "StoreManager")
@SuppressWarnings("unused")
public class StoreManager extends AbstractEntity {

    @EmbeddedId
    private StoreManagerPK storeManagerPK;

    public StoreManagerPK getStoreManagerPK() {
        return storeManagerPK;
    }

    public void setStoreManagerPK(StoreManagerPK storeManagerPK) {
        this.storeManagerPK = storeManagerPK;
    }
}
