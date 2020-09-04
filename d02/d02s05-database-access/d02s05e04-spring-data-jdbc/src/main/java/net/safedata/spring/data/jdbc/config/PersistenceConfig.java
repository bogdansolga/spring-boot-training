package net.safedata.spring.data.jdbc.config;

import net.safedata.spring.data.jdbc.domain.entity.AbstractEntity;
import net.safedata.spring.data.jdbc.domain.entity.Product;
import net.safedata.spring.data.jdbc.domain.entity.Section;
import net.safedata.spring.data.jdbc.domain.repository.ProductRepository;
import net.safedata.spring.data.jdbc.domain.repository.SectionRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableJdbcRepositories(basePackages = "net.safedata.spring.data.jdbc.domain.repository")
public class PersistenceConfig extends AbstractJdbcConfiguration {

    private static final AtomicInteger PK_GENERATOR = new AtomicInteger(0);

    @Bean
    public ApplicationListener<BeforeSaveEvent> idSetting() {
        return event -> {
            final AbstractEntity abstractEntity = (AbstractEntity) event.getEntity();
            abstractEntity.setId(PK_GENERATOR.incrementAndGet());
        };
    }

    @Bean
    public ApplicationRunner applicationRunner(final ProductRepository productRepository,
                                               final SectionRepository sectionRepository) {
        return args -> {
            final Section savedSection = sectionRepository.save(new Section("Electronics"));
            System.out.println("The saved section is '" + savedSection + "'");
            sectionRepository.findById(savedSection.getId())
                             .ifPresent(System.out::println);

            System.out.println();

            final Product tablet = new Product("Tablet", 200d);
            final Product savedTablet = productRepository.save(tablet);
            System.out.println("The saved product is '" + savedTablet + "'");
            productRepository.findById(tablet.getId())
                             .ifPresent(System.out::println);

            /* TODO fix the one-to-many example, use https://www.youtube.com/watch?v=ccxBXDAPdmo
            final Product phone = new Product("Phone", 100d);
            final Section electronics = new Section("Electronics", phone, tablet);

            final Section electronicsSection = sectionRepository.save(electronics);
            sectionRepository.findById(electronicsSection.getId())
                             .ifPresent(it -> System.out.println("The section '" + it.getName() + "' has " +
                                     it.getProducts()
                                       .map(items -> items.size())
                                       .orElse(0) + " items"));
            */
        };
    }
}
