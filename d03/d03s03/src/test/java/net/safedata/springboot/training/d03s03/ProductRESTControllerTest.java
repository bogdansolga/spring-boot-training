package net.safedata.springboot.training.d03s03;

import net.safedata.springboot.training.d03s03.config.ProductServiceConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ProductServiceConfig.class, ProductServiceDemo.class})
public class ProductRESTControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup () {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                 .build();
    }

    @Test
    public void shouldRetrieveProducts() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/product")
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content(createProduct("Tablet"));

        mockMvc.perform(builder)
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk());

        // create one more product
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
               .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
               .andDo(MockMvcResultHandlers.print());

    }

    private String createProduct(final String productName) {
        return "{ \"name\": \"" + productName + "\"}";
    }
}
