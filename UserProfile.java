import java.util.ArrayList;
import java.util.List;

public class UserProfile {
    private String username;
    private List<PizzaCombination> favoritePizzas;

    public UserProfile(String username) {
        this.username = username;
        this.favoritePizzas = new ArrayList<>();
    }

    public void addFavoritePizza(PizzaCombination pizza) {
        favoritePizzas.add(pizza);
    }

    public void removeFavoritePizza(PizzaCombination pizza) {
        favoritePizzas.remove(pizza);
    }

    public List<PizzaCombination> getFavoritePizzas() {
        return favoritePizzas;
    }

    public String getUsername() {
        return username;
    }
}
