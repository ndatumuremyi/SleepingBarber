package main;

import barberTasks.ShavingTask;
import barberTasks.SleepingTask;
import customerTasks.CustomerBeShaved;
import customerTasks.CustomerEnteringTask;
import customerTasks.CustomerLeaving;
import customerTasks.CustomerWaitingTask;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main implements Runnable {
    WaitingRoom waitingRoom;
    Barber barber;
    ExecutorService barberTaskExecutor;
    Button addNewCustomer;
    ShavingPlace shavingPlace;
    SleepingPlace sleepingPlace;

    @Override
    public void run() {
        barber = new Barber(waitingRoom);

        barberTaskExecutor = Executors.newFixedThreadPool(1);
        barberTaskExecutor.execute(new SleepingTask(barber));


        addNewCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("main.BarberShop: hello, new customer need to be shaved");
                Customer customer = new Customer();

                // Create a fixed thread pool with only 1 threads for each new customer
                ExecutorService customerTasksExecutor = Executors.newFixedThreadPool(1);
                customerTasksExecutor.execute(new CustomerEnteringTask(customer, waitingRoom, barber));
                customerTasksExecutor.execute(new CustomerWaitingTask(customer));
                customerTasksExecutor.execute(new CustomerBeShaved(customer));
                customerTasksExecutor.execute(new CustomerLeaving(customer));


            }
        });


        barber.status.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                switch (barber.status.getValue()){
                    case C.BARBER_IS_SLEEPING:
                        barberTaskExecutor.execute(new SleepingTask(barber));
                        System.out.println("main.BarberShop: SLEEPING_PLACE_HAS_BARBER");
                        sleepingPlace.setStatus(C.SLEEPING_PLACE_HAS_BARBER);
                        shavingPlace.setStatus(C.SHAVING_PLACE_IS_EMPTY);

                        break;
                    case C.BARBER_IS_SHAVING:{
                        sleepingPlace.setStatus(C.SLEEPING_PLACE_IS_EMPTY);
                        shavingPlace.setStatus(C.SHAVING_PLACE_HAS_CUSTOMER);
                        barberTaskExecutor.execute(new ShavingTask(barber));
                        barberTaskExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("BARBER_FINISH_SHAVING function");
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        barber.setStatus(C.BARBER_FINISH_SHAVING);
                                    }
                                });

                            }
                        });
                        System.out.println("main.BarberShop: SLEEPING_PLACE_IS_EMPTY in barber state switch");

                        break;
                    }
                    default:
                        break;
                }
            }
        });




        while (!barberTaskExecutor.isShutdown()){

        }
        System.out.println("start Ending");
    }
    Main(WaitingRoom waitingRoom,SleepingPlace sleepingPlace, ShavingPlace shavingPlace, Button addNewCustomer ){
        this.sleepingPlace = sleepingPlace;
        this.shavingPlace = shavingPlace;
        this.addNewCustomer = addNewCustomer;
        this.waitingRoom = waitingRoom;

    }
}
