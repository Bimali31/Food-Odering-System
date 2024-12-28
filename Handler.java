
import java.util.Scanner;

public interface Handler {
    void setNextHandler(Handler handler);
    void handle(int choice, UserProfile userProfile, Scanner scanner);
}
