package net.safedata.springboot.training.d02.s05.repository;

import net.safedata.springboot.training.d02.s05.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * A simple Spring Data {@link CrudRepository} for the {@link Product} entity
 *
 * @author bogdan.solga
 */
@Repository
@SuppressWarnings("unused")
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Optional<List<Product>> findByName(final String name);

    @Query(value =  "SELECT product " +
                    "FROM Product product " +
                    "WHERE product.name LIKE :name"
    )
    Optional<List<Product>> findProductsWhichIncludeName(@Param(value = "name") final String name);

    Optional<Product> findById(int id);
}
