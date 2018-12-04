package net.safedata.spring.training.domain.bootstrap;

import net.safedata.spring.training.domain.model.Discount;
import net.safedata.spring.training.domain.model.Product;
import net.safedata.spring.training.domain.model.Section;
import net.safedata.spring.training.domain.model.Store;
import net.safedata.spring.training.domain.model.StoreSection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public final class StoreSetup {

    private static Store defaultStore;

    static {
        final Section tabletsSection = new Section(1, StoreSection.Tablets, buildDefaultTablets());
        final Section monitorsSection = new Section(2, StoreSection.Monitors, buildDefaultMonitors());
        final Section laptopsSection = new Section(3, StoreSection.Laptops, buildDefaultLaptops());

        defaultStore = new Store(1, "Goodies", "Over there",
                                 new HashSet<>(Arrays.asList(tabletsSection, monitorsSection, laptopsSection)));
    }

    public static Store getDefaultStore() {
        return defaultStore;
    }

    private StoreSetup() {}

    private static List<Product> buildDefaultTablets() {
        final List<Product> tablets = new ArrayList<>();

        tablets.add(new Product(1, "Google Nexus 7 2013", 200, new Discount(50, Discount.Type.Value)));
        tablets.add(new Product(2, "Apple iPad Pro 9.7", 300, new Discount(10, Discount.Type.Percent)));
        tablets.add(new Product(3, "Samsung Galaxy Tab S2", 350));
        tablets.add(new Product(4, "Microsoft Surface Pro 4", 400));

        return tablets;
    }

    private static List<Product> buildDefaultMonitors() {
        final List<Product> monitors = new ArrayList<>();

        monitors.add(new Product(5, "Samsung CF791", 500));
        monitors.add(new Product(6, "LG 32UD99", 550));
        monitors.add(new Product(7, "Samsung CH711", 600));

        return monitors;
    }

    private static List<Product> buildDefaultLaptops() {
        return Arrays.asList(
                new Product(10, "Lenovo X11", 1500),
                new Product(11, "Apple MacBook Pro", 2000)
        );
    }
}
