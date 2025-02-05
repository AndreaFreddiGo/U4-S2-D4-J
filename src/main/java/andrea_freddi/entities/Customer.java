package andrea_freddi.entities;

import java.util.Random;

public class Customer {
    private long id;
    private String name;
    private int tier;


    public Customer(String name, int tier) {
        Random random = new Random();
        this.id = random.nextLong();
        this.name = name;
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTier() {
        return tier;
    }
}