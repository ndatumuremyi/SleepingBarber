package main;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShavingPlace extends StackPane {
    private int height = 300;
    private int width = 300;
    // Create a new lock
 private static Lock lock = new ReentrantLock();

    StringProperty status = new SimpleStringProperty(C.SHAVING_PLACE_IS_EMPTY) ;
    ImageView emptyChair;
    ImageView shaving;

    Customer customer ;

    ShavingPlace() {
        emptyChair = new ImageView(new Image("emptyChair.png"));
        emptyChair.setFitWidth(width);
        emptyChair.setFitHeight(height);

        shaving = new ImageView(new Image("shavingBarber.png"));
        shaving.setFitWidth(width);
        shaving.setFitHeight(height);

        getChildren().add(emptyChair);
        status.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                switch (status.getValue()){
                    case C.SHAVING_PLACE_HAS_CUSTOMER: {
                        Platform.runLater(() -> {
                            System.out.println("Shaving place: SHAVING_PLACE_HAS_CUSTOMER");
                            ImageView shaving = new ImageView(new Image("shavingBarber.png"));
                            shaving.setFitWidth(width);
                            shaving.setFitHeight(height);
                            getChildren().clear();
                            getChildren().add(shaving);
                        });
                        System.out.println("Shaving Place "+C.SHAVING_PLACE_HAS_CUSTOMER);
                        break;
                    }
                    default:
                        //for empty and others
                        Platform.runLater(() -> {
                            ImageView emptyChair = new ImageView(new Image("emptyChair.png"));
                            emptyChair.setFitWidth(width);
                            emptyChair.setFitHeight(height);
                            getChildren().clear();
                            getChildren().add(emptyChair);
                        });
                        System.out.println("Shaving Place " + C.SHAVING_PLACE_IS_EMPTY);
                        break;
                }
            }
        });
    }
    public void setState(String state){
        status.setValue(state);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        status.setValue(C.SHAVING_PLACE_HAS_CUSTOMER);
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
}
