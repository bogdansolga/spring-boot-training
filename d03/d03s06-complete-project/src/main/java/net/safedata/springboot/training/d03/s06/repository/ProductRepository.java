package net.safedata.springboot.training.d03.s06.repository;

import net.safedata.springboot.training.d03.s06.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
