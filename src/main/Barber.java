package main;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author paterneN
 */

public class Barber extends Thread{
//    Main main;
    int shavingTime = 10;






    private StringProperty status = new SimpleStringProperty(C.BARBER_IS_SLEEPING);
    private Boolean stillShaving = false;
    private IntegerProperty shavingRemainingTime = new SimpleIntegerProperty(0);

    private Lock lock = new ReentrantLock();
    private Condition shaving = lock.newCondition();
    private Condition startShavingCondition = lock.newCondition();
    private Condition readyToReceiveCondition = lock.newCondition();


    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public Barber(){
//        this.main = main;
    }

    @Override
    public void run() {
        super.run();

        while (true){

            System.out.println("Barber top");
            lock.lock();
            try {
                startShavingCondition.await();
                setStatus(C.BARBER_IS_SHAVING);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
            stillShaving = true;
            this.shavingRemainingTime.setValue(this.shavingTime);
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            Runnable Shaving = new Runnable() {
                public void run() {
                    System.out.println(shavingRemainingTime);
                    shavingRemainingTime.setValue(shavingRemainingTime.getValue() - 1);
                    if (shavingRemainingTime.getValue() < 0) {
                        lock.lock();
                        try {

                            scheduler.shutdown();
                            stillShaving = false;
                            shaving.signalAll();
                            System.out.println("Timer Over!");
                            System.out.println("finish setting (C.BARBER_FINISH_SHAVING ");

                            setStatus(C.BARBER_IS_SLEEPING);
                        }finally {
                            lock.unlock();
                        }

                    }

                }
            };
//            this.shavingRemainingTime this.shavingTime;
            scheduler.scheduleAtFixedRate(Shaving, 0L, 1L, TimeUnit.SECONDS);
            System.out.println("Barber Bottom");


        }
    }


    public void canCustomerLeave() {
        lock.lock();
        System.out.println("Barber: canCustomerLeave() Start");

        try {
            shaving.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        System.out.println("barber: canCustomerLeave() ends");
    }
    public void shaveCustomer(Customer customer){
        lock.lock();
        if(stillShaving == true){
            try {
                readyToReceiveCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            startShavingCondition.signal();
        }finally {
            lock.unlock();
        }
    }
    public void readyToReceiveNewCustomer(){

        lock.lock();
        try {
            readyToReceiveCondition.signalAll();
            System.out.println("Function readyToReceiveNewCustomer run");


        }
        finally {
            lock.unlock();
        }
    }

    public IntegerProperty shavingRemainingTimeProperty() {
        return shavingRemainingTime;
    }

    public int getShavingRemainingTime() {
        return shavingRemainingTime.getValue();
    }
}
