package barberTasks;

import main.Barber;
import main.C;

public class FinishShavingTask implements Runnable {
    Barber barber;
    @Override
    public void run() {
        barber.finishShaving();
        if(barber.isThereOtherCustomer()){
//            barber.startShaving();
            System.out.println("barber finish Sleeping task ");
            Thread shavingT = new Thread(new ShavingTask(barber));
            shavingT.setPriority(5);
            shavingT.start();
        }
        else {
//            barber.startSleeping();
            Thread sleepingT = new Thread(new SleepingTask(barber));
            sleepingT.setPriority(5);
            sleepingT.start();
        }

    }
    public FinishShavingTask(Barber barber){
        this.barber = barber;
    }
}
