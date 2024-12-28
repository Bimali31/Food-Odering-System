
import java.util.List;
import java.util.Scanner;

public class RemovePizzaHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handle(int choice, UserProfile userProfile, Scanner scanner) {
        if (choice == 3) {
            System.out.println("\nSelect a pizza to remove:");
            List<PizzaCombination> favorites = userProfile.getFavoritePizzas();
            for (int i = 0; i < favorites.size(); i++) {
                System.out.println((i + 1) + ". " + favorites.get(i));
            }
            int removeChoice = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (removeChoice >= 0 && removeChoice < favorites.size()) {
                userProfile.removeFavoritePizza(favorites.get(removeChoice));
                System.out.println("Pizza removed from favorites!");
            } else {
                System.out.println("Invalid choice.");
            }
        } else if (nextHandler != null) {
            nextHandler.handle(choice, userProfile, scanner);
        }
    }
}
