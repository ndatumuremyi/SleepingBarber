package customerTasks;

import main.Barber;
import main.Customer;
import main.WaitingRoom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerWorks extends Thread{
    Customer customer;
    WaitingRoom waitingRoom;
    Barber barber;
    public CustomerWorks(Customer customer, WaitingRoom waitingRoom, Barber barber){
        this.customer = customer;
        this.waitingRoom =waitingRoom;
        this.barber = barber;
    }
    @Override
    public void run(){
//

        Thread enteringT = new Thread(new CustomerEnteringTask(customer, waitingRoom, barber));
        enteringT.setPriority(5);
        enteringT.start();



    }
}