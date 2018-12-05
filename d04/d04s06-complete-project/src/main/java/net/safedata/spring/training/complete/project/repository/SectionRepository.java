package net.safedata.spring.training.complete.project.repository;

import net.safedata.spring.training.jpa.model.Section;
import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends CrudRepository<Section, Integer> {
}
