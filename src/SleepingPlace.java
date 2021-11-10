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
    StringProperty status ;
    ImageView emptyChair;
    ImageView sleeping;

    SleepingPlace() {
        emptyChair = new ImageView(new Image("barberChair.png"));
        emptyChair.setFitWidth(width);
        emptyChair.setFitHeight(height);

        sleeping = new ImageView(new Image("sleepingBarber.png"));
        sleeping.setFitWidth(width);
        sleeping.setFitHeight(height);

        status = new SimpleStringProperty("sleeping");
        getChildren().add(sleeping);
        status.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                switch (status.getValue()){
                    case "sleeping":
                        getChildren().removeAll();
                        getChildren().add(sleeping);
                        break;
                    default:
                        //for empty and others
                        getChildren().removeAll();
                        getChildren().add(emptyChair);
                        break;
                }
            }
        });
    }
    public void setState(String state){
        status.setValue(state);
    }

}
