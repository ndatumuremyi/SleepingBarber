import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CustomerGenerator extends Thread{
    int timeForNewCustomer = 9;

    @Override
    public void run(){
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {

            public void run() {

                System.out.println(timeForNewCustomer);
                timeForNewCustomer--;

                if (timeForNewCustomer < 0) {
                    Customer customer = new Customer();
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
}
