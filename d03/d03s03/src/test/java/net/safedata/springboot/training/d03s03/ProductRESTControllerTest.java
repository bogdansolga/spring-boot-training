package net.safedata.springboot.training.d03s03;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
class ProductRESTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void given_thereAreTabletsInTheDatabase_andANewTabletIsCreated_whenRetrievingTablets_thenTheirNumberIsCorrect()
            throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/product")
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content(createProduct("Tablet"));

        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());

        // create one more products
        builder = MockMvcRequestBuilders.post("/product")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(createProduct("phone"));

        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());

        // get all products
        builder = MockMvcRequestBuilders.get("/product")
                                        .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
               .andDo(MockMvcResultHandlers.print());

    }

    private String createProduct(final String productName) {
        return "{ \"name\": \"" + productName + "\"}";
    }
}
