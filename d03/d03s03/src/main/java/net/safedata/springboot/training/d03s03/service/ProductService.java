package net.safedata.springboot.training.d03s03.service;

import net.safedata.spring.training.jpa.model.Product;
import net.safedata.springboot.training.d03s03.dto.ProductDTO;
import net.safedata.springboot.training.d03s03.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private static final int THROWING_ID = 13;

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        save(new ProductDTO(1, "Tablet"));
        save(new ProductDTO(2, "Phone"));
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED
    )
    public String save(final ProductDTO productDTO) {
        // a lot of processing goes in here, before actually saving the product :)
        productRepository.save(new Product(productDTO.getProductName()));
        return "OK";
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public ProductDTO get(final int id) {
        Assert.isTrue(id != THROWING_ID, "There is no product with the ID " + THROWING_ID);

        final Product product = getByIdOrThrow(id);
        return getProductConverter().apply(product);
    }

    @Transactional(
            readOnly = true,
            propagation = Propagation.SUPPORTS
    )
    public List<ProductDTO> getAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                            .filter(filterItem())
                            .map(getProductConverter())
                            .sorted(Comparator.comparing(ProductDTO::getProductName))
                            .collect(Collectors.toList());
    }

    @Async
    public void update(final int id, final ProductDTO productDTO) {
        LOGGER.debug("Updating the product with the ID {}...", id);
        final Product product = getByIdOrThrow(id);
        productRepository.save(convertProductForUpdate().apply(productDTO, product));
    }

    public void delete(final int id) {
        productRepository.delete(getByIdOrThrow(id));
    }

    private Product getByIdOrThrow(int id) {
        return productRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("There is no product with the id " + id));
    }

    private Function<Product, ProductDTO> getProductConverter() {
        return product -> new ProductDTO(product.getId(), product.getName());
    }

    private BiFunction<ProductDTO, Product, Product> convertProductForUpdate() {
        return (dto, existingProduct) -> {
            existingProduct.setName(dto.getProductName());
            return existingProduct;
        };
    }

    private Predicate<Product> filterItem() {
        return product -> !product.getName().isEmpty() || product.getId() < 20;
    }
}
