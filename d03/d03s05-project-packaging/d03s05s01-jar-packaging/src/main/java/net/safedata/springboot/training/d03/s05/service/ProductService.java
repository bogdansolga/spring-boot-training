package net.safedata.springboot.training.d03.s05.service;

import net.safedata.spring.training.jpa.model.Product;
import net.safedata.springboot.training.d03.s05.dto.ProductDTO;
import net.safedata.springboot.training.d03.s05.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(final ProductDTO productDTO) {
        productRepository.create(getDTOConverter().apply(productDTO));
    }

    public ProductDTO get(final int id) {
        final Product product =
                Optional.ofNullable(productRepository.get(id))
                        .orElseThrow(() -> new IllegalArgumentException("There is no product with the id " + id));

        return getProductConverter().apply(product);
    }

    public List<ProductDTO> getAll() {
        return productRepository.getAll()
                                .parallelStream()
                                .filter(filterItem())
                                .map(getProductConverter())
                                .collect(Collectors.toList());
    }

    public void update(final int id, final ProductDTO productDTO) {
        productRepository.update(id, getDTOConverter().apply(productDTO));
    }

    public void delete(final int id) {
        productRepository.delete(id);
    }

    private Function<ProductDTO, Product> getDTOConverter() {
        return dto -> new Product(dto.getProductName());
    }

    private Function<Product, ProductDTO> getProductConverter() {
        return product -> new ProductDTO(product.getId(), product.getName());
    }

    private Predicate<Product> filterItem() {
        return product -> !product.getName().isEmpty() || product.getId() < 50;
    }
}
