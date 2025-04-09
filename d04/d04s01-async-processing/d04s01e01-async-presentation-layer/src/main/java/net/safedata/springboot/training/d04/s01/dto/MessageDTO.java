package net.safedata.springboot.training.d04.s01.dto;

import java.io.Serializable;

/**
 * A simple DTO used to carry a message to the consumer
 *
 * @author bogdan.solga
 */
public record MessageDTO(String message) implements Serializable {}
