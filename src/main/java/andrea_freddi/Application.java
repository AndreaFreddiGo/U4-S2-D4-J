package andrea_freddi;

import andrea_freddi.entities.Customer;
import andrea_freddi.entities.Order;
import andrea_freddi.entities.Product;
import andrea_freddi.enums.Status;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Product product1 = new Product("Laptop", "Electronics", 1000.0);
        Product product2 = new Product("Mouse", "Electronics", 20.0);
        Product product3 = new Product("Keyboard", "Electronics", 50.0);
        Product product4 = new Product("Lord of the Rings", "Books", 120.0);
        Product product5 = new Product("Harry Potter", "Books", 115.0);
        Product product6 = new Product("IT", "Books", 65.0);
        Product product7 = new Product("Shirt", "Boys", 30.0);
        Product product8 = new Product("Pants", "Boys", 40.0);
        Product product9 = new Product("Hat", "Boys", 10.0);
        Product product10 = new Product("Ball", "Baby", 5.0);
        Product product11 = new Product("Diapers", "Baby", 13.0);
        Product product12 = new Product("Pacifier", "Baby", 3.0);

        Customer customer1 = new Customer("Aldo", 1);
        Customer customer2 = new Customer("Giovanni", 2);
        Customer customer3 = new Customer("Giacomo", 3);
        Customer customer4 = new Customer("Ajeje", 2);
        Customer customer5 = new Customer("Emilio", 1);

        Order order1 = new Order(Status.PENDING, LocalDate.of(2021, 2, 1), LocalDate.of(2021, 2, 10), List.of(product10, product2, product3), customer1);
        Order order2 = new Order(Status.DELIVERED, LocalDate.of(2021, 3, 2), LocalDate.of(2021, 2, 11), List.of(product4, product5, product6), customer2);
        Order order3 = new Order(Status.CANCELLED, LocalDate.of(2021, 4, 3), LocalDate.of(2021, 3, 12), List.of(product7, product9, product12), customer3);
        Order order4 = new Order(Status.PENDING, LocalDate.of(2021, 2, 4), LocalDate.of(2021, 3, 13), List.of(product10, product11, product12), customer4);
        Order order5 = new Order(Status.DELIVERED, LocalDate.of(2021, 3, 5), LocalDate.of(2021, 3, 14), List.of(product6, product2, product3), customer5);
        Order order6 = new Order(Status.CANCELLED, LocalDate.of(2021, 1, 6), LocalDate.of(2021, 2, 15), List.of(product4, product1, product6), customer1);

        List<Product> products = List.of(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11, product12);
        List<Customer> customers = List.of(customer1, customer2, customer3, customer4, customer5);
        List<Order> orders = List.of(order1, order2, order3, order4, order5, order6);

        Map<String, List<Order>> ordersByClient = orders.stream().collect(Collectors.groupingBy(Order -> Order.getCustomer().getName()));
        ordersByClient.forEach((name, ordersList) -> System.out.println("Nome: " + name + " - Ordini: " + ordersList));

        Map<String, Double> totalSpentByClient = orders.stream().collect(Collectors.groupingBy(Order -> Order.getCustomer().getName(), Collectors.summingDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum())));
        totalSpentByClient.forEach((name, total) -> System.out.println("Nome: " + name + " - Totale speso: " + total));

        List<Product> mostExpensiveProducts = products.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).limit(3).toList();
        mostExpensiveProducts.forEach(product -> System.out.println("Name: " + product.getName() + " - Price: " + product.getPrice()));

        OptionalDouble averageTotalPrice = orders.stream().mapToDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum()).average();
        System.out.println("La spesa media degli ordini è: " + averageTotalPrice.getAsDouble());

        Map<String, Double> productsByCategoryTotalPrice = products.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));
        productsByCategoryTotalPrice.forEach((category, total) -> System.out.println("Categoria: " + category + " - Prezzo totale: " + total));

        File file = new File("src/main/java/andrea_freddi/prodotti.txt");
        products.forEach(product -> {
            try {
                FileUtils.writeStringToFile(file, product.getName() + "@" + product.getCategory() + "@" + product.getPrice() + "#", "UTF-8", true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            String content = FileUtils.readFileToString(file, "UTF-8");
            String[] productStrings = content.split("#");
            List<Product> productsFromFile = new ArrayList<>();
            for (String productString : productStrings) {
                String[] productData = productString.split("@");
                String name = productData[0];
                String category = productData[1];
                double price = Double.parseDouble(productData[2]);
                productsFromFile.add(new Product(name, category, price));
            }
            productsFromFile.forEach(product -> System.out.println("Name: " + product.getName() + ", Category: " + product.getCategory() + ", Price: " + product.getPrice()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        

    }
}
