package main;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


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
    ShavingPlace shavingPlace;
    SleepingPlace sleepingPlace;



    public Barber(WaitingRoom waitingRoom, SleepingPlace sleepingPlace, ShavingPlace shavingPlace){
        this.waitingRoom = waitingRoom;
        this.shavingPlace = shavingPlace;
        this.sleepingPlace = sleepingPlace;

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
                        currentShavedCustomer.setStatus(C.CUSTOMER_IS_BEING_SHAVED);

//                        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//                        final Runnable Shaving = new Runnable() {
//
//                            public void run() {
//
//                                System.out.println(shavingRemainingTime);
//                                shavingRemainingTime--;
//
//                                if (shavingRemainingTime < 0) {
//                                    System.out.println("Timer Over!");
//                                    status.setValue(C.BARBER_FINISH_SHAVING);
//                                    scheduler.shutdown();
//                                }
//                            }
//                        };
//
//
//                        shavingRemainingTime = shavingTime; // reset shaving remainingTime
//
//                        scheduler.scheduleAtFixedRate(Shaving, 0, 1, SECONDS);
                        break;
                    }
                    case C.BARBER_FINISH_SHAVING: {

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
        this.currentShavedCustomer = currentShavedCustomer;
        this.status.setValue(C.BARBER_IS_SHAVING);

    }
    public void startSleeping(){
        setStatus(C.BARBER_IS_SLEEPING);
        shavingPlace.removeBarberAndCustomer();
        sleepingPlace.addBarber();
    }
    public void startShaving(){
        shavingPlace.addBarberAndCustomer();
        sleepingPlace.removeBarber();
    }
    public void finishShaving(){
        if(currentShavedCustomer != null){
            currentShavedCustomer.startLeaving();
        }



        //take new customer

        System.out.println("testing" + currentShavedCustomer);
        currentShavedCustomer = waitingRoom.getNextCustomer();
        System.out.println("testing" + currentShavedCustomer);
        if (currentShavedCustomer== null) {
            startSleeping();
            System.out.println("Barber: barber got to sleep");

        } else {
            currentShavedCustomer.startBeingShaved();
            startShaving();
        }
    }
}
