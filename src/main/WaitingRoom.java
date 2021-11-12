package main;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author paterneN
 */
public class WaitingRoom extends VBox{
    private int height = 120;
    private int width = 120;

    private int maxCustomer = 4;

    public LinkedBlockingQueue<Customer> customers = new LinkedBlockingQueue<>();

    public StringProperty status = new SimpleStringProperty(C.WAITING_ROOM_HAS_FREE_PLACES);
    // Create a new lock
    private static Lock lock = new ReentrantLock();

    // Create a condition
    private static Condition roomIsFullCondition = lock.newCondition();

    // IntegerProperty waitingPeoples = new SimpleIntegerProperty(0);
    Image emptyChair = new Image("emptyChair.png");
    Image takenChair = new Image("waitingCustomer.png");
    WaitingRoom(){
        int maxCustomerCopy = maxCustomer;
        while(maxCustomerCopy > 0){
            ImageView img1 = new ImageView(emptyChair);
            img1.setFitHeight(height);
            img1.setFitWidth(width);

            getChildren().add(img1);
            --maxCustomerCopy;
        }



    }

    public String getStatus() {
        return status.getValue();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.setValue(status);
    }

    public void addNewCustomer(Customer customer){

        lock.lock();
        try {
            if (status.getValue() == C.WAITING_ROOM_IS_FULL){
                System.out.println("main.WaitingRoom: room is full");
                roomIsFullCondition.await();
            }

        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            lock.unlock(); // Release the lock
        }

        customers.add(customer);
        customer.setStatus(C.CUSTOMER_IS_WAITING);
        repopulate();
        if(customers.size() >= maxCustomer){
            this.status.setValue(C.WAITING_ROOM_IS_FULL);
        }
    }

    private boolean isWaitingRoomFull() {
        if(maxCustomer <= customers.size()){
            return true;
        }
        return false;
    }

    private void repopulate() {
        Platform.runLater(() -> {
            int freeSpaces = maxCustomer - customers.size();
            int numberOfCustomer = customers.size();
            getChildren().clear();
            while (numberOfCustomer > 0){
                ImageView takenC = new ImageView(takenChair);
                takenC.setFitHeight(height);
                takenC.setFitWidth(width);
                getChildren().add(takenC);
                numberOfCustomer--;
            }
            while (freeSpaces > 0){
                ImageView emptyP = new ImageView(emptyChair);
                emptyP.setFitHeight(height);
                emptyP.setFitWidth(width);
                getChildren().add(emptyP);
                freeSpaces--;
            }
        });

    }

    //remove customer from waiting room
    public Customer getNextCustomer(){
        lock.lock();
        if(customers.size() == 0){
            return null;

        }
        repopulate();
        if(status.getValue() ==C.WAITING_ROOM_IS_FULL){
            try {
                status.setValue(C.WAITING_ROOM_HAS_FREE_PLACES);
                roomIsFullCondition.signalAll();
            } finally {
                lock.unlock(); // Release the lock
            }


        }
        return customers.poll();

    }


}
