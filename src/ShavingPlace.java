import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ShavingPlace extends StackPane {
    private int height = 300;
    private int width = 300;

    StringProperty status ;
    ImageView emptyChair;
    ImageView shaving;

    ShavingPlace() {
        emptyChair = new ImageView(new Image("emptyChair.png"));
        emptyChair.setFitWidth(width);
        emptyChair.setFitHeight(height);

        shaving = new ImageView(new Image("shavingBarber.png"));
        shaving.setFitWidth(width);
        shaving.setFitHeight(height);

        status = new SimpleStringProperty("empty");
        getChildren().add(emptyChair);
        status.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                switch (status.getValue()){
                    case "shaving":
                        getChildren().removeAll();
                        getChildren().add(shaving);
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
