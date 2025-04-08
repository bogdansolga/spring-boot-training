package net.safedata.springboot.training.d02.s03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * A Spring {@link Controller} used to showcase the usages of the {@link RequestMapping} Spring web annotation
 *
 * @author bogdan.solga
 */
@Controller
public class RequestResponseBodyController {

    @RequestMapping(
            method = {
                    RequestMethod.POST,
                    RequestMethod.PATCH
            },
            path = "/requestBody"
    )
    public @ResponseBody String requestBody(@RequestBody String request) {
        return "Getting the body of a POST request using the @RequestBody annotation - " + request;
    }

    @PostMapping("/responseBody")
    public @ResponseBody String responseBody() {
        return "Returning a response as a @ResponseBody";
    }
}
