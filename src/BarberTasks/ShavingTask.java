package barberTasks;

import main.Barber;
import main.C;
import main.Customer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ShavingTask implements Runnable {
    int shavingTime = 10 ;
    int shavingRemainingTime = 0;
    Barber barber;
    @Override
    public void run() {

        shavingRemainingTime = shavingTime;
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable Shaving = new Runnable() {

            public void run() {


                System.out.println(shavingRemainingTime);
                shavingRemainingTime--;

                if (shavingRemainingTime < 0) {
                    System.out.println("Timer Over!");

                    scheduler.shutdown();
//                    barber.status.setValue(C.BARBER_FINISH_SHAVING);
                    System.out.println("finish setting (C.BARBER_FINISH_SHAVING " + barber.getStatus());
                }
            }
        };


        shavingRemainingTime = shavingTime; // reset shaving remainingTime

        scheduler.scheduleAtFixedRate(Shaving, 0, 1, SECONDS);

        while (!scheduler.isShutdown()){

        }
        System.out.println("shavingTask end it work");
    }
    public ShavingTask(Barber barber){
        this.barber = barber;
    }
}
