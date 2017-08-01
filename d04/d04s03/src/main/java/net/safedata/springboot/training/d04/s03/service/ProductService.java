package net.safedata.springboot.training.d04.s03.service;

import net.safedata.springboot.training.d04.s03.event.ProductRetrievedEvent;
import net.safedata.springboot.training.d04.s03.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ProductService(final ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public Product get(int id) {
        applicationEventPublisher.publishEvent(new ProductRetrievedEvent("iSomething"));

        //if (true) throw new IllegalArgumentException("Throwing from the publisher");

        return new Product(id, "iSomething");
    }
}
