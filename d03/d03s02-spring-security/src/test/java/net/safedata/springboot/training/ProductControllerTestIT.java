package net.safedata.springboot.training.d03s03.integration;

import net.safedata.springboot.training.d03s03.ProductServiceDemo;
import net.safedata.springboot.training.d03s03.Profiles;
import net.safedata.springboot.training.d03s03.dto.ProductDTO;
import net.safedata.springboot.training.d03s03.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProductServiceDemo.class)
@AutoConfigureMockMvc
@ActiveProfiles(Profiles.IN_MEMORY)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@DisplayName("Product Controller Integration Tests")
public class ProductControllerTestIT {

    private static final String PRODUCT_NAME = "The product with the ID 1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @BeforeAll
    public void initializeProduct() {
        productService.save(new ProductDTO(1, PRODUCT_NAME));
    }

    @Test
    @DisplayName("Given the content type is correct, when getting a product then the status and the name are correct")
    public void givenTheContentTypeIsCorrect_WhenGettingAProduct_ThenTheStatusAndTheNameAreCorrect() throws Exception {
        mockMvc.perform(get("/product/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", is(PRODUCT_NAME)));
    }

    @Test
    @DisplayName("Given the content type is correct, when getting all products then all products are returned")
    public void givenTheContentTypeIsCorrect_WhenGettingAllProducts_ThenAllGood() throws Exception {
        mockMvc.perform(get("/product")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productName", is(PRODUCT_NAME)));
    }

    // a sample of using a parameterized test (JUnit 5 equivalent of TestNG's @DataProvider)
    @ParameterizedTest(name = "Product ID {0} should return status {1}")
    @MethodSource("dataProvider")
    @DisplayName("Given the content type is correct, when using a data provider then the correct status is returned")
    public void givenTheContentTypeIsCorrect_WhenUsingADataProvider_ThenAllGood(final String productId, final int statusCode) throws Exception {
        mockMvc.perform(get("/product/{id}", productId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(statusCode));
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("1", 200),   // productId and status code
                Arguments.of("13", 400)
        );
    }
}
