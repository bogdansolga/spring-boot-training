package net.safedata.spring.data.jdbc.domain.repository;

import net.safedata.spring.data.jdbc.domain.entity.Section;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends CrudRepository<Section, Integer> {
}
