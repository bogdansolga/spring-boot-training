package net.safedata.springboot.training.d02.s03.service;

import org.springframework.stereotype.Controller;

@Controller
public class ProductService {

    public String getProductDetails(final int productId) {
        return "product-details";
    }
}
