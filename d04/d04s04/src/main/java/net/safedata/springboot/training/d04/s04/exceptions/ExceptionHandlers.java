package net.safedata.springboot.training.d04.s04.exceptions;

import net.safedata.springboot.training.d04.s04.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

/**
 * The most common exception handlers
 *
 * @author bogdan.solga
 */
@ControllerAdvice
public class ExceptionHandlers {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler({
            NotFoundException.class,
            IllegalAccessException.class,
            AccessDeniedException.class
    })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageDTO notFoundException(final NotFoundException e) {
        return new MessageDTO(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO illegalArgumentException(final IllegalArgumentException e) {
        return new MessageDTO(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<MessageDTO> internalServerError(final Exception e) {
        LOGGER.error(e.getMessage(), e);

        return new ResponseEntity<>(new MessageDTO("We have a little problem :)"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
