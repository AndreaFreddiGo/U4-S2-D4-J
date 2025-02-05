package andrea_freddi.entities;

import andrea_freddi.enums.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Order {
    private long id;
    private Status status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Product> products;
    private Customer customer;

    public Order(Status status, LocalDate orderDate, LocalDate deliveryDate, List<Product> products, Customer customer) {
        Random random = new Random();
        this.id = random.nextLong();
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.products = products;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", id=" + id +
                ", status=" + status +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", products=" + products +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Status getStatus() {
        return status;
    }
}
