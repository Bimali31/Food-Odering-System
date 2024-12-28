
import java.util.Scanner;

public class AddPizzaHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handle(int choice, UserProfile userProfile, Scanner scanner) {
        if (choice == 2) {
            System.out.print("\nEnter Pizza Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Pizza Size (e.g., Medium, Large): ");
            String size = scanner.nextLine();
            System.out.print("Enter Crust: ");
            String crust = scanner.nextLine();
            System.out.print("Enter Sauce: ");
            String sauce = scanner.nextLine();
            System.out.print("Enter Toppings: ");
            String toppings = scanner.nextLine();
            System.out.print("Enter Cheese: ");
            String cheese = scanner.nextLine();
            System.out.print("Enter Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            PizzaCombination newPizza = new PizzaCombination(name, size, crust, sauce, toppings, cheese, price);
            userProfile.addFavoritePizza(newPizza);
            System.out.println("New pizza added to favorites!");
        } else if (nextHandler != null) {
            nextHandler.handle(choice, userProfile, scanner);
        }
    }
}
