package net.safedata.springboot.training.d02.s05.service;

import net.safedata.spring.training.jpa.model.Product;
import net.safedata.spring.training.jpa.model.Section;
import net.safedata.springboot.training.d02.s05.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionService(final SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Transactional(
            readOnly = false,
            rollbackFor = Exception.class
    )
    public void saveSectionAndProduct() {
        Section section = new Section();
        section.setName("Coffee Shop");
        Product product = new Product("Coffee");

        Section existingOrCreate = sectionRepository.findById(5)
                                                    .orElseGet(() -> sectionRepository.save(section));
        existingOrCreate.setProducts(Set.of(product));
        sectionRepository.save(existingOrCreate);

        int sectionId = existingOrCreate.getId();
        existingOrCreate.getProducts().forEach(p -> System.out.println(p.getId() + " " + p.getName()));
    }
}
