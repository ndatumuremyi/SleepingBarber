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
    public StringProperty status = new SimpleStringProperty(C.SLEEPING_PLACE_HAS_BARBER); ;
    ImageView emptyChair;
    ImageView sleeping;
//    Barber barber;


    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.setValue(status);
    }

    SleepingPlace() {

        emptyChair = new ImageView(new Image("barberChair.png"));
        emptyChair.setFitWidth(width);
        emptyChair.setFitHeight(height);

        sleeping = new ImageView(new Image("sleepingBarber.png"));
        sleeping.setFitWidth(width);
        sleeping.setFitHeight(height);

        getChildren().add(sleeping);



        this.status.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                switch (status.getValue()){
                    case C.SLEEPING_PLACE_HAS_BARBER: {
                        Platform.runLater(() -> {
                            getChildren().clear();
                            ImageView sleeping = new ImageView(new Image("sleepingBarber.png"));
                            sleeping.setFitWidth(width);
                            sleeping.setFitHeight(height);
                            getChildren().add(sleeping);
                        });
                        break;
                    }
                    default: {
                        Platform.runLater(() -> {
                            Platform.runLater(() -> {
                                getChildren().clear();
                                ImageView emptyChair = new ImageView(new Image("barberChair.png"));
                                emptyChair.setFitWidth(width);
                                emptyChair.setFitHeight(height);
                                getChildren().add(emptyChair);
                            });

                        });

                        break;
                    }
                }
            }
        });
    }
    public void setState(String state){
        status.setValue(state);
    }

}
