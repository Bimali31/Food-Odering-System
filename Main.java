import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// DeliveryMappingService to calculate estimated delivery time
class DeliveryMappingService {
    public int calculateDeliveryTime(String location) {
        // Mock logic for delivery time based on location
        return switch (location.toLowerCase()) {
            case "downtown" -> 20;
            case "suburbs" -> 30;
            case "outskirts" -> 40;
            default -> 50;
        }; // 20 minutes
        // 30 minutes
        // 40 minutes
        // Default time
    }
}
class Payment {
    private PaymentStrategy paymentMethod;

    public Payment(PaymentStrategy paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void makePayment(double amount) {
        paymentMethod.pay(amount);
    }
}

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);
        String name, cardNumber, expirationDate, cvv;

        while (true) {
            System.out.println("Enter Cardholder's Name: ");
            name = scanner.nextLine();
            System.out.println("Enter Card Number: ");
            cardNumber = scanner.nextLine();
            System.out.println("Enter Expiration Date (MM/YY): ");
            expirationDate = scanner.nextLine();
            System.out.println("Enter CVV: ");
            cvv = scanner.nextLine();

            if (!name.isEmpty() && !cardNumber.isEmpty() && !expirationDate.isEmpty() && !cvv.isEmpty()) {
                System.out.println("Payment of $" + amount + " made successfully via Credit Card.");
                break;
            } else {
                System.out.println("All fields are required. Please enter the card details again.");
            }
        }
    }
}

class DigitalWalletPayment implements PaymentStrategy {
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Wallet Address: ");
        String walletAddress = scanner.nextLine();

        if (!walletAddress.isEmpty()) {
            System.out.println("Payment of $" + amount + " made successfully via Digital Wallet.");
        } else {
            System.out.println("Wallet address is required. Please try again.");
        }
    }
}

class LoyaltyProgram {
    private int points;

    public LoyaltyProgram() {
        this.points = 0;
    }

    public void addPoints(double totalPrice) {
        // 1 point for every $10 spent
        points += totalPrice / 10;
    }

    public void applyLoyaltyDiscount(int quantity, String pizzaSize) {
        if (pizzaSize.equals("3") && quantity == 2) {
            System.out.println("You've earned 2 free Lava Cakes!");
        }
        if ((pizzaSize.equals("3") && quantity == 1) || (pizzaSize.equals("2") && quantity == 1)) {
            System.out.println("You've earned a 25% discount on your order!");
        }
    }
}


public class Main {
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            DeliveryMappingService deliveryService = new DeliveryMappingService();

            // Welcome message
            System.out.println("Welcome to the Pizza Ordering System!");

            // Choose Pizza Customization or Seasonal Specials
            int choice;
            while (true) {
                System.out.println("1. Pizza Customization");
                System.out.println("2. Seasonal Specials");
                System.out.println("3. Favourites");
                System.out.println("4. IntroduceSeasonalSpecial");
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (choice == 1 || choice == 2|| choice == 3 || choice == 4) break;
                System.out.println("The number you typed is incorrect. Please type a valid number.");
            }

            switch (choice) {
                case 1 -> {
                    // Pizza Customization
                    String pizzaChoice = null;
                    List<Integer> selectedCategories = new ArrayList<>(); // Initialize selectedCategories list
        
        while (true) {
            System.out.println("Select Pizza Category (you can select multiple categories by entering numbers separated by commas): ");
            System.out.println("1. Margherita - Regular $8, Medium $10, Large $12");
            System.out.println("2. Pepperoni - Regular $9, Medium $11, Large $13");
            System.out.println("3. Veggie - Regular $7, Medium $9, Large $11");
            System.out.println("4. BBQ Chicken - Regular $10, Medium $12, Large $14");
            System.out.println("5. Hawaiian - Regular $8, Medium $10, Large $12");
            System.out.println("6. Meat Feast - Regular $11, Medium $13, Large $15");
            System.out.println("7. Four Cheese - Regular $9, Medium $11, Large $13");
            System.out.println("8. Seafood - Regular $12, Medium $14, Large $16");
            System.out.println("9. Supreme - Regular $13, Medium $15, Large $17");
            System.out.println("10. Special Veggie - Regular $8, Medium $10, Large $12");
            System.out.println("Enter Pizza Numbers (e.g., 1,2,3): ");
            
            String pizzaChoiceInput = scanner.nextLine();
            String[] selectedChoices = pizzaChoiceInput.split(","); // Split user input by commas

            boolean isValid = true;
            selectedCategories.clear(); // Clear previous selections to allow fresh input
            
            for (String choiceStr : selectedChoices) {
                choiceStr = choiceStr.trim(); // Trim whitespace
                if (choiceStr.matches("[1-9]|10")) { 
                    selectedCategories.add(Integer.parseInt(choiceStr)); // Add to selectedCategories list
                } else {
                    isValid = false; // Mark invalid if any choice is incorrect
                    break;
                }
            }

            if (isValid && !selectedCategories.isEmpty()) {
                // Break out of the loop if all inputs are valid and some categories were selected
                break;
            }

            System.out.println("One or more numbers you typed are incorrect. Please type valid numbers.");
        }

        // Now collect size and quantity details for each selected pizza
        Map<Integer, String> pizzaSizes = new HashMap<>();
        Map<Integer, Integer> pizzaQuantities = new HashMap<>();
        
        for (int pizza : selectedCategories) {
            // Ask for size
            System.out.println("For Pizza Category " + pizza + ", choose size (1 for Regular, 2 for Medium, 3 for Large): ");
            String sizeChoiceInput = scanner.nextLine();
            pizzaSizes.put(pizza, sizeChoiceInput);

            // Ask for quantity
            System.out.println("Enter quantity for Pizza Category " + pizza + ": ");
            int quantity = scanner.nextInt();
            pizzaQuantities.put(pizza, quantity);

            scanner.nextLine(); // Consume the newline left by nextInt()
        }

        // Display the order summary
        System.out.println("Your order summary:");
        for (int pizza : selectedCategories) {
            String size = pizzaSizes.get(pizza);
            int quantity = pizzaQuantities.get(pizza);

            String sizeName = "Unknown";
            switch (size) {
                case "1": sizeName = "Regular"; break;
                case "2": sizeName = "Medium"; break;
                case "3": sizeName = "Large"; break;
            }

            System.out.println("Pizza Category " + pizza + " (Size: " + sizeName + ", Quantity: " + quantity + ")");
        }



                    // Pizza Builder for Customization
                    PizzaBuilder builder = new PizzaBuilder("Custom Pizza");

                    // Crust Selection
                    String crustChoice;
                    while (true) {
                        System.out.println("Select Crust: ");
                        System.out.println("1. Thin");
                        System.out.println("2. Thick");
                        System.out.println("3. Stuffed");
                        System.out.println("4. No");
                        crustChoice = scanner.nextLine();
                        if (crustChoice.matches("[1-4]")) break;
                        System.out.println("The number you typed is incorrect. Please type a valid number.");
                    }
                    builder.setCrust(crustChoice);

                    // Sauce Selection
                    String sauceChoice;
                    while (true) {
                        System.out.println("Select Sauce: ");
                        System.out.println("1. Tomato");
                        System.out.println("2. BBQ");
                        System.out.println("3. Alfredo");
                        System.out.println("4. No");
                        sauceChoice = scanner.nextLine();
                        if (sauceChoice.matches("[1-4]")) break;
                        System.out.println("The number you typed is incorrect. Please type a valid number.");
                    }
                    builder.setSauce(sauceChoice);

                    // Toppings Selection
                    System.out.println("Select Toppings (comma separated): ");
                    System.out.println("1. Pepperoni");
                    System.out.println("2. Mushrooms");
                    System.out.println("3. Onions");
                    System.out.println("4. Olives");
                    System.out.println("5. Bacon");
                    System.out.println("6. Pineapple");
                    System.out.println("7. No");
                    String toppings = scanner.nextLine();
                    List<String> toppingsList = Arrays.asList(toppings.split(","));
                    builder.setToppings(toppingsList);

                    // Cheese Selection
                    String cheeseChoice;
                    while (true) {
                        System.out.println("Select Cheese: ");
                        System.out.println("1. Mozzarella");
                        System.out.println("2. Cheddar");
                        System.out.println("3. Parmesan");
                        System.out.println("4. No");
                        cheeseChoice = scanner.nextLine();
                        if (cheeseChoice.matches("[1-4]")) break;
                        System.out.println("The number you typed is incorrect. Please type a valid number.");
                    }
                    builder.setCheese(cheeseChoice);

                    System.out.println("Click the enter key to see the price.");
                    
                    

                    double totalPrice = 0;
                    
            for (int pizza : selectedCategories) {
                String size = pizzaSizes.get(pizza);
                int quantity = pizzaQuantities.get(pizza);
                double pricePerPizza = 0;

                switch (size) {
                    case "1": // Regular
                        pricePerPizza = switch (pizza) {
                            case 1 -> 8;
                            case 2 -> 9;
                            case 3 -> 7;
                            case 4 -> 10;
                            case 5 -> 8;
                            case 6 -> 11;
                            case 7 -> 9;
                            case 8 -> 12;
                            case 9 -> 13;
                            case 10 -> 8;
                            default -> 0;
                        };
                        break;
                    case "2": // Medium
                        pricePerPizza = switch (pizza) {
                            case 1 -> 10;
                            case 2 -> 11;
                            case 3 -> 9;
                            case 4 -> 12;
                            case 5 -> 10;
                            case 6 -> 13;
                            case 7 -> 11;
                            case 8 -> 14;
                            case 9 -> 15;
                            case 10 -> 10;
                            default -> 0;
                        };
                        break;
                    case "3": // Large
                        pricePerPizza = switch (pizza) {
                            case 1 -> 12;
                            case 2 -> 13;
                            case 3 -> 11;
                            case 4 -> 14;
                            case 5 -> 12;
                            case 6 -> 15;
                            case 7 -> 13;
                            case 8 -> 16;
                            case 9 -> 17;
                            case 10 -> 12;
                            default -> 0;
                        };
                        break;
                        
                }
                totalPrice += pricePerPizza * quantity;
                
String pizzaSize = pizzaSizes.get(pizza); // Get the pizza size from the pizzaSizes map


String sizeChoice;


String sizeName = "Unknown";
switch (pizzaSize) {
    case "1": sizeName = "Regular"; break;
    case "2": sizeName = "Medium"; break;
    case "3": sizeName = "Large"; break;
}

                
                if (pizzaSize.equals("3") && quantity == 2) {
                    System.out.println("You've earned 2 free Lava Cakes!");
                }
                
                double discountPrice = 0;

// Check if the pizza size is "Large" and quantity is 1 or if the pizza size is "Medium" and quantity is 1
if ((pizzaSize.equals("3") && quantity == 1) || (pizzaSize.equals("2") && quantity == 1)) {
    discountPrice = totalPrice * 0.25; // Calculate the discount
    totalPrice -= discountPrice; // Subtract the discount from the total price
    System.out.println("You've earned a 25% discount on your order!");
}
                
            }


            System.out.println("Your total price is: $" + totalPrice);


                    // Confirm Order
                    System.out.println("Do you want to confirm your order? (yes/no)");
                    String confirmOrder = scanner.nextLine();
                    if (confirmOrder.equalsIgnoreCase("yes")) {
                        // Pickup or Delivery option
                        int deliveryChoice;
                        while (true) {
                            System.out.println("Choose Pickup or Delivery: ");
                            System.out.println("1. Pickup");
                            System.out.println("2. Delivery");
                            deliveryChoice = scanner.nextInt();
                            scanner.nextLine();
                            if (deliveryChoice == 1 || deliveryChoice == 2) break;
                            System.out.println("The number you typed is incorrect. Please type a valid number.");
                        }
                        if (deliveryChoice == 1){
                            System.out.println("Delivery estimated time: 15 minutes.");
                        }

                        if (deliveryChoice == 2) {
                            // Delivery mapping
                            System.out.println("Enter your location (Downtown/Suburbs/Outskirts): ");
                            String location = scanner.nextLine();
                            int deliveryTime = deliveryService.calculateDeliveryTime(location);
                            System.out.println("Delivery estimated time: " + deliveryTime + " minutes.");
                        }
                        

                        // Proceed to Payment
                        int paymentChoice;
                        while (true) {
                            System.out.println("Choose Payment Method: ");
                            System.out.println("1. Credit Card");
                            System.out.println("2. Digital Wallet");
                            paymentChoice = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            if (paymentChoice == 1 || paymentChoice == 2) break;
                            System.out.println("The number you typed is incorrect. Please type a valid number.");
                        }

                        PaymentStrategy paymentMethod = (paymentChoice == 1) ? new CreditCardPayment() : new DigitalWalletPayment();
                        Payment payment = new Payment(paymentMethod);

                        System.out.println("Enter Payment Details: ");
                        payment.makePayment(totalPrice);
                        
                        
                    } else {
                        System.out.println("Order canceled.");
                    }
                    // Declare the scanner once at the beginning
        

        // Create Feedback object and pass the existing scanner
        Feedback feedback = new Feedback(scanner);

        // Simulate the pizza ordering process
        System.out.println("Welcome to the pizza ordering system!");

        // Main pizza customization and ordering logic can go here
        System.out.println("Pizza customization and ordering process goes here...");

        // Assuming the user has confirmed the order:
        System.out.println("Your order has been confirmed!");

        // Prompt user for feedback
        System.out.print("Would you like to provide feedback on your pizza? (yes/no): ");
        String feedbackChoice = scanner.nextLine();

        if (feedbackChoice.equalsIgnoreCase("yes")) {
            feedback.getFeedback();  // Get feedback from the user
            feedback.displayFeedback();  // Display the feedback
        } else {
            System.out.println("Thank you for your order!");
        }
        // Initialize Scanner once
  
                }
                

                case 2 -> {
                    
                    // Seasonal Specials
                    String pizzaChoice = null;
                    List<Integer> selectedCategories = new ArrayList<>(); // Initialize selectedCategories list
        
        while (true) {
            System.out.println("Select Pizza Category (you can select multiple categories by entering numbers separated by commas): ");
            System.out.println("1. Winter Special - Regular $8, Medium $10, Large $12");
            System.out.println("2. Summer Special - Regular $9, Medium $11, Large $13");
            System.out.println("3. Autumn Special - Regular $7, Medium $9, Large $11");
            System.out.println("4. Spring Special - Regular $10, Medium $12, Large $14");
            System.out.println("Enter Pizza Numbers (e.g., 1,2,3): ");
            
            String pizzaChoiceInput = scanner.nextLine();
            String[] selectedChoices = pizzaChoiceInput.split(","); 

            boolean isValid = true;
            selectedCategories.clear(); 
            
            for (String choiceStr : selectedChoices) {
                choiceStr = choiceStr.trim(); 
                if (choiceStr.matches("[1-9]|10")) { 
                    selectedCategories.add(Integer.parseInt(choiceStr)); // Add to selectedCategories list
                } else {
                    isValid = false;
                    break;
                }
            }

            if (isValid && !selectedCategories.isEmpty()) {
               
                break;
            }

            System.out.println("One or more numbers you typed are incorrect. Please type valid numbers.");
        }

        
        Map<Integer, String> pizzaSizes = new HashMap<>();
        Map<Integer, Integer> pizzaQuantities = new HashMap<>();
        
        for (int pizza : selectedCategories) {
            // Ask for size
            System.out.println("For Pizza Category " + pizza + ", choose size (1 for Regular, 2 for Medium, 3 for Large): ");
            String sizeChoiceInput = scanner.nextLine();
            pizzaSizes.put(pizza, sizeChoiceInput);

            // Ask for quantity
            System.out.println("Enter quantity for Pizza Category " + pizza + ": ");
            int quantity = scanner.nextInt();
            pizzaQuantities.put(pizza, quantity);

            scanner.nextLine(); 
        }
        // Display the order summary
        System.out.println("Your order summary:");
        for (int pizza : selectedCategories) {
            String size = pizzaSizes.get(pizza);
            int quantity = pizzaQuantities.get(pizza);

            String sizeName = "Unknown";
            switch (size) {
                case "1": sizeName = "Regular"; break;
                case "2": sizeName = "Medium"; break;
                case "3": sizeName = "Large"; break;
            }

            System.out.println("Pizza Category " + pizza + " (Size: " + sizeName + ", Quantity: " + quantity + ")");
        }
        
        double totalSpecialPrice = 0;
                    
            for (int pizza : selectedCategories) {
                String size = pizzaSizes.get(pizza);
                int quantity = pizzaQuantities.get(pizza);
                double pricePerPizza = 0;

                switch (size) {
                    case "1": // Regular
                        pricePerPizza = switch (pizza) {
                            case 1 -> 8;
                            case 2 -> 9;
                            case 3 -> 7;
                            case 4 -> 10;
                            
                            default -> 0;
                        };
                        break;
                    case "2": // Medium
                        pricePerPizza = switch (pizza) {
                            case 1 -> 10;
                            case 2 -> 11;
                            case 3 -> 9;
                            case 4 -> 12;
                            
                            default -> 0;
                        };
                        break;
                    case "3": // Large
                        pricePerPizza = switch (pizza) {
                            case 1 -> 12;
                            case 2 -> 13;
                            case 3 -> 11;
                            case 4 -> 14;
                            
                            default -> 0;
                        };
                        break;
                        
                }
                
                        totalSpecialPrice += pricePerPizza * quantity;
                String pizzaSize = pizzaSizes.get(pizza); 


String sizeChoice;


String sizeName = "Unknown";
switch (pizzaSize) {
    case "1": sizeName = "Regular"; break;
    case "2": sizeName = "Medium"; break;
    case "3": sizeName = "Large"; break;
}
double discountPrice = 0;
                    
                if ((pizzaSize.equals("3") && quantity == 1))  {
                    discountPrice = totalSpecialPrice * 0.25; // Calculate the discount
                    totalSpecialPrice -= discountPrice; // Subtract the discount from the total price
                    System.out.println("You've earned a 25% discount on your order!");
                }
            }
                System.out.println("Your total price is: $" + totalSpecialPrice);
                    
                
                    // Confirm Order
                    System.out.println("Do you want to confirm your order? (yes/no)");
                    String confirmOrder = scanner.nextLine();
                    if (confirmOrder.equalsIgnoreCase("yes")) {
                        // Pickup or Delivery option
                        int deliveryChoice;
                        while (true) {
                            System.out.println("Choose Pickup or Delivery: ");
                            System.out.println("1. Pickup");
                            System.out.println("2. Delivery");
                            deliveryChoice = scanner.nextInt();
                            scanner.nextLine();
                            if (deliveryChoice == 1 || deliveryChoice == 2) break;
                            System.out.println("The number you typed is incorrect. Please type a valid number.");
                        }
                        if (deliveryChoice == 1){
                            System.out.println("Delivery estimated time: 15 minutes.");
                        }

                        if (deliveryChoice == 2) {
                            // Delivery mapping
                            System.out.println("Enter your location (Downtown/Suburbs/Outskirts): ");
                            String location = scanner.nextLine();
                            int deliveryTime = deliveryService.calculateDeliveryTime(location);
                            System.out.println("Delivery estimated time: " + deliveryTime + " minutes.");
                        }
                        

                        // Proceed to Payment
                        int paymentChoice;
                        while (true) {
                            System.out.println("Choose Payment Method: ");
                            System.out.println("1. Credit Card");
                            System.out.println("2. Digital Wallet");
                            paymentChoice = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            if (paymentChoice == 1 || paymentChoice == 2) break;
                            System.out.println("The number you typed is incorrect. Please type a valid number.");
                        }

                        PaymentStrategy paymentMethod = (paymentChoice == 1) ? new CreditCardPayment() : new DigitalWalletPayment();
                        Payment payment = new Payment(paymentMethod);

                        System.out.println("Enter Payment Details: ");
                        payment.makePayment(totalSpecialPrice);
                        
                        
                    } else {
                        System.out.println("Order canceled.");
                    }
                
                // Create Feedback object and pass the existing scanner
        Feedback feedback = new Feedback(scanner);

        // Simulate the pizza ordering process
        System.out.println("Welcome to the pizza ordering system!");

        // Main pizza customization and ordering logic can go here
        System.out.println("Pizza customization and ordering process goes here...");

        // Assuming the user has confirmed the order:
        System.out.println("Your order has been confirmed!");

        // Prompt user for feedback
        System.out.print("Would you like to provide feedback on your pizza? (yes/no): ");
        String feedbackChoice = scanner.nextLine();

        if (feedbackChoice.equalsIgnoreCase("yes")) {
            feedback.getFeedback();  // Get feedback from the user
            feedback.displayFeedback();  // Display the feedback
        } else {
            System.out.println("Thank you for your order!");
        }
            }
            case 3 ->{
                // Sample pizzas (user would normally create these)
        PizzaCombination pizza1 = new PizzaCombination("Pepperoni", "Medium","Thin", "Tomato", "Cheese, Pepperoni","Mozzarella", 12.99);
        PizzaCombination pizza2 = new PizzaCombination("Pepperoni","Large","Thick","BBQ", "Cheese, Mushrooms, Olives","Cheddar", 15.99);
        PizzaCombination pizza3 = new PizzaCombination("BBQ Chicken","Small","Thin","Alfredo", "Cheese, Bacon","Mozzarella", 11.99);

        // Create user profile
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        UserProfile userProfile = new UserProfile(username);

        // Create chain of handlers
        Handler viewFavoritesHandler = new ViewFavoritesHandler();
        Handler addPizzaHandler = new AddPizzaHandler();
        Handler removePizzaHandler = new RemovePizzaHandler();
        Handler orderPizzaHandler = new OrderPizzaHandler();

        viewFavoritesHandler.setNextHandler(addPizzaHandler);
        addPizzaHandler.setNextHandler(removePizzaHandler);
        removePizzaHandler.setNextHandler(orderPizzaHandler);

        // Simulate adding pizzas to favorites
        userProfile.addFavoritePizza(pizza1);
        userProfile.addFavoritePizza(pizza2);
        userProfile.addFavoritePizza(pizza3);

        boolean continueShopping = true;
        while (continueShopping) {
            System.out.println("\nWelcome " + userProfile.getUsername() + "! What would you like to do?");
            System.out.println("1. View Favorite Pizzas");
            System.out.println("2. Add New Pizza to Favorites");
            System.out.println("3. Remove Pizza from Favorites");
            System.out.println("4. Order Pizza from Favorites");
            System.out.println("5. Exit");

            // Fixed duplicate variable 'choice'
            int menuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String crust;
                        String sauce;
                                                switch (menuChoice) {
                                                    case 1:
                                                        // View favorite pizzas
                                                        System.out.println("\nYour Favorite Pizzas:");
                                                        for (int i = 0; i < userProfile.getFavoritePizzas().size(); i++) {
                                                            System.out.println((i + 1) + ". " + userProfile.getFavoritePizzas().get(i));
                                                        }
                                                        break;
                                    
                                                    case 2:
                                                    System.out.println("1. Margherita - Regular $8, Medium $10, Large $12");
            System.out.println("2. Pepperoni - Regular $9, Medium $11, Large $13");
            System.out.println("3. Veggie - Regular $7, Medium $9, Large $11");
            System.out.println("4. BBQ Chicken - Regular $10, Medium $12, Large $14");
            System.out.println("5. Hawaiian - Regular $8, Medium $10, Large $12");
            System.out.println("6. Meat Feast - Regular $11, Medium $13, Large $15");
            System.out.println("7. Four Cheese - Regular $9, Medium $11, Large $13");
            System.out.println("8. Seafood - Regular $12, Medium $14, Large $16");
            System.out.println("9. Supreme - Regular $13, Medium $15, Large $17");
            System.out.println("10. Special Veggie - Regular $8, Medium $10, Large $12");
            System.out.println("1. Winter Special - Regular $8, Medium $10, Large $12");
            System.out.println("2. Summer Special - Regular $9, Medium $11, Large $13");
            System.out.println("3. Autumn Special - Regular $7, Medium $9, Large $11");
            System.out.println("4. Spring Special - Regular $10, Medium $12, Large $14");
                                                        // Add new pizza to favorites
                                                        System.out.print("\nEnter Pizza Name: ");
                                                        String name = scanner.nextLine();
                                                        System.out.print("\nEnter Pizza Size (e.g., Medium, Large): ");
                                                        String size = scanner.nextLine();
                                                        System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                                                        System.out.println("1. Thin");
                        System.out.println("2. Thick");
                        System.out.println("3. Stuffed");
                        System.out.println("4. No");
                                                        System.out.print("Enter Crust (comma-separated): ");
                                                        crust = scanner.nextLine();
                                                        System.out.println("1. Tomato");
                        System.out.println("2. BBQ");
                        System.out.println("3. Alfredo");
                        System.out.println("4. No");
                                            System.out.print("Enter Sauce (comma-separated): ");
                                            sauce = scanner.nextLine();
                                            System.out.println("1. Pepperoni");
                    System.out.println("2. Mushrooms");
                    System.out.println("3. Onions");
                    System.out.println("4. Olives");
                    System.out.println("5. Bacon");
                    System.out.println("6. Pineapple");
                    System.out.println("7. No");
                    System.out.print("Enter Toppings (comma-separated): ");
                String toppings = scanner.nextLine();
                System.out.println("1. Mozzarella");
                        System.out.println("2. Cheddar");
                        System.out.println("3. Parmesan");
                        System.out.println("4. No");
                    System.out.print("Enter Cheese (comma-separated): ");
                String cheese = scanner.nextLine();
                     // Consume newline

                    PizzaCombination newPizza = new PizzaCombination(name, size, crust, sauce, toppings, cheese, price);
                    userProfile.addFavoritePizza(newPizza);
                    System.out.println("New pizza added to favorites!");
                    break;



                case 3:
                    // Remove pizza from favorites
                    System.out.println("\nSelect a pizza to remove:");
                    for (int i = 0; i < userProfile.getFavoritePizzas().size(); i++) {
                        System.out.println((i + 1) + ". " + userProfile.getFavoritePizzas().get(i));
                    }
                    int removeChoice = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline

                    if (removeChoice >= 0 && removeChoice < userProfile.getFavoritePizzas().size()) {
                        userProfile.removeFavoritePizza(userProfile.getFavoritePizzas().get(removeChoice));
                        System.out.println("Pizza removed from favorites!");
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 4:
                    // Order pizza from favorites
                    System.out.println("\nSelect a pizza to order:");
                    for (int i = 0; i < userProfile.getFavoritePizzas().size(); i++) {
                        System.out.println((i + 1) + ". " + userProfile.getFavoritePizzas().get(i));
                    }
                    int orderChoice = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline

                    if (orderChoice >= 0 && orderChoice < userProfile.getFavoritePizzas().size()) {
                        PizzaCombination orderedPizza = (PizzaCombination) userProfile.getFavoritePizzas().get(orderChoice);
                        System.out.println("You have ordered: " + orderedPizza);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 5:
                    // Exit
                    System.out.println("Thank you for using the Pizza Order System!");
                    continueShopping = false;
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close(); 
            }
            case 4 -> {
                if (choice == 4) {
                    
                    // Input Pizza Name
                System.out.print("Enter Pizza Name: ");
                String pizzaName = scanner.nextLine();

                // Input Prices for Regular, Medium, and Large
                System.out.print("Enter Regular Pizza Price: ");
                double regularPrice = scanner.nextDouble();

                System.out.print("Enter Medium Pizza Price: ");
                double mediumPrice = scanner.nextDouble();

                System.out.print("Enter Large Pizza Price: ");
                double largePrice = scanner.nextDouble();

                // Input the discount percentage
                System.out.print("Enter Discount Percentage: ");
                double discountPercentage = scanner.nextDouble();

                // Calculate discounted prices
                double discountedRegularPrice = regularPrice - (regularPrice * discountPercentage / 100);
                double discountedMediumPrice = mediumPrice - (mediumPrice * discountPercentage / 100);
                double discountedLargePrice = largePrice - (largePrice * discountPercentage / 100);

                // Assuming the discount name is "Winter Special" for now
                String discount = "Winter Special";

                // Call the method to display the seasonal special with the discounted prices
                displayIntroduceSeasonalSpecial(pizzaName, discountedRegularPrice, discountedMediumPrice, discountedLargePrice, discount);
                } else if (choice == 1) {
                    // Add your Pizza Customization code here if needed
                    System.out.println("Pizza Customization feature is not yet implemented.");
                }

                
        
            }
            }
        }
    }

    private static void displayIntroduceSeasonalSpecial(String pizzaName, double regularPrice, double mediumPrice, double largePrice, String discount) {
        System.out.println("\nSeasonal Special: " + discount);
        System.out.println("1. " + pizzaName + " - Regular $" + String.format("%.2f", regularPrice));
        System.out.println("   " + pizzaName + " - Medium $" + String.format("%.2f", mediumPrice));
        System.out.println("   " + pizzaName + " - Large $" + String.format("%.2f", largePrice));
    }
}
