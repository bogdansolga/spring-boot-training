package net.safedata.springboot.training.d03s03.unit;

import net.safedata.spring.training.jpa.model.Product;
import net.safedata.springboot.training.d03s03.dto.ProductDTO;
import net.safedata.springboot.training.d03s03.repository.ProductRepository;
import net.safedata.springboot.training.d03s03.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository; // the collaborator (productRepository) is mocked

    // the tested service; also called 'system under test' // SUT
    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Given there are available products, when retrieving the products then products are retrieved correctly")
    void givenThereAreAvailableProducts_whenRetrievingProducts_thenProductsAreRetrievedCorrectly() {
        // arrange, including mocking behavior setup    --> given
        final List<Product> products = List.of(
                new Product(2, "Asus"),
                new Product(5, "Dell")
        );
        when(productRepository.findAll()).thenReturn(products); // simple mocking example

        // act --> calling the tested service method    --> when
        final List<ProductDTO> allProducts = productService.getAll();

        // assert --> verifying the response of the tested method is correct (by the requirements)  --> then
        assertNotNull(allProducts, "The products are null");
        assertEquals(allProducts.size(), products.size(), "Not all the products were returned");
        assertTrue(allProducts.iterator().hasNext(), "There is just a single product");
        allProducts.forEach(productDTO -> {
        	assertThat(productDTO.getId(), not(0));
			assertThat("The name must not be null or empty", StringUtils.hasLength(productDTO.getProductName()));
        });
    }

    @Test
    @DisplayName("Given there are no available products, when retrieving the products then no products are retrieved")
    void givenThereAreNoAvailableProducts_whenRetrievingProducts_thenNoProductsAreReturned() {
        when(productRepository.findAll()).thenReturn(List.of());

        final List<ProductDTO> allProducts = productService.getAll();

        assertNotNull(allProducts);
        assertThat(allProducts.size(), is(0));
    }

    @Test
    @DisplayName("Given there are available products, when retrieving a product by ID then the product is correctly retrieved")
    void givenThereAreAvailableProducts_whenRetrievingAProductById_thenTheProductIsCorrectlyRetrieved() {
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

    @Test
    @DisplayName("Given there are no available products, when retrieving a product by ID then an IAE is thrown")
    void givenThereAreNoAvailableProducts_whenRetrievingAProductById_thenAnIllegalArgumentExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () -> productService.get(13));
    }

    @Test
    @DisplayName("Given a product is saved, when saving the product then save is called one time and the response is not " +
            "null or empty")
    void givenAProductIsSaved_whenSavingTheProduct_thenSaveIsCalledOneTimesAndTheResponseShouldNotBeEmptyOrNull () {
        final ProductDTO productDTO = mock(ProductDTO.class);

        final String response = productService.save(productDTO);

        // it verifies that the .save method (from the productRepository collaborator) was called exactly 1 times
        verify(productRepository, times(1)).save(any(Product.class));

        assertNotNull(response);
        assertThat("The displayed error message", response.length(), not(0));
    }

    // ========================================
    // UPDATE tests
    // ========================================

    @Test
    @DisplayName("Given a product exists, when updating the product then the product is updated correctly")
    void givenAProductExists_whenUpdatingTheProduct_thenTheProductIsUpdatedCorrectly() {
        final int productId = 10;
        final String newProductName = "Updated Product";

        final Product existingProduct = new Product(productId, "Old Product");
        final ProductDTO productDTO = new ProductDTO(productId, newProductName);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        productService.update(productId, productDTO);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    @DisplayName("Given a product does not exist, when updating the product then an IAE is thrown")
    void givenAProductDoesNotExist_whenUpdatingTheProduct_thenAnIllegalArgumentExceptionIsThrown() {
        final int productId = 999;
        final ProductDTO productDTO = new ProductDTO(productId, "Product Name");

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.update(productId, productDTO));
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(0)).save(any(Product.class));
    }

    @Test
    @DisplayName("Given a null ProductDTO, when updating the product then an NPE is thrown")
    void givenANullProductDTO_whenUpdatingTheProduct_thenANullPointerExceptionIsThrown() {
        final int productId = 10;
        final Product existingProduct = new Product(productId, "Old Product");

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        assertThrows(NullPointerException.class, () -> productService.update(productId, null));
    }

    @Test
    @DisplayName("Given a negative product ID, when updating the product then an IAE is thrown")
    void givenANegativeProductId_whenUpdatingTheProduct_thenAnIllegalArgumentExceptionIsThrown() {
        final int negativeId = -5;
        final ProductDTO productDTO = new ProductDTO(negativeId, "Product Name");

        when(productRepository.findById(negativeId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.update(negativeId, productDTO));
    }

    // ========================================
    // DELETE tests
    // ========================================

    @Test
    @DisplayName("Given a product exists, when deleting the product then the product is deleted correctly")
    void givenAProductExists_whenDeletingTheProduct_thenTheProductIsDeletedCorrectly() {
        final int productId = 15;
        final Product product = new Product(productId, "Product to Delete");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        productService.delete(productId);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    @DisplayName("Given a product does not exist, when deleting the product then an IAE is thrown")
    void givenAProductDoesNotExist_whenDeletingTheProduct_thenAnIllegalArgumentExceptionIsThrown() {
        final int productId = 999;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.delete(productId));
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(0)).delete(any(Product.class));
    }

    @Test
    @DisplayName("Given a negative product ID, when deleting the product then an IAE is thrown")
    void givenANegativeProductId_whenDeletingTheProduct_thenAnIllegalArgumentExceptionIsThrown() {
        final int negativeId = -10;

        when(productRepository.findById(negativeId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.delete(negativeId));
        verify(productRepository, times(0)).delete(any(Product.class));
    }

    @Test
    @DisplayName("Given a zero product ID, when deleting the product then an IAE is thrown")
    void givenAZeroProductId_whenDeletingTheProduct_thenAnIllegalArgumentExceptionIsThrown() {
        final int zeroId = 0;

        when(productRepository.findById(zeroId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.delete(zeroId));
    }

    // ========================================
    // Additional edge cases for GET
    // ========================================

    @Test
    @DisplayName("Given a negative product ID, when retrieving a product then an IAE is thrown")
    void givenANegativeProductId_whenRetrievingAProduct_thenAnIllegalArgumentExceptionIsThrown() {
        final int negativeId = -1;

        when(productRepository.findById(negativeId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.get(negativeId));
    }

    @Test
    @DisplayName("Given a zero product ID, when retrieving a product then an IAE is thrown")
    void givenAZeroProductId_whenRetrievingAProduct_thenAnIllegalArgumentExceptionIsThrown() {
        final int zeroId = 0;

        when(productRepository.findById(zeroId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.get(zeroId));
    }

    @Test
    @DisplayName("Given the special throwing ID 13, when retrieving a product then an IAE is thrown with specific message")
    void givenTheSpecialThrowingId_whenRetrievingAProduct_thenAnIllegalArgumentExceptionIsThrownWithSpecificMessage() {
        final int throwingId = 13;

        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> productService.get(throwingId));

        assertThat(exception.getMessage(), is("There is no product with the ID 13"));
    }

    // ========================================
    // Additional edge cases for SAVE
    // ========================================

    @Test
    @DisplayName("Given a null ProductDTO, when saving the product then an NPE is thrown")
    void givenANullProductDTO_whenSavingTheProduct_thenANullPointerExceptionIsThrown() {
        assertThrows(NullPointerException.class, () -> productService.save(null));
        verify(productRepository, times(0)).save(any(Product.class));
    }

    @Test
    @DisplayName("Given a ProductDTO with null product name, when saving the product then save is still called")
    void givenAProductDTOWithNullProductName_whenSavingTheProduct_thenSaveIsStillCalled() {
        final ProductDTO productDTO = new ProductDTO(1, null);

        final String response = productService.save(productDTO);

        verify(productRepository, times(1)).save(any(Product.class));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Given a ProductDTO with empty product name, when saving the product then save is called")
    void givenAProductDTOWithEmptyProductName_whenSavingTheProduct_thenSaveIsCalled() {
        final ProductDTO productDTO = new ProductDTO(1, "");

        final String response = productService.save(productDTO);

        verify(productRepository, times(1)).save(any(Product.class));
        assertNotNull(response);
        assertThat(response, is("OK"));
    }

    // ========================================
    // Additional edge cases for GETALL
    // ========================================

    @Test
    @DisplayName("Given products with filtering criteria, when retrieving all products then filtering is applied correctly")
    void givenProductsWithFilteringCriteria_whenRetrievingAllProducts_thenFilteringIsAppliedCorrectly() {
        final List<Product> products = List.of(
                new Product(1, "Apple"),
                new Product(25, ""), // should be filtered out (empty name and id >= 20)
                new Product(5, "Banana"),
                new Product(30, "Cherry") // id >= 20 but has a name, should pass filter
        );
        when(productRepository.findAll()).thenReturn(products);

        final List<ProductDTO> allProducts = productService.getAll();

        assertNotNull(allProducts);
        // Based on filterItem() predicate: !product.getName().isEmpty() || product.getId() < 20
        // Product(1, "Apple") -> passes (id < 20)
        // Product(25, "") -> fails (name is empty AND id >= 20)
        // Product(5, "Banana") -> passes (id < 20)
        // Product(30, "Cherry") -> passes (name not empty)
        assertThat(allProducts.size(), is(3));
    }

    @Test
    @DisplayName("Given products in random order, when retrieving all products then products are sorted by name")
    void givenProductsInRandomOrder_whenRetrievingAllProducts_thenProductsAreSortedByName() {
        final List<Product> products = List.of(
                new Product(1, "Zebra"),
                new Product(2, "Apple"),
                new Product(3, "Mango")
        );
        when(productRepository.findAll()).thenReturn(products);

        final List<ProductDTO> allProducts = productService.getAll();

        assertNotNull(allProducts);
        assertThat(allProducts.size(), is(3));
        assertThat(allProducts.get(0).getProductName(), is("Apple"));
        assertThat(allProducts.get(1).getProductName(), is("Mango"));
        assertThat(allProducts.get(2).getProductName(), is("Zebra"));
    }
}
