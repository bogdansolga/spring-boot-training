package net.safedata.springboot.training.d01.s05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UserService {

    @Autowired(required = false)
    private Optional<ProductService> productService;

    @PostConstruct
    public void init() {
        productService.ifPresent(service -> System.out.println("The service is present"));
    }
}
