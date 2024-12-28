import java.util.List;
import java.util.Scanner;

public class ViewFavoritesHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handle(int choice, UserProfile userProfile, Scanner scanner) {
        if (choice == 1) {
            System.out.println("\nYour Favorite Pizzas:");
            List<PizzaCombination> favorites = userProfile.getFavoritePizzas();
            for (int i = 0; i < favorites.size(); i++) {
                System.out.println((i + 1) + ". " + favorites.get(i));
            }
        } else if (nextHandler != null) {
            nextHandler.handle(choice, userProfile, scanner);
        }
    }
}

