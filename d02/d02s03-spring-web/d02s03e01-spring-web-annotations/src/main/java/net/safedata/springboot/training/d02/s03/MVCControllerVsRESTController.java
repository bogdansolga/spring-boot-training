package net.safedata.springboot.training.d02.s03;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Description("A controller which shows the difference between MVC and REST mappings")
public class MVCControllerVsRESTController {

    @RequestMapping("/mvc")
    public String getPage() {
        return "index"; // will return the name of a page, not a response body
    }

    @RequestMapping("/rest")
    public @ResponseBody String getResponseBody() {
        return "index";   // will return the response as a response body [because of the @ResponseBody annotation]
    }
}
