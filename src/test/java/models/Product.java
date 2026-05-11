package models;

public class Product {

    private String name;
    private String category;
    private String price;
    private String availability;
    private String condition;
    private String brand;

    // Constructor
    public Product(String name, String category, String price,
                   String availability, String condition, String brand) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.availability = availability;
        this.condition = condition;
        this.brand = brand;
    }

    // Getters
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getPrice() { return price; }
    public String getAvailability() { return availability; }
    public String getCondition() { return condition; }
    public String getBrand() { return brand; }
}