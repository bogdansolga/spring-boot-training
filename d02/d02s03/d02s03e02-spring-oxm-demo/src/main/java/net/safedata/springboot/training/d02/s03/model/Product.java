package net.safedata.springboot.training.d02.s03.model;

import java.io.Serializable;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
