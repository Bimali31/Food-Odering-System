// OrderTracker.java
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String status);
}

class Customer implements Observer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public void update(String status) {
        System.out.println(name + " received update: " + status);
    }
}

class OrderTracker {
    private List<Observer> observers = new ArrayList<>();
    private String orderStatus;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setOrderStatus(String status) {
        this.orderStatus = status;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(orderStatus);
        }
    }
}
