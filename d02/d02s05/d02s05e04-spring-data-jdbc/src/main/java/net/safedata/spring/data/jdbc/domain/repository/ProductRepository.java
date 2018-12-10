package net.safedata.spring.data.jdbc.domain.repository;

import net.safedata.spring.data.jdbc.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
