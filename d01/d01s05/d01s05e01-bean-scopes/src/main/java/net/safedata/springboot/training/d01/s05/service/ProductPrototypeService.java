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
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProductPrototypeService {

    private Set<String> values;

    public List<Integer> prcrossoverss() {
        //...

        values = new HashSet<>();
        values.add("ceva");

        // alte operatii obscure...

        return values.stream().map(item -> 6).collect(Collectors.toList());
    }

    public void displayHashCode() {
        System.out.println("The hashCode for this instance is " + hashCode());
    }
}
