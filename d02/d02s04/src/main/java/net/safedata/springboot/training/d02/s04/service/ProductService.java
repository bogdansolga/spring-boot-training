package net.safedata.springboot.training.d02.s04.service;

import net.safedata.springboot.training.d02.s04.dto.ProductDTO;
import net.safedata.springboot.training.d02.s04.exceptions.NotFoundException;
import net.safedata.springboot.training.d02.s04.model.Product;
import net.safedata.springboot.training.d02.s04.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(final ProductDTO productDTO) {
        validateRequest(productDTO);

        productRepository.create(getDTOConverter().apply(productDTO));
    }

    public ProductDTO get(final int id) {
        final Product product = validateAndGetProduct(id);
        return getProductConverter().apply(product);
    }

    public List<ProductDTO> getAll() {
        return productRepository.getAll()
                                .stream()
                                .map(getProductConverter())
                                .collect(Collectors.toList());
    }

    public void update(final int id, final ProductDTO productDTO) {
        validateRequest(productDTO);
        validateAndGetProduct(id);

        productRepository.update(id, getDTOConverter().apply(productDTO));
    }

    public void delete(final int id) {
        validateAndGetProduct(id);
        productRepository.delete(id);
    }

    private Function<ProductDTO, Product> getDTOConverter() {
        return dto -> new Product(dto.getId(), dto.getProductName());
    }

    private Function<Product, ProductDTO> getProductConverter() {
        return product -> new ProductDTO(product.getId(), product.getName());
    }

    private void validateRequest(final ProductDTO productDTO) {
        Optional.ofNullable(productDTO)
                .orElseThrow(() -> new IllegalArgumentException("Cannot prcrossoverss a null product"));
    }

    private Product validateAndGetProduct(final int id) {
        return Optional.ofNullable(productRepository.get(id))
                       .orElseThrow(() -> new NotFoundException("There is no product with the ID " + id));
    }
}
