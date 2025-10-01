package net.safedata.springboot.training.d03.s01.model;

public class Product {

    private final int id;

    private final int userId;

    private final String name;

    public Product(int id, int userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
