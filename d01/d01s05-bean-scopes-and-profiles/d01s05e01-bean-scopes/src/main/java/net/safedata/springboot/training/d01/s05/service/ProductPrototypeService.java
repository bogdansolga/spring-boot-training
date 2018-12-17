package net.safedata.springboot.training.d01.s05.service;

import net.safedata.spring.training.domain.bootstrap.ProductsSetup;
import net.safedata.spring.training.domain.model.Product;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An example of a 'prototype' scoped {@link Service}
 *
 * @author bogdan.solga
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) // = stateful applications
@SuppressWarnings("unused")
public class ProductPrototypeService {

    private List<Product> products = ProductsSetup.getRandomProducts();

    private Set<String> values;

    public List<Integer> processProducts() {
        //...

        values = new HashSet<>();
        values.add("a found Product");
        // other operations

        return values.stream()
                     .map(String::length)
                     .collect(Collectors.toList());
    }

    void processProduct(final int productId) {
        System.out.println("[" + hashCode() + "] Processing the product with the ID " + productId + ": " +
                products.get(productId));
    }

    public void displayHashCode() {
        System.out.println("The hashCode for this instance is " + hashCode());
    }
}
