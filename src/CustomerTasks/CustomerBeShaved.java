package customerTasks;

import main.C;
import main.Customer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CustomerBeShaved implements Runnable {
    Customer customer ;
    @Override
    public void run() {
        System.out.println("customer begin to be shaved ");
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable Shaving = new Runnable() {

            public void run() {

                if (customer.getStatus() == C.CUSTOMER_IS_LEAVING) {
                    Thread leavingT = new Thread(new CustomerLeaving(customer));
                    leavingT.setPriority(5);
                    leavingT.start();
                    System.out.println("Customer finish to be shaved");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(Shaving, 0, 1, SECONDS);

    }
    public CustomerBeShaved(Customer customer){
        this.customer = customer;
    }
}
