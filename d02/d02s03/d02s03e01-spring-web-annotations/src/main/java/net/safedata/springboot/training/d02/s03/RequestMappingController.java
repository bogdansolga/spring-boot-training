package net.safedata.springboot.training.d02.s03;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

/**
 * A Spring {@link RestController} used to showcase the usages of the {@link RequestMapping} and of the {@link PathVariable}
 * Spring web annotations
 *
 * @author bogdan.solga
 */
@RestController
public class RequestMappingController {

    @RequestMapping(
            method = RequestMethod.GET,
            path = {
                    "/hello",
                    "/salut",
                    "bonjour"
            }
    )
    public String helloSpring() {
        return "Hello, Spring web! The date is " + new Date();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/requestParams"
    )
    public String requestParamsIntro(@RequestParam(name = "color") String color,
                                     @RequestParam(required = false) String weight) {
        return "The color is '" + color + "', the weight is '" + Optional.ofNullable(weight)
                                                                         .orElse("N/A") + "'";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/pathVariable/{first}/{second}"
    )
    public String pathVariableIntro(@PathVariable String first,
                                    @PathVariable(required = false) String second) {
        return "The first path variable value is '" + first + "', the second is '" + second + "'";
    }
}
