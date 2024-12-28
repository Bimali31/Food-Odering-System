import java.util.List;

public class FavoritePizza {
    private String pizzaName;
    private String size;
    private String crust;
    private String sauce;
    private List<String> toppings;
    private String cheese;

    public FavoritePizza(String pizzaName, String size, String crust, String sauce, List<String> toppings, String cheese) {
        this.pizzaName = pizzaName;
        this.size = size;
        this.crust = crust;
        this.sauce = sauce;
        this.toppings = toppings;
        this.cheese = cheese;
    }

    // Getters and setters
    public String getPizzaName() {
        return pizzaName;
    }

    public String getSize() {
        return size;
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

    @Override
    public String toString() {
        return "Pizza Name: " + pizzaName + ", Size: " + size + ", Crust: " + crust + ", Sauce: " + sauce + 
               ", Toppings: " + toppings + ", Cheese: " + cheese;
    }
}
