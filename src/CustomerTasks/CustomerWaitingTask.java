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

                if (customer.status.getValue() == C.CUSTOMER_IS_BEING_SHAVED) {
                    System.out.println("customer end waiting");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(Shaving, 0, 1, SECONDS);


        while (!scheduler.isShutdown()){

        }
    }
    public CustomerWaitingTask(Customer customer){
        this.customer = customer;

    }
}
