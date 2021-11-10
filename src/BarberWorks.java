/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;


/**
 *
 * @author paterneN
 */

public class BarberWorks extends Thread{
    String status;
     int shavingTime = 30;

    public BarberWorks(String status) {
        this.status = status;
    }
    @Override
    public void run(){
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable sleeping = new Runnable() {
            @Override
            public void run() {
                System.out.println("goToSleep");
            }
        };

        final Runnable Shaving = new Runnable() {

            public void run() {

                System.out.println(shavingTime);
                shavingTime--;

                if (shavingTime < 0) {
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(Shaving, 0, 1, SECONDS);


    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public int getShavingTime() {
        return shavingTime;
    }
}
