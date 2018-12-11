package net.safedata.springboot.training.d03s03;

import net.safedata.springboot.training.d03s03.model.Product;
import net.safedata.springboot.training.d03s03.repository.ProductRepository;
import net.safedata.springboot.training.d03s03.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository; // the collaborator (productRepository) is mocked

    // the tested service; also called 'system under test' // SUT
    @InjectMocks
    private ProductService productService;

    @Test
    void givenThereAreAvailableProducts_whenRetrievingProducts_thenProductsAreRetrievedCorrectly() {
        // arrange, including mocking behavior setup    --> given
        final List<Product> products = Arrays.asList(
                new Product(1, "Asus"),
                new Product(2, "Dell")
        );
        when(productRepository.findAll()).thenReturn(products); // simple mocking example

        // act --> calling the tested service method    --> when
        final List<Product> resulted = productService.getProducts();

        // assert --> verifying the response of the tested method is correct (by the requirements)  --> then
        assertNotNull(resulted);
        assertThat(resulted.size(), is(products.size()));
    }

    @Test
    void givenThereAreNoAvailableProducts_whenGettingProducts_thenNoProductsAreReturned() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        final List<Product> resulted = productService.getProducts();

        assertNotNull(resulted);
        assertThat(resulted.size(), is(0));
    }

    @Test
    void givenThereAreAvailableProducts_whenRetrievingAProductById_thenTheProductIsCorrectlyRetrieved() {
        Product product = mock(Product.class);
        final String mockedName = "mocked name";
        when(product.getName()).thenReturn(mockedName);
        when(product.getId()).thenReturn(20);

        when(productRepository.findById(10)).thenReturn(Optional.of(product));

        final Product resulted = productService.getProduct(10);

        assertNotNull(resulted);
        assertThat(resulted.getName(), is(mockedName));
        assertThat(resulted.getId(), not(0));
        assertThat(resulted.getId(), is(20));
    }

    @Test
    void givenThereAreNoAvailableProducts_whenGettingAProductById_thenAnIllegalArgumentExceptionIsThrown() {

        assertThrows(IllegalArgumentException.class, () -> productService.getProduct(13));
    }

    @Test
    void givenAProductIsSaved_whenSavingTheProduct_thenSaveIsCalledOneTimesAndTheResponseShouldNotBeEmptyOrNull () {
        final Product product = mock(Product.class);

        final String response = productService.saveProduct(product);

        // it verifies that the .save method (from the productRepository collaborator) was called exactly 1 times
        verify(productRepository, times(1)).save(product);

        assertNotNull(response);
        assertThat("The reason why we use the assert", response.length(), not(0));
    }
}
