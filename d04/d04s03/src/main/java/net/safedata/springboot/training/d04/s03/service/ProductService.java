package net.safedata.springboot.training.d04.s03.service;

import net.safedata.springboot.training.d04.s03.event.ProductRetrievedEvent;
import net.safedata.springboot.training.d04.s03.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ProductService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Product get(int id) {
        applicationEventPublisher.publishEvent(new ProductRetrievedEvent("iSomething"));
        return new Product(10, "iSomething");
    }
}
