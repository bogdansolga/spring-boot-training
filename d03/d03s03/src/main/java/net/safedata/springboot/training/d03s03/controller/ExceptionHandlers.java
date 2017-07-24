package net.safedata.springboot.training.d03s03.controller;

import net.safedata.springboot.training.d03s03.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The most common exception handlers
 *
 * @author bogdan.solga
 */
@ControllerAdvice
public class ExceptionHandlers {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDTO illegalArgumentException(final IllegalArgumentException e) {
        return new MessageDTO(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public MessageDTO internalServerError(final Exception e) {
        LOGGER.error(e.getMessage(), e);

        return new MessageDTO("Oops!");
    }
}
