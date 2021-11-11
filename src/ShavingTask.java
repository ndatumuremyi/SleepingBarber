import java.util.TimerTask;

public class ShavingTask extends TimerTask
{
    int shavingRemainingTime;
    Barber barber;
    ShavingTask(int shavingRemainingTime, Barber barber){
        this.barber = barber;
        this.shavingRemainingTime =  shavingRemainingTime;

    }
    public static int i = 0;
    public void run()
    {
        System.out.println(shavingRemainingTime);
        shavingRemainingTime--;

        if (shavingRemainingTime < 0) {
            System.out.println("Timer Over!");
            barber.status.setValue(C.BARBER_IS_SLEEPING);
        }
    }
}