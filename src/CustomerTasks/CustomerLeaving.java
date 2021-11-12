package customerTasks;

import main.Customer;

public class CustomerLeaving implements Runnable {
    Customer customer;
    @Override
    public void run() {
        System.out.println("customer is leaving!");
    }
    public CustomerLeaving(Customer customer){
        this.customer = customer;
    }
}
