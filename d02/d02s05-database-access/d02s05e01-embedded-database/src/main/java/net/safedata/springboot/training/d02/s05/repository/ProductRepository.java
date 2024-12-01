package net.safedata.springboot.training.d02.s05.repository;

import net.safedata.spring.training.jpa.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * A simple Spring Data {@link CrudRepository} for the {@link Product} entity
 *
 * @author bogdan.solga
 */
@Repository
@SuppressWarnings("unused")
public interface ProductRepository extends CrudRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {

    Optional<List<Product>> findByName(final String name);

    Optional<Product> findProductByNameAndId(@Param(value = "name") final String name,
                                             @Param(value = "id") final int id);

    Optional<List<Product>> findByPriceOrderByNameAsc(final double price, final Pageable pageable);

    Stream<Product> findAllByName(@Param(value = "name") final String name);

    @Async
    Future<Product> findProductById(int id);

    @Query(value =  "SELECT product " +
                    "FROM Product product " +
                    "WHERE product.name LIKE :name",
            nativeQuery = false
    )
    List<Product> findProductsWhichIncludeName(final @Param(value = "name") String name);
}
