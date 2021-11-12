package barberTasks;

import main.Barber;
import main.C;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;


public class SleepingTask implements Runnable {
    Barber barber;
    public SleepingTask(Barber barber) {
        this.barber = barber;
    }

    @Override
    public void run() {
        System.out.println("barber Start of Sleeping task ");
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable Shaving = new Runnable() {

            public void run() {

                if (barber.status.getValue() != C.BARBER_IS_SLEEPING) {
                    System.out.println("barber finish Sleeping task ");
                    scheduler.shutdown();
                }
            }
        };


        scheduler.scheduleAtFixedRate(Shaving, 0, 1, SECONDS);

        while (!scheduler.isShutdown()){

        }

    }
}
