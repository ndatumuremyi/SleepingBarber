package main;

import barberTasks.BarberTasks;
import customerTasks.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Main implements Runnable {
    WaitingRoom waitingRoom;
    Barber barber;

    Button addNewCustomer;
    ShavingPlace shavingPlace;
    SleepingPlace sleepingPlace;
    BarberShop barberShop;

    @Override
    public void run() {
        barber = new Barber(waitingRoom, sleepingPlace, shavingPlace);

        BarberTasks barberTasks = new BarberTasks(barber);
        Thread barberWorks = new Thread(barberTasks);
        barberWorks.setPriority(5);
        barberWorks.start();


        addNewCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("main.BarberShop: hello, new customer need to be shaved");
                Customer customer = new Customer();
                CustomerWorks customerWorks = new CustomerWorks(customer,waitingRoom,barber);
                customerWorks.start();

                // Create a fixed thread pool with only 1 threads for each new customer



            }
        });




        this.barber.shavingRemainingTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                barberShop.updateShavingTime(barber.getShavingRemainingTime());
            }
        });
        this.barber.sleepingTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                barberShop.updateSleepingTime(barber.getSleepingTime());
            }
        });
        this.waitingRoom.peopleThatAreOutSideProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                barberShop.updatePeopleOutside(waitingRoom.getPeopleThatAreOutSide());
            }
        });



        System.out.println("start Ending");
    }
    Main(WaitingRoom waitingRoom,SleepingPlace sleepingPlace, ShavingPlace shavingPlace, Button addNewCustomer, BarberShop barberShop ){
        this.barberShop = barberShop;
        this.sleepingPlace = sleepingPlace;
        this.shavingPlace = shavingPlace;
        this.addNewCustomer = addNewCustomer;
        this.waitingRoom = waitingRoom;

    }
}
