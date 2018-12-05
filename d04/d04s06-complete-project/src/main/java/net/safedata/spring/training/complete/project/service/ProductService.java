package net.safedata.spring.training.complete.project.service;

import net.safedata.spring.training.jpa.model.Product;
import net.safedata.spring.training.complete.project.dto.ProductDTO;
import net.safedata.spring.training.complete.project.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED
    )
    public void create(final ProductDTO productDTO) {
        productRepository.save(getDTOConverter().apply(productDTO));
    }

    public ProductDTO get(final int id) {
        final Product product = getByIdOrThrow(id);
        return getProductConverter().apply(product);
    }

    public List<ProductDTO> getAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                            .filter(filterItem())
                            .map(getProductConverter())
                            .sorted(Comparator.comparing(ProductDTO::getProductName))
                            .collect(Collectors.toList());
    }

    public void update(final int id, final ProductDTO productDTO) {
        final Product product = getByIdOrThrow(id);
        LOGGER.debug("Updating the product with the ID '{}'...", product.getId());
        productRepository.save(getDTOConverter().apply(productDTO));
    }

    public void delete(final int id) {
        productRepository.delete(getByIdOrThrow(id));
    }

    private Product getByIdOrThrow(int id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("There is no product with the id " + id));
    }

    private Function<ProductDTO, Product> getDTOConverter() {
        return dto -> new Product(dto.getProductName());
    }

    private Function<Product, ProductDTO> getProductConverter() {
        return product -> new ProductDTO(product.getId(), product.getName());
    }

    // just an example of a filtering operation
    private Predicate<Product> filterItem() {
        return product -> !product.getName().isEmpty() || product.getId() < 20;
    }
}
