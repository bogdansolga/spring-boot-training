package net.safedata.springboot.training.d02.s04.exceptions;

/**
 * A runtime exception thrown when an entity was not found
 *
 * @author bogdan.solga
 */
public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    public NotFoundException(final String message) {
        super(message);
    }
}
