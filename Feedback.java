import java.util.Scanner;

public class Feedback {
    private int rating;
    private String comments;
    private Scanner scanner;

    // Constructor to accept the existing scanner
    public Feedback(Scanner scanner) {
        this.scanner = scanner;
    }

    public void getFeedback() {
        System.out.print("Please rate your pizza (1-5): ");
        rating = scanner.nextInt();
        scanner.nextLine();  // consume the newline
        System.out.print("Enter your comments: ");
        comments = scanner.nextLine();
    }

    public void displayFeedback() {
        System.out.println("Rating: " + rating + "/5");
        System.out.println("Comments: " + comments);
    }
}
