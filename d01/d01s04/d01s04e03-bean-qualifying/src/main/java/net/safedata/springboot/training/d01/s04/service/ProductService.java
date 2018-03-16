package net.safedata.springboot.training.d01.s04.service;

import net.safedata.springboot.training.d01.s04.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * A simple product service, which uses a {@link ProductRepository} as a collaborator
 *
 * @author bogdan.solga
 */
@Service
public class ProductService {

    private final ProductRepository mySQLProductRepository;
    private final ProductRepository oracleProductRepository;

    @Autowired
    public ProductService(@Qualifier("mySQLProductRepository") final ProductRepository repository,
                          @Qualifier("oracleProductRepository") final ProductRepository theOtherRepository) {
        this.mySQLProductRepository = repository;
        this.oracleProductRepository = theOtherRepository;
    }

    public void displayProducts() {
        final ProductRepository usedRepo = System.currentTimeMillis() % 2 == 0 ? mySQLProductRepository : oracleProductRepository;
        usedRepo.displayProducts();
    }
}
