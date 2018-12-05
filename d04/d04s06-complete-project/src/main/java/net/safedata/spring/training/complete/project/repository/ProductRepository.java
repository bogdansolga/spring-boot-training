package net.safedata.spring.training.complete.project.repository;

import net.safedata.spring.training.jpa.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
