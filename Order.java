// Order.java
public class Order {
    private OrderState state;

    public Order() {
        state = new NewOrderState(); // Initial state
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void processOrder() {
        state.handleRequest(this);
    }
}

interface OrderState {
    void handleRequest(Order order);
}

class NewOrderState implements OrderState {
    public void handleRequest(Order order) {
        System.out.println("Order is now being prepared.");
        order.setState(new PreparingOrderState());
    }
}

class PreparingOrderState implements OrderState {
    public void handleRequest(Order order) {
        System.out.println("Order is being prepared.");
        order.setState(new OutForDeliveryState());
    }
}

class OutForDeliveryState implements OrderState {
    public void handleRequest(Order order) {
        System.out.println("Order is out for delivery.");
        order.setState(new CompletedOrderState());
    }
}

class CompletedOrderState implements OrderState {
    public void handleRequest(Order order) {
        System.out.println("Order has been completed.");
    }
}
