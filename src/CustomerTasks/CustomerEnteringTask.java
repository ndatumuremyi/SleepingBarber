package customerTasks;

import main.Barber;
import main.C;
import main.Customer;
import main.WaitingRoom;

public class CustomerEnteringTask implements Runnable {
    Customer customer;
    WaitingRoom waitingRoom;
    Barber barber;
    @Override
    public void run() {
        if(barber.getStatus() == C.BARBER_IS_SLEEPING){
            barber.setCurrentShavedCustomer(customer);
            System.out.println("CustomerEnteringTask:" );
        }
        else {
            waitingRoom.addNewCustomer(customer);
            System.out.println("CustomerEnteringTask: customer added");
        }
        Thread waitingT = new Thread(new CustomerWaitingTask(customer));
        waitingT.setPriority(5);
        waitingT.start();



    }
    public CustomerEnteringTask(Customer customer, WaitingRoom waitingRoom, Barber barber){
        this.barber = barber;
        this.customer = customer;
        this.waitingRoom = waitingRoom;
    }
}
