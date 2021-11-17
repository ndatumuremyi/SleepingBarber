package main;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CustomerGenerator extends Thread{
    Main main;
    Random random = new Random();
    boolean disabled = false;

    Lock lock = new ReentrantLock();
    Condition disableCondition = lock.newCondition();
    public  CustomerGenerator(Main main){
        this.main = main;
    }
    @Override
    public void run(){
        while (true){
            if(disabled){
                lock.lock();
                try {
                    disableCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }


            try {
                sleep(1000*random.nextInt(15));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Auto generate customer generate new customer");
            Customer customer = new Customer(main);
            customer.start();


        }
    }

    public void takeBreak() {
        this.disabled = true;
    }

    public void goToWork() {
        lock.lock();
        try {
            disableCondition.signalAll();
        }finally {
            lock.unlock();
        }
        this.disabled = false;
    }
}
