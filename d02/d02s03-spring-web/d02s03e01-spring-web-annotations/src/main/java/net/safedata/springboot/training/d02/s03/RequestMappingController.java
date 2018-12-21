package net.safedata.springboot.training.d02.s03;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
public class RequestMappingController {

    private static final LocalDateTime NOW = LocalDateTime.now();

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
                                     @RequestParam(required = false) Optional<String> weight) {
        return "The color is '" + color + "', the weight is '" + weight.orElse("N/A") + "'";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = {
                    "/pathVariable/{first}",
                    "/pathVariable/{first}/{second}"
            }
    )
    public String pathVariableIntro(@PathVariable String first,
                                    @PathVariable(required = false) Optional<String> second) {
        return "The first path variable value is '" + first + "', the second is '" + second.orElse("N/A") + "'";
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    @SuppressWarnings("unused")
    public String requestAndResponseUsage(final HttpServletRequest request, final HttpServletResponse response) {
        return "We can pass the HttpServletRequest and HttpServletResponse objects to any RequestMapping annotated method";
    }

    @GetMapping(
            path = "/stream",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public void stream(final HttpServletResponse response) throws IOException {
        final ClassPathResource classPathResource = new ClassPathResource("spring-boot.png");
        readAndWrite(classPathResource.getInputStream(), response.getOutputStream());
    }

    @GetMapping(
            path = "/download",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<StreamingResponseBody> download() throws IOException {
        final ClassPathResource classPathResource = new ClassPathResource("spring-boot.png");
        return ResponseEntity.ok()
                             .headers(buildHttpHeaders(classPathResource))
                             .body(outputStream -> readAndWrite(classPathResource.getInputStream(), outputStream));
    }

    private HttpHeaders buildHttpHeaders(final ClassPathResource trainingInfoFile) throws IOException {
        final HttpHeaders headers = new HttpHeaders();

        final ContentDisposition contentDisposition = buildContentDisposition(trainingInfoFile);
        headers.setContentDisposition(contentDisposition);
        headers.setContentLength(trainingInfoFile.getFile().length());
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS));

        return headers;
    }

    private ContentDisposition buildContentDisposition(final ClassPathResource trainingInfoFile) throws IOException {
        return ContentDisposition.builder("attachment")
                                 .filename(trainingInfoFile.getFilename(), Charset.forName("UTF-8"))
                                 .creationDate(NOW.atZone(ZoneId.of("Europe/Bucharest")))
                                 .size(trainingInfoFile.getFile().length())
                                 .build();
    }

    private void readAndWrite(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] data = new byte[1024000];
        int read;
        while ((read = inputStream.read(data)) > 0) {
            outputStream.write(data, 0, read);
        }
        outputStream.flush();
    }

    @GetMapping("/simpleGETMapping")
    public String simpleGetMapping() {
        return "A simple GetMapping";
    }

    @PostMapping("/simplePOSTMapping")
    public String simplePostMapping() {
        return "A simple PostMapping";
    }
}
