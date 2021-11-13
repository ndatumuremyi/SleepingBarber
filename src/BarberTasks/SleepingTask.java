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

            int i = 0;
            public void run() {
                System.out.println(i);
                barber.setSleepingTime(i);
                i++;

                if (barber.getStatus() == C.BARBER_IS_SHAVING) {
                    barber.startShaving();
                    System.out.println("barber finish Sleeping task ");
                    Thread shavingT = new Thread(new ShavingTask(barber));
                    shavingT.setPriority(5);
                    shavingT.start();

                    scheduler.shutdown();
                }
            }
        };


        scheduler.scheduleAtFixedRate(Shaving, 0, 1, SECONDS);

    }
}
