package main;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main extends Thread{
    WaitingRoom waitingRoom;
    Barber barber;

    Button addNewCustomer;
    ShavingPlace shavingPlace;
    SleepingPlace sleepingPlace;
    BarberShop barberShop;

    private static Lock lock = new ReentrantLock();

    // Create a condition
    private static Condition requestShavingCondition = lock.newCondition();
    private static Condition finishShavingCondition = lock.newCondition();

    @Override
    public void run() {
        super.run();

        barber = new Barber(this);
        barber.start();

        CustomerGenerator customerGenerator = new CustomerGenerator(this);
        customerGenerator.start();



        addNewCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("main.BarberShop: hello, new customer need to be shaved");
                Customer customer = new Customer(Main.this);
                customer.start();

            }
        });
        barber.statusProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                switch (barber.getStatus()){
                    case C.BARBER_IS_SLEEPING:
                    {
                        sleepingPlace.addBarber();
                        shavingPlace.removeBarberAndCustomer();
                        break;
                    }
                    case C.BARBER_IS_SHAVING:
                    {
                        sleepingPlace.removeBarber();
                        shavingPlace.addBarberAndCustomer();
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
            }
        });
        this.waitingRoom.peopleThatAreOutSideProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                barberShop.updatePeopleOutside(waitingRoom.getPeopleThatAreOutSide());
            }
        });
        this.barber.shavingRemainingTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                barberShop.updateShavingTime(barber.getShavingRemainingTime());
            }
        });

    }
    Main(WaitingRoom waitingRoom,SleepingPlace sleepingPlace, ShavingPlace shavingPlace, Button addNewCustomer, BarberShop barberShop ){
        this.barberShop = barberShop;
        this.sleepingPlace = sleepingPlace;
        this.shavingPlace = shavingPlace;
        this.addNewCustomer = addNewCustomer;
        this.waitingRoom = waitingRoom;

    }

    public void requestToEnter(Customer customer) {

        System.out.println("Main requestToEnter() start");
        waitingRoom.addNewCustomer(customer);
        System.out.println("Main requestToEnter() ends");
    }

    public void requestToBeShaved() {
        lock.lock();

        try {
            Customer customer = waitingRoom.getNextCustomer();
            barber.shaveCustomer(customer);

            barber.canCustomerLeave();

            barber.readyToReceiveNewCustomer();
            System.out.println("customer leaving");


        } finally {
            lock.unlock();
        }
    }
}
