// Pizza.java
import java.util.ArrayList;
import java.util.List;

// Pizza Class (Base class)
public class Pizza {
    private String name;
    private String crust;
    private String sauce;
    private List<String> toppings = new ArrayList<>();
    private String cheese;
    private String size;
    private int price;

    public Pizza(String name, String crust, String sauce, List<String> toppings, String cheese, String size, int price) {
        this.name = name;
        this.crust = crust;
        this.sauce = sauce;
        this.toppings = toppings;
        this.cheese = cheese;
        this.size = size;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCrust() {
        return crust;
    }

    public String getSauce() {
        return sauce;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public String getCheese() {
        return cheese;
    }

    public String getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public void displayPizzaDetails() {
        System.out.println("Pizza Name: " + name);
        System.out.println("Crust: " + crust);
        System.out.println("Sauce: " + sauce);
        System.out.println("Toppings: " + String.join(", ", toppings));
        System.out.println("Cheese: " + cheese);
        System.out.println("Size: " + size);
        System.out.println("Price: $" + price);
    }
}

// PizzaBuilder class (Builder Pattern)
class PizzaBuilder {
    private String name;
    private String crust;
    private String sauce;
    private List<String> toppings = new ArrayList<>();
    private String cheese;
    private String size;
    private int price;

    public PizzaBuilder(String name) {
        this.name = name;
    }

    public PizzaBuilder setCrust(String crust) {
        this.crust = crust;
        return this;
    }

    public PizzaBuilder setSauce(String sauce) {
        this.sauce = sauce;
        return this;
    }

    public PizzaBuilder setToppings(List<String> toppings) {
        this.toppings = toppings;
        return this;
    }

    public PizzaBuilder setCheese(String cheese) {
        this.cheese = cheese;
        return this;
    }

    public PizzaBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    public PizzaBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public Pizza build() {
        return new Pizza(name, crust, sauce, toppings, cheese, size, price);
    }
}

// PizzaDecorator class (Decorator Pattern)
abstract class PizzaDecorator extends Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        super(pizza.getName(), pizza.getCrust(), pizza.getSauce(), pizza.getToppings(), pizza.getCheese(), pizza.getSize(), pizza.getPrice());
        this.pizza = pizza;
    }
    

    public abstract void displayPizzaDetails();
}

class ExtraCheesePizza extends PizzaDecorator {
    public ExtraCheesePizza(Pizza pizza) {
        super(pizza);
    }

    @Override
    public void displayPizzaDetails() {
        pizza.displayPizzaDetails();
        System.out.println("Extra Cheese Added!");
    }
}


