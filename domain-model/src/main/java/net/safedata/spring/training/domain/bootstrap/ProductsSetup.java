package net.safedata.spring.training.domain.bootstrap;

import net.safedata.spring.training.domain.model.Discount;
import net.safedata.spring.training.domain.model.Product;

import java.util.Arrays;
import java.util.List;

public final class ProductsSetup {

    private static List<Product> tablets;
    private static List<Product> monitors;
    private static List<Product> laptops;

    static {
        tablets = buildTablets();
        monitors = buildMonitors();
        laptops = buildLaptops();
    }

    public static List<Product> getTablets() {
        return tablets;
    }

    public static List<Product> getMonitors() {
        return monitors;
    }

    public static List<Product> getLaptops() {
        return laptops;
    }

    public static List<Product> getRandomProducts() {
        final long now = System.currentTimeMillis();
        if (now % 3 == 0) {
            return tablets;
        } else if (now % 2 == 0) {
            return monitors;
        } else {
            return laptops;
        }
    }

    private ProductsSetup() {}

    private static List<Product> buildTablets() {
        return Arrays.asList(
                new Product(1, "Google Nexus 7", 200, new Discount(50, Discount.Type.Value)),
                new Product(2, "Apple iPad Pro", 300, new Discount(10, Discount.Type.Percent)),
                new Product(3, "Samsung Galaxy Tab", 350),
                new Product(4, "Microsoft Surface Pro", 400)
        );
    }

    private static List<Product> buildMonitors() {
        return Arrays.asList(
                new Product(5, "Samsung CF791", 500),
                new Product(6, "Dell UP3218K", 550),
                new Product(7, "Samsung CH711", 600)
        );
    }

    private static List<Product> buildLaptops() {
        return Arrays.asList(
                new Product(8, "Lenovo Carbon X11", 1500),
                new Product(9, "Apple MacBookPro", 2000)
        );
    }
}
