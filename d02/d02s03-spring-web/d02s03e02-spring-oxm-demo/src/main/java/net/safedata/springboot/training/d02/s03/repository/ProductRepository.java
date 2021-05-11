package net.safedata.springboot.training.d02.s03.repository;

import net.safedata.spring.training.domain.bootstrap.ProductsSetup;
import net.safedata.spring.training.domain.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    // an in-memory list of products
    private final List<Product> products = ProductsSetup.getRandomProducts();

    public Product get(int id) {
        return products.get(id);
    }

    public List<Product> getAll() {
        return products;
    }

    public void create(final Product product) {
        products.add(product);
    }

    public void update(final int id, final Product product) {
    }

    public void delete(final int id) {
        products.remove(id < products.size() ? id : 0);
    }
}
