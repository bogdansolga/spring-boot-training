package net.safedata.springboot.training.d01.s05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ApplicationContext applicationContext;

    @Autowired
    public ProductService(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void processUsingPrototypeService(final int productId) {
        final ProductPrototypeService usedService = applicationContext.getBean(ProductPrototypeService.class);
        usedService.processProduct(productId);
    }
}
