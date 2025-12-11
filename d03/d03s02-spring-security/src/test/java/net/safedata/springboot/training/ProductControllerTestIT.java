package net.safedata.springboot.training;

import net.safedata.springboot.training.d03.s01.SpringSecurityDemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(classes = SpringSecurityDemo.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Product Controller Integration Tests")
@WithMockUser(username = "user", authorities = "CAN_READ")
public class ProductControllerTestIT {

    @Test
    @DisplayName("The context loads")
    public void itLoads() {}
}
