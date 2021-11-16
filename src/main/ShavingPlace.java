package main;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ShavingPlace extends StackPane {
    private int height = 300;
    private int widthEmpty = 310;
    private int WidthShaving = 396;

    String status = C.SHAVING_PLACE_IS_EMPTY ;
    ImageView emptyChair;
    ImageView shaving;


    ShavingPlace() {
        emptyChair = new ImageView(new Image("emptyChair.png"));
        emptyChair.setFitWidth(widthEmpty);
        emptyChair.setFitHeight(height);

        shaving = new ImageView(new Image("shavingBarber.png"));
        shaving.setFitWidth(WidthShaving);
        shaving.setFitHeight(height);

        getChildren().add(emptyChair);
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
