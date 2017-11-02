package net.safedata.springboot.training.d02.s05.repository;

import net.safedata.springboot.training.d02.s05.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.Future;

/**
 * A simple Spring Data {@link CrudRepository} for the {@link Product} entity
 *
 * @author bogdan.solga
 */
@Repository
@SuppressWarnings("unused")
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByName(final String name);

    List<Product> findByPriceOrderByNameAsc(final double price, final Pageable pageable);

    @Async
    Future<Product> findProductById(int id);

    @Query(value =  "SELECT product " +
                    "FROM Product product " +
                    "WHERE product.name LIKE :name"
    )
    List<Product> findProductsWhichIncludeName(final @Param(value = "name") String name);
}
