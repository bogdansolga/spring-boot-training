package net.safedata.springboot.training.d02.s01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

/**
 * A very simple {@link RestController}, which exposes a '/hello' endpoint
 *
 * @author bogdan.solga
 */
@RestController
@RequestMapping
public class SimpleRestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot! The current time is " + LocalTime.now();
    }
}
