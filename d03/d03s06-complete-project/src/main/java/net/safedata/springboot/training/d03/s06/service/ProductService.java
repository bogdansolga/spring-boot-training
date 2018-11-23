package net.safedata.springboot.training.d03.s06.service;

import net.safedata.springboot.training.d03.s06.dto.ProductDTO;
import net.safedata.springboot.training.d03.s06.model.Product;
import net.safedata.springboot.training.d03.s06.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(final ProductDTO productDTO) {
        productRepository.save(getDTOConverter().apply(productDTO));
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.REQUIRED,
            rollbackFor = {
                    Exception.class
            }
    )
    public String save(final Product product) {
        // a lot of processing, before saving the product
        productRepository.save(product);
        return "OK";
    }

    @Transactional(
            readOnly = false,
            propagation = Propagation.SUPPORTS
    )
    public ProductDTO get(final int id) {
        final Product product = productRepository.findById(id)
                             .orElseThrow(() -> new IllegalArgumentException("There is no product with the id " + id));

        return getProductConverter().apply(product);
    }

    public List<ProductDTO> getAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                            .filter(filterItem())
                            .map(getProductConverter())
                            .collect(Collectors.toList());
    }

    public void update(final int id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                      .orElseThrow(() -> new IllegalArgumentException("There is no product with the ID " + id));
        productRepository.save(convertProductForUpdate().apply(productDTO, product));
    }

    public void delete(final int id) {
        productRepository.deleteById(id);
    }

    private Function<ProductDTO, Product> getDTOConverter() {
        return dto -> new Product(dto.getId(), dto.getProductName());
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
        return product -> !product.getName().isEmpty() || product.getId() < 50;
    }
}
