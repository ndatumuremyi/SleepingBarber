package main;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class SleepingPlace extends StackPane {
    private int height = 150;
    private int width = 350;
    public String status = C.SLEEPING_PLACE_HAS_BARBER ;
    ImageView emptyChair;
    ImageView sleeping;
//    main.Barber barber;


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status=status;
    }

    SleepingPlace() {

        emptyChair = new ImageView(new Image("barberChair.png"));
        emptyChair.setFitWidth(width);
        emptyChair.setFitHeight(height);

        sleeping = new ImageView(new Image("sleepingBarber.png"));
        sleeping.setFitWidth(width);
        sleeping.setFitHeight(height);

        getChildren().add(sleeping);



    }

    public void addBarber() {
        setStatus(C.SLEEPING_PLACE_HAS_BARBER);
        Platform.runLater(() -> {
            getChildren().clear();
            ImageView sleeping = new ImageView(new Image("sleepingBarber.png"));
            sleeping.setFitWidth(width);
            sleeping.setFitHeight(height);
            getChildren().add(sleeping);
        });
    }

    public void removeBarber() {
        setStatus(C.SLEEPING_PLACE_IS_EMPTY);
        Platform.runLater(() -> {
            getChildren().clear();
            ImageView emptyChair = new ImageView(new Image("barberChair.png"));
            emptyChair.setFitWidth(width);
            emptyChair.setFitHeight(height);
            getChildren().add(emptyChair);
        });
    }
}
