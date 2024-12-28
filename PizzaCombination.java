public class PizzaCombination {
    private String name;
    private String size;
    private String crust;
    private String sauce;
    private String toppings;
    private String cheese;
    private double price;

    public PizzaCombination(String name,String size, String crust, String sauce, String toppings, String cheese, double price) {
        this.name = name;
        this.size = size;
        this.crust = crust;
        this.sauce = sauce;
        this.toppings = toppings;
        this.cheese = cheese;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name: " + name + "Size: " + size + ", Crust: " + crust + ", Sauce: " + sauce + ", Toppings: " + toppings + ", Cheese: " + cheese + ", Price: $" + price ;
    }

    public double getPrice() {
        return price;
    }
}
