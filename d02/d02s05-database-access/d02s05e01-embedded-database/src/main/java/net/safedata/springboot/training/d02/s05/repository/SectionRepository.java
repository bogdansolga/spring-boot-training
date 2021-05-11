package net.safedata.springboot.training.d02.s05.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.safedata.spring.training.jpa.model.Section;

@Repository
public interface SectionRepository extends CrudRepository<Section, Integer>{
}
