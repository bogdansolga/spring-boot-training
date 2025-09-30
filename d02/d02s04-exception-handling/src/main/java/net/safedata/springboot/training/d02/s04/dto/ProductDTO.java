package net.safedata.springboot.training.d02.s04.dto;

import net.safedata.spring.training.jpa.model.Product;

import java.io.Serializable;

/**
 * A DTO (Data Transfer Object) used to serialize / deserialize {@link Product} objects
 *
 * @author bogdan.solga
 */
public record ProductDTO(int id, String name, double price) implements Serializable {

}
