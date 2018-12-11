package net.safedata.springboot.training.d03s03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = ProductServiceDemo.class)
class ProductRESTControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @PostConstruct
    void setup () {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                 .build();
    }

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
