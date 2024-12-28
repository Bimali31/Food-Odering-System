
import java.util.List;
import java.util.Scanner;

public class OrderPizzaHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handle(int choice, UserProfile userProfile, Scanner scanner) {
        if (choice == 4) {
            System.out.println("\nSelect a pizza to order:");
            List<PizzaCombination> favorites = userProfile.getFavoritePizzas();
            for (int i = 0; i < favorites.size(); i++) {
                System.out.println((i + 1) + ". " + favorites.get(i));
            }
            int orderChoice = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (orderChoice >= 0 && orderChoice < favorites.size()) {
                PizzaCombination orderedPizza = favorites.get(orderChoice);
                System.out.println("You have ordered: " + orderedPizza);
            } else {
                System.out.println("Invalid choice.");
            }
        } else if (nextHandler != null) {
            nextHandler.handle(choice, userProfile, scanner);
        }
    }
}
