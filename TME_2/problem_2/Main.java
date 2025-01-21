package problem_2;

import java.util.*;

// Define Product class
class Product {
    private String name;
    private double price;

    // Constructor for Product class
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter method for product name
    public String getName() {
        return name;
    }

    // Getter method for product price
    public double getPrice() {
        return price;
    }

    // Override toString method to customize string representation of Product
    @Override
    public String toString() {
        return name + " - name=" + name + ", price=$" + price;
    }
}

// Define ComputerPart, Peripheral, Cheese, Fruit, and Service classes
class ComputerPart extends Product {
    // Constructor for ComputerPart class
    public ComputerPart(String name, double price) {
        super(name, price); // Call superclass constructor
    }
}

class Peripheral extends Product {
    // Constructor for Peripheral class
    public Peripheral(String name, double price) {
        super(name, price); // Call superclass constructor
    }
}

class Cheese extends Product {
    // Constructor for Cheese class
    public Cheese(String name, double price) {
        super(name, price); // Call superclass constructor
    }
}

class Fruit extends Product {
    // Constructor for Fruit class
    public Fruit(String name, double price) {
        super(name, price); // Call superclass constructor
    }
}

class Service extends Product {
    // Constructor for Service class
    public Service(String name, double price) {
        super(name, price); // Call superclass constructor
    }
}

// Define GenericOrder class
class GenericOrder<T extends Product> {
    private static int nextOrderId = 123456; // Starting order ID
    private int orderId;
    private List<T> items;

    // Constructor for GenericOrder class
    public GenericOrder() {
        orderId = nextOrderId; // Assign order ID
        nextOrderId += 100000; // Increase order ID by 100000 for each new order
        items = new ArrayList<>(); // Initialize items list
    }

    // Getter method for order ID
    public int getOrderId() {
        return orderId;
    }

    // Method to add item to order
    public void addItem(T item) {
        items.add(item);
    }

    // Method to get items in the order
    public List<T> getItems() {
        return items;
    }
}

// Define ComputerOrder subclass of GenericOrder
class ComputerOrder extends GenericOrder<Product> {
    // Additional methods specific to ComputerOrder can be added here
}

// Define PartyTrayOrder subclass of GenericOrder
class PartyTrayOrder extends GenericOrder<Product> {
    // Additional methods specific to PartyTrayOrder can be added here
}



// Define OrderProcessor class
class OrderProcessor {
    private Map<Integer, List<Product>> orderMap;

    // Constructor for OrderProcessor class
    public OrderProcessor() {
        orderMap = new HashMap<>(); // Initialize order map
    }

    // Method to accept an order
    public void accept(GenericOrder<? extends Product> order) {
        // Put the order in the order map
        orderMap.put(order.getOrderId(), (List<Product>) order.getItems());
    }

    // Method to process orders
    public void process() {
        // Sorting and processing logic
        // For simplicity, we'll just print the items
        for (Map.Entry<Integer, List<Product>> entry : orderMap.entrySet()) {
            int orderId = entry.getKey();
            List<Product> items = entry.getValue();

            for (Product item : items) {
                // Print each item along with its order number
                System.out.println(item + ", order number=" + orderId);
            }
        }
    }

    // Method to dispatch computer parts
    public void dispatchComputerParts() {
        System.out.println("\nDispatching Computer Parts");
        for (List<Product> products : orderMap.values()) {
            for (Product product : products) {
                // Check if product is a ComputerPart and print if true
                if (product instanceof ComputerPart) {
                    System.out.println("Product: " + product + ", order number=" + getOrderNumber(product));
                }
            }
        }
    }

    // Method to dispatch peripherals
    public void dispatchPeripherals() {
        System.out.println("\nDispatching Peripherals");
        for (List<Product> products : orderMap.values()) {
            for (Product product : products) {
                // Check if product is a Peripheral and print if true
                if (product instanceof Peripheral) {
                    System.out.println("Product: " + product + ", order number=" + getOrderNumber(product));
                }
            }
        }
    }

    // Method to dispatch cheeses
    public void dispatchCheeses() {
        System.out.println("\nDispatching Cheeses");
        for (List<Product> products : orderMap.values()) {
            for (Product product : products) {
                // Check if product is a Cheese and print if true
                if (product instanceof Cheese) {
                    System.out.println("Product: " + product + ", order number=" + getOrderNumber(product));
                }
            }
        }
    }

    // Method to dispatch fruits
    public void dispatchFruits() {
        System.out.println("\nDispatching Fruits");
        for (List<Product> products : orderMap.values()) {
            for (Product product : products) {
                // Check if product is a Fruit and print if true
                if (product instanceof Fruit) {
                    System.out.println("Product: " + product + ", order number=" + getOrderNumber(product));
                }
            }
        }
    }

    // Method to dispatch services
    public void dispatchServices() {
        System.out.println("\nDispatching Services");
        for (List<Product> products : orderMap.values()) {
            for (Product product : products) {
                // Check if product is a Service and print if true
                if (product instanceof Service) {
                    System.out.println("Product: " + product + ", order number=" + getOrderNumber(product));
                }
            }
        }
    }

    // Method to get order number for a product
    private int getOrderNumber(Product product) {
        for (Map.Entry<Integer, List<Product>> entry : orderMap.entrySet()) {
            if (entry.getValue().contains(product)) {
                return entry.getKey();
            }
        }
        return -1; // Not found
    }
}

// Client class to test OrderProcessor
public class Main {
    public static void main(String[] args) {
        // Creating orders
        GenericOrder<Product> computerOrder = new ComputerOrder();
        computerOrder.addItem(new ComputerPart("Motherboard", 37.5));
        computerOrder.addItem(new ComputerPart("Motherboard", 37.5)); // Duplicate item
        computerOrder.addItem(new Peripheral("Mouse", 15.0));

        GenericOrder<Product> partyTrayOrder = new PartyTrayOrder();
        partyTrayOrder.addItem(new Cheese("Cheddar", 5.0));
        partyTrayOrder.addItem(new Fruit("Apple", 2.0));
        partyTrayOrder.addItem(new ComputerPart("RAM", 25.0)); // Adding another type of product

        // Creating and initializing OrderProcessor
        OrderProcessor orderProcessor = new OrderProcessor();

        // Accepting orders
        orderProcessor.accept(computerOrder);
        orderProcessor.accept(partyTrayOrder);

        // Processing and dispatching orders
        orderProcessor.process();
        orderProcessor.dispatchComputerParts();
        orderProcessor.dispatchPeripherals();
        orderProcessor.dispatchCheeses();
        orderProcessor.dispatchFruits();
        orderProcessor.dispatchServices();
    }
}
