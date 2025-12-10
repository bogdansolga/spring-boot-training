package net.safedata.spring.training.complete.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Base64;

import static net.safedata.spring.training.complete.project.controller.ProductController.API_PREFIX;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Product REST Controller Integration Tests")
class ProductRESTControllerTest {

    private static final String PRODUCT_ENDPOINT = API_PREFIX + "/product";
    private static final String AUTH_HEADER = "Basic " + Base64.getEncoder().encodeToString("user:password".getBytes());

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given there are tablets in the database and a new tablet is created, when retrieving tablets then their number is correct")
    void given_thereAreTabletsInTheDatabase_andANewTabletIsCreated_whenRetrievingTablets_thenTheirNumberIsCorrect()
            throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post(PRODUCT_ENDPOINT)
                                      .header("Authorization", AUTH_HEADER)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content(createProduct("Tablet"));

        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());

        // create one more product
        builder = MockMvcRequestBuilders.post(PRODUCT_ENDPOINT)
                                        .header("Authorization", AUTH_HEADER)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(createProduct("Phone"));

        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());

        // get all products
        builder = MockMvcRequestBuilders.get(PRODUCT_ENDPOINT)
                                        .header("Authorization", AUTH_HEADER)
                                        .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
               .andDo(MockMvcResultHandlers.print());

    }

    private String createProduct(final String productName) {
        return "{ \"id\": 0, \"productName\": \"" + productName + "\"}";
    }
}
