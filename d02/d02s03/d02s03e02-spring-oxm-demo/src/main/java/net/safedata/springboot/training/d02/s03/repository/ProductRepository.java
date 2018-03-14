package net.safedata.springboot.training.d02.s03.repository;

import net.safedata.springboot.training.d02.s03.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private static final Object MUTEX = new Object();

    // an in-memory list of products
    private List<Product> products = new ArrayList<>(1);

    @PostConstruct
    public void init() {
        products.add(getDefaultProduct());
    }

    @SuppressWarnings("unused")
    public Product get(int id) {
        return getDefaultProduct();
    }

    public List<Product> getAll() {
        return products;
    }

    public void create(final Product product) {
        products.add(product);
    }

    public void update(final int id, final Product product) {
        final Product currentProduct = products.get(id < products.size() ? id : 0);
        synchronized (MUTEX) {
            currentProduct.setName(product.getName());
        }
    }

    public void delete(final int id) {
        products.remove(id < products.size() ? id : 0);
    }

    private Product getDefaultProduct() {
        final Product product = new Product();
        product.setId(24);
        product.setName("Dell XPS 9360");

        return product;
    }
}
