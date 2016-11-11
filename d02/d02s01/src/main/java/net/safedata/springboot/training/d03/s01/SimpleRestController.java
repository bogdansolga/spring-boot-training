package net.safedata.springboot.training.d03.s01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleRestController {

    @RequestMapping(
            value = "/hello"
    )
    public @ResponseBody String hello() {
        return "Hello, Spring Boot!";
    }

    // a redirect sample - the method response is a page name, not a ResponseBody
    @RequestMapping(
            value = "/redirect/{pageName}"
    )
    public String redirect(@PathVariable final String pageName) {
        return "redirect:" + pageName;
    }
}
