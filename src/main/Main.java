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


//        barber.status.addListener(new InvalidationListener() {
//            @Override
//            public void invalidated(Observable observable) {
//                switch (barber.status.getValue()){
//                    case C.BARBER_IS_SLEEPING:
//
//                        System.out.println("main.BarberShop: SLEEPING_PLACE_HAS_BARBER");
//                        sleepingPlace.setStatus(C.SLEEPING_PLACE_HAS_BARBER);
//                        shavingPlace.setStatus(C.SHAVING_PLACE_IS_EMPTY);
//
//                        break;
//                    case C.BARBER_IS_SHAVING:{
//                        sleepingPlace.setStatus(C.SLEEPING_PLACE_IS_EMPTY);
//                        shavingPlace.setStatus(C.SHAVING_PLACE_HAS_CUSTOMER);
//
//                        System.out.println("main.BarberShop: SLEEPING_PLACE_IS_EMPTY in barber state switch");
//
//                        break;
//                    }
//                    default:
//                        break;
//                }
//            }
//        });





        System.out.println("start Ending");
    }
    Main(WaitingRoom waitingRoom,SleepingPlace sleepingPlace, ShavingPlace shavingPlace, Button addNewCustomer ){
        this.sleepingPlace = sleepingPlace;
        this.shavingPlace = shavingPlace;
        this.addNewCustomer = addNewCustomer;
        this.waitingRoom = waitingRoom;

    }
}
