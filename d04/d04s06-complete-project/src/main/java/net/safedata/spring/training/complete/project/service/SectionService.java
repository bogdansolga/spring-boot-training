package net.safedata.spring.training.complete.project.service;

import net.safedata.spring.training.complete.project.repository.SectionRepository;
import net.safedata.spring.training.jpa.model.Product;
import net.safedata.spring.training.jpa.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionService(final SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        final Section section = new Section();
        section.setName("Goodies");

        final LinkedHashSet<Product> products =
                IntStream.rangeClosed(1, 10)
                         .boxed()
                         .map(id -> new Product("The product with the ID " + id, section))
                         .collect(Collectors.toCollection(LinkedHashSet::new));
        section.setProducts(products);

        sectionRepository.save(section);
    }
}
