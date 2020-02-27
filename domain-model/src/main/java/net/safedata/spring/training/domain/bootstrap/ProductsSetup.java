package net.safedata.spring.training.domain.bootstrap;

import net.safedata.spring.training.domain.model.Discount;
import net.safedata.spring.training.domain.model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
public final class ProductsSetup {

    private static final Random RANDOM = new Random(1000);

    private static final List<Product> tablets;
    private static final List<Product> monitors;
    private static final List<Product> laptops;

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
                new Product(1, "Google Nexus 7", getRandomPrice(150), new Discount(50, Discount.Type.Value)),
                new Product(2, "Apple iPad Pro", getRandomPrice(300), new Discount(10, Discount.Type.Percent)),
                new Product(3, "Samsung Galaxy Tab", getRandomPrice(200)),
                new Product(4, "Microsoft Surface Pro", getRandomPrice(230))
        );
    }

    private static List<Product> buildMonitors() {
        return Arrays.asList(
                new Product(5, "Samsung CF791", getRandomPrice(700)),
                new Product(6, "Dell UP3218K", getRandomPrice(600)),
                new Product(7, "Samsung CH711", getRandomPrice(900))
        );
    }

    private static List<Product> buildLaptops() {
        return Arrays.asList(
                new Product(8, "Lenovo Carbon X11", getRandomPrice(1000)),
                new Product(9, "Apple MacBookPro", getRandomPrice(1500)),
                new Product(10, "Razer Blade Black", getRandomPrice(2000))
        );
    }

    private static double getRandomPrice(final int multiplier) {
        return RANDOM.nextDouble() * multiplier;
    }
}
