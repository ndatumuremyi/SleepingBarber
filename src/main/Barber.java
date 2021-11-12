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

public class Barber{
    public String status = C.BARBER_IS_SLEEPING;
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
    public boolean isThereOtherCustomer(){
        if(this.waitingRoom.getNumberOfCustomersWaiting() == 0){
            return false;
        }
        else {
            return true;
        }
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status=status;
    }

    public int getShavingTime() {
        return shavingTime;
    }

    public Customer getCurrentShavedCustomer() {
        return currentShavedCustomer;
    }

    public void setCurrentShavedCustomer(Customer currentShavedCustomer) {
        this.currentShavedCustomer = currentShavedCustomer;
        this.setStatus(C.BARBER_IS_SHAVING);

    }
    public void startSleeping(){
        setStatus(C.BARBER_IS_SLEEPING);
        shavingPlace.removeBarberAndCustomer();
        sleepingPlace.addBarber();
    }
    public void startShaving(){
        setStatus(C.BARBER_IS_SHAVING);
        currentShavedCustomer.setStatus(C.CUSTOMER_IS_BEING_SHAVED);
        shavingPlace.addBarberAndCustomer();
        sleepingPlace.removeBarber();
    }
    public void finishShaving(){
        setStatus(C.BARBER_FINISH_SHAVING);
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
