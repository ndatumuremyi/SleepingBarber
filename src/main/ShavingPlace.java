package main;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShavingPlace extends StackPane {
    private int height = 300;
    private int widthEmpty = 310;
    private int WidthShaving = 396;
    // Create a new lock
    private static Lock lock = new ReentrantLock();

    String status = C.SHAVING_PLACE_IS_EMPTY ;
    ImageView emptyChair;
    ImageView shaving;

    Customer customer ;

    ShavingPlace() {
        emptyChair = new ImageView(new Image("emptyChair.png"));
        emptyChair.setFitWidth(widthEmpty);
        emptyChair.setFitHeight(height);

        shaving = new ImageView(new Image("shavingBarber.png"));
        shaving.setFitWidth(WidthShaving);
        shaving.setFitHeight(height);

        getChildren().add(emptyChair);
    }
    public void setState(String state){
        status=state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        status=C.SHAVING_PLACE_HAS_CUSTOMER;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status=status;
    }

    public void removeBarberAndCustomer() {
        setStatus(C.SHAVING_PLACE_IS_EMPTY);
        Platform.runLater(() -> {
            ImageView emptyChair = new ImageView(new Image("emptyChair.png"));
            emptyChair.setFitWidth(widthEmpty);
            emptyChair.setFitHeight(height);
            getChildren().clear();
            getChildren().add(emptyChair);
        });
    }

    public void addBarberAndCustomer() {
        setStatus(C.SHAVING_PLACE_HAS_CUSTOMER);

        Platform.runLater(() -> {
            System.out.println("Shaving place: SHAVING_PLACE_HAS_CUSTOMER");
            ImageView shaving = new ImageView(new Image("shavingBarber.png"));
            shaving.setFitWidth(WidthShaving);
            shaving.setFitHeight(height);
            getChildren().clear();
            getChildren().add(shaving);
        });

    }
}
