package net.safedata.springboot.training.d02.s03.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * A Spring {@link RestController} used to showcase the usages of the {@link RequestMapping} and of the {@link PathVariable}
 * Spring web annotations
 *
 * @author bogdan.solga
 */
@RestController
@RequestMapping
public class RequestMappingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestMappingController.class);

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
    ) // http://localhost:8080/requestParams?color=blue&weight=light
    public String requestParamsIntro(@RequestParam(name = "color") String color,
                                     @RequestParam(required = false) Optional<String> weight) {
        return "The color is '" + Arrays.toString(color.split(",")) + "', the weight is '" + weight.orElse("N/A") + "'";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = {
                    "/pathVariable/{first}",
                    "/pathVariable/{first}/{second}"
            }
    ) // http://localhost:8080/pathVariable/green/blue
    public String pathVariableIntro(@PathVariable String first,
                                    @PathVariable(required = false) Optional<String> second) {
        return "The first path variable value is '" + first + "', the second is '" + second.orElse("N/A") + "'";
    }

    @GetMapping
    @SuppressWarnings("unused")
    public String requestAndResponseUsage(final HttpServletRequest request, final HttpServletResponse response,
                                          @RequestHeader("something") String header) {
        final String color = request.getParameter("color");
        final String requestHeader = request.getHeader("Content-Type");

        response.addHeader("Content-Type", "application/json");

        return "We can pass the HttpServletRequest and HttpServletResponse objects to any RequestMapping annotated method";
    }

    @GetMapping(
            path = "/stream",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public void stream(final HttpServletResponse response) {
        final ClassPathResource classPathResource = new ClassPathResource("spring-boot.png");

        try (final InputStream inputStream = classPathResource.getInputStream();
             final ServletOutputStream outputStream = response.getOutputStream()) {
            readAndWrite(inputStream, outputStream);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    @GetMapping(
            path = "/download",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public ResponseEntity<StreamingResponseBody> download() throws IOException {
        final ClassPathResource classPathResource = new ClassPathResource("spring-boot.png");
        return ResponseEntity.ok()
                             .headers(buildHttpHeaders(classPathResource))
                             .body(outputStream -> readAndWrite(classPathResource.getInputStream(), outputStream));
    }

    private HttpHeaders buildHttpHeaders(final ClassPathResource trainingInfoFile) throws IOException {
        final HttpHeaders headers = new HttpHeaders();

        headers.setContentDisposition(buildContentDisposition(trainingInfoFile));
        headers.setContentLength(trainingInfoFile.getFile().length());
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS));

        return headers;
    }

    private ContentDisposition buildContentDisposition(final ClassPathResource trainingInfoFile) {
        return ContentDisposition.builder("attachment")
                                 .filename(trainingInfoFile.getFilename(), StandardCharsets.UTF_8)
                                 .build();
    }

    private void readAndWrite(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        StreamUtils.copy(inputStream, outputStream);
    }

    @GetMapping("/simpleGETMapping")
    public String simpleGetMapping() {
        return "A simple GetMapping";
    }

    @PostMapping(
            value = "/simplePOSTMapping",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @DeleteMapping("/simpleDELETEMapping")
    @PutMapping("/simplePUTMapping")
    @PatchMapping("/simplePATCHMapping")
    public String simplePostMapping() {
        return "A simple PostMapping";
    }
}
