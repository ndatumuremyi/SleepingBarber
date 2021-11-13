package barberTasks;

import main.Barber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarberTasks implements Runnable{
    Barber barber;

    public BarberTasks(Barber barber){
        this.barber = barber;


            barber.startSleeping();
            Thread sleepingT = new Thread(new SleepingTask(barber));
            sleepingT.setPriority(5);
            sleepingT.start();

//        barberTaskExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("BARBER_FINISH_SHAVING function");
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        barber.setStatus(C.BARBER_FINISH_SHAVING);
//                    }
//                });
//
//            }
//        });
    }
    @Override
    public void run(){

    }
}
