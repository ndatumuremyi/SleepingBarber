/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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

   // IntegerProperty waitingPeoples = new SimpleIntegerProperty(0);
    Image emptyChair = new Image("emptyChair.png");
    Image takenChair = new Image("waitingCustomer.png");
    WaitingRoom(){
        ImageView img1 = new ImageView(emptyChair);
        img1.setFitHeight(height);
        img1.setFitWidth(width);
        ImageView img2 = new ImageView(emptyChair);
        img2.setFitHeight(height);
        img2.setFitWidth(width);
        ImageView img3 = new ImageView(emptyChair);
        img3.setFitHeight(height);
        img3.setFitWidth(width);
        ImageView img4 = new ImageView(emptyChair);
        img4.setFitHeight(height);
        img4.setFitWidth(width);

        getChildren().addAll(img1,img2,img3,img4);

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

    public boolean addNewCustomer(Customer customer){

        if(isWaitingRoomFull()){
            System.out.println("WaitingRoom: room is full");
            return false;
        }
        customers.add(customer);
        repopulate();
        if(customers.size() >= maxCustomer){
            this.status.setValue(C.WAITING_ROOM_IS_FULL);
        }
        return true;
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
        if(customers.size() == 0){
            return null;

        }
        repopulate();
        if(status.getValue() ==C.WAITING_ROOM_IS_FULL){
            status.setValue(C.WAITING_ROOM_HAS_FREE_PLACES);
        }
        return customers.poll();

    }
    
    
}
