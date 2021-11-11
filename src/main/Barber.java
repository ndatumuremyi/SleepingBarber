package main;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;


/**
 *
 * @author paterneN
 */

public class Barber extends Thread{
    public StringProperty status = new SimpleStringProperty(C.BARBER_IS_SLEEPING);
     int shavingTime = 10;
     int shavingRemainingTime = 0;
     WaitingRoom waitingRoom; // will help us to get next customer.
     Customer currentShavedCustomer;

    public Barber(WaitingRoom waitingRoom){
        this.waitingRoom = waitingRoom;
    }
    @Override
    public void run(){


        this.status.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                switch (status.getValue()){
                    case C.BARBER_IS_SLEEPING:
                        System.out.println("main.Barber" + C.BARBER_IS_SLEEPING);
                        break;

                    case C.BARBER_IS_SHAVING: {
                        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                        final Runnable Shaving = new Runnable() {

                            public void run() {

                                System.out.println(shavingRemainingTime);
                                shavingRemainingTime--;

                                if (shavingRemainingTime < 0) {
                                    System.out.println("Timer Over!");
                                    status.setValue(C.BARBER_FINISH_SHAVING);
                                    scheduler.shutdown();
                                }
                            }
                        };


                        shavingRemainingTime = shavingTime; // reset shaving remainingTime

                        scheduler.scheduleAtFixedRate(Shaving, 0, 1, SECONDS);
                        break;
                    }
                    case C.BARBER_FINISH_SHAVING: {
                        if(currentShavedCustomer == null)
                        {
                            System.out.println("main.Barber currentSavedCustomer is empty" );

                        }
                        else {
                            System.out.println("current user status " + currentShavedCustomer.getStatus());
                            currentShavedCustomer.setStatus(C.CUSTOMER_IS_LEAVING);
                        }


                        //take new customer
                        currentShavedCustomer = waitingRoom.getNextCustomer();
                        if (currentShavedCustomer== null) {
                            status.setValue(C.BARBER_IS_SLEEPING);

                        } else {
                            currentShavedCustomer.setStatus(C.CUSTOMER_IS_BEING_SHAVED);
                            status.setValue(C.BARBER_IS_SHAVING);
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        });




    }

    public String getStatus() {
        return status.getValue();
    }


    public void setStatus(String status) {
        this.status.setValue(status);
    }

    public int getShavingTime() {
        return shavingTime;
    }

    public Customer getCurrentShavedCustomer() {
        return currentShavedCustomer;
    }

    public void setCurrentShavedCustomer(Customer currentShavedCustomer) {
        this.setStatus(C.BARBER_IS_SHAVING);
        this.currentShavedCustomer = currentShavedCustomer;
    }
}
