package net.safedata.springboot.training.d01.s05.service;

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
public class ProductPrototypeService {

    private Set<String> values;

    @SuppressWarnings("unused")
    public List<Integer> processProducts() {
        //...

        values = new HashSet<>();
        values.add("a found Product");
        // other operations

        return values.stream()
                     .map(String::length)
                     .collect(Collectors.toList());
    }

    public void processProduct(final int productId) {
        System.out.println("[" + hashCode() + "] Processing the product with the ID " + productId + "...");
    }

    public void displayHashCode() {
        System.out.println("The hashCode for this instance is " + hashCode());
    }
}
