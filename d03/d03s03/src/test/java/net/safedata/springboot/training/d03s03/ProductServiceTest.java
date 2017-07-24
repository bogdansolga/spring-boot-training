package net.safedata.springboot.training.d03s03;

import net.safedata.springboot.training.d03s03.model.Product;
import net.safedata.springboot.training.d03s03.repository.ProductRepository;
import net.safedata.springboot.training.d03s03.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    // the tested service; also called 'system under test'
    @InjectMocks
    private ProductService productService;

    @Test
    //public void shouldGetProductsWhenThereAreAvailableProducts() {
    public void givenThereAreAvailableProducts_whenRetrievingProducts_thenProductsAreRetrievedCorrectly() {
        // arrange
        final List<Product> products = Arrays.asList(new Product(1, "Asus"), new Product(2, "Dell"));
        when(productRepository.findAll()).thenReturn(products);

        // act
        final List<Product> resulted = productService.getProducts();

        // assert
        assertNotNull(resulted);
        assertThat(resulted.size(), is(products.size()));
    }

    @Test
    public void shouldNotGetAnyProductsWhenThereAreNoAvailableProducts() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        final List<Product> resulted = productService.getProducts();

        assertThat(resulted.size(), is(0));
    }

    @Test
    public void shouldGetAProductByIdWhenTheProductExists() {
        Product product = mock(Product.class);
        final String mockedName = "mocked name";
        when(product.getName()).thenReturn(mockedName);
        when(product.getId()).thenReturn(20);

        when(productRepository.findOne(anyInt())).thenReturn(product);

        final Product resulted = productService.getProduct(25);

        assertNotNull(resulted);
        assertThat(resulted.getName(), is(mockedName));
        assertThat(resulted.getId(), not(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGetAProductByIdWhenTheProductDoesNotExist() {
        when(productRepository.findOne(anyInt())).thenReturn(null);

        productService.getProduct(13);
    }

    @Test
    public void shouldSaveAProduct() {
        Product product = mock(Product.class);
        final String mockedName = "mocked name";
        when(product.getName()).thenReturn(mockedName);

        final String response = productService.saveProduct(product);

        verify(productRepository, times(1)).save(product);

        assertNotNull(response);
        assertThat(response.length(), not(0));
    }
}
