package customerTasks;

import main.C;
import main.Customer;
import main.ShavingPlace;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CustomerWaitingTask implements Runnable {
    Customer customer;
    @Override
    public void run() {
        System.out.println("customer begin waiting ");
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable Shaving = new Runnable() {

            public void run() {

                if (customer.getStatus() == C.CUSTOMER_IS_BEING_SHAVED) {
                    Thread beShavedT = new Thread(new CustomerBeShaved(customer));
                    beShavedT.setPriority(5);
                    beShavedT.start();
                    System.out.println("customer end waiting");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(Shaving, 0, 1, SECONDS);
    }
    public CustomerWaitingTask(Customer customer){
        this.customer = customer;

    }
}
