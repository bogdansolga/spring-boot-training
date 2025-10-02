package net.safedata.springboot.training.d03s03.integration;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import net.safedata.springboot.training.d03s03.ProductServiceDemo;
import net.safedata.springboot.training.d03s03.Profiles;
import net.safedata.springboot.training.d03s03.dto.ProductDTO;
import net.safedata.springboot.training.d03s03.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@SpringBootTest(
        classes = ProductServiceDemo.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(Profiles.IN_MEMORY)
public class ProductControllerTestIT extends AbstractTransactionalTestNGSpringContextTests {

    private static final String PRODUCT_NAME = "The product with the ID 1";

    @LocalServerPort
    private int port;

    @Autowired
    private ProductService productService;

    @BeforeMethod
    public void init() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    @BeforeClass
    public void initializeProduct() {
        productService.save(new ProductDTO(1, PRODUCT_NAME));
    }

    @Test
    public void givenTheContentTypeIsCorrect_WhenGettingAProduct_ThenTheStatusAndTheNameAreCorrect() {
        given()
                .accept(ContentType.JSON).
        when()
                .get("/product/{id}", 1).
        then()
                .statusCode(HttpStatus.OK.value())
                .body("productName", is(PRODUCT_NAME));
    }

    @Test
    public void givenTheContentTypeIsCorrect_WhenGettingAllProducts_ThenAllGood() {
        given()
                .accept(ContentType.JSON).
        when()
                .get("/product").
        then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(1))
                .body("[0].productName", is(PRODUCT_NAME));
    }

    // a sample of using a dataProvider
    @Test(dataProvider = "dataProvider")
    public void givenTheContentTypeIsCorrect_WhenUsingADataProvider_ThenAllGood(final String productId, final int statusCode) {
        given()
                .accept(ContentType.JSON).
        when()
                .get("/product/{id}", productId).
        then()
                .statusCode(statusCode);
    }

    @DataProvider(name = "dataProvider", parallel = true)
    public Object[][] dataProvider() {
        return new Object[][]{
                { "1", HttpStatus.OK.value()}, // productId and status code
                {"13", HttpStatus.BAD_REQUEST.value()}
        };
    }
}
