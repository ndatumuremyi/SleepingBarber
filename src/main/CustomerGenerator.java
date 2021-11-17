package main;

import java.util.Random;

public class CustomerGenerator extends Thread{
    Main main;
    Random random = new Random();
    public  CustomerGenerator(Main main){
        this.main = main;
    }
    @Override
    public void run(){
        while (true){
            Customer customer = new Customer(main);
            customer.start();

            try {
                sleep(1000*random.nextInt(30));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
