package net.safedata.spring.training.complete.project;

import net.safedata.spring.training.jpa.model.Product;
import net.safedata.spring.training.complete.project.dto.ProductDTO;
import net.safedata.spring.training.complete.project.repository.ProductRepository;
import net.safedata.spring.training.complete.project.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository; // the collaborator (productRepository) is mocked

    // the tested service; also called 'system under test' // SUT
    @InjectMocks
    private ProductService productService;

    @Test
    public void givenThereAreAvailableProducts_whenRetrievingProducts_thenProductsAreRetrievedCorrectly() {
        // arrange, including mocking behavior setup    --> given
        final List<Product> products = Arrays.asList(
                new Product("Samsung S8"),
                new Product("Google Pixel")
        );
        when(productRepository.findAll()).thenReturn(products); // simple mocking example

        // act --> calling the tested service method    --> when
        final List<ProductDTO> resulted = productService.getAll();

        // assert --> verifying the response of the tested method is correct (by the requirements)  --> then
        assertNotNull(resulted);
        System.out.println(resulted.size());
        assertThat(resulted.iterator().hasNext(), is(true));
    }

    @Test
    public void givenThereAreNoAvailableProducts_whenGettingProducts_thenNoProductsAreReturned() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        final Iterable<ProductDTO> resulted = productService.getAll();

        assertNotNull(resulted);
        //assertThat(resulted.size(), is(0));
    }

    @Test
    public void givenThereAreAvailableProducts_whenRetrievingAProductById_thenTheProductIsCorrectlyRetrieved() {
        final int productId = 20;

        final Product product = mock(Product.class);
        final String mockedName = "mocked name";
        when(product.getName()).thenReturn(mockedName);
        when(product.getId()).thenReturn(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        final ProductDTO resulted = productService.get(productId);

        assertNotNull(resulted);
        assertThat(resulted.getProductName(), is(mockedName));
        assertThat(resulted.getId(), not(0));
        assertThat(resulted.getId(), is(productId));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenThereAreNoAvailableProducts_whenGettingAProductById_thenAnIllegalArgumentExceptionIsThrown() {
        productService.get(13);
    }

    @Test
    public void givenAProductIsSaved_whenSavingTheProduct_thenSaveIsCalledOneTimesAndTheResponseShouldNotBeEmptyOrNull () {
        final ProductDTO product = mock(ProductDTO.class);

        productService.create(product);

        // it verifies that the .save method (from the productRepository collaborator) was called exactly 1 times
        verify(productRepository, times(1)).save(any(Product.class));
    }
}
