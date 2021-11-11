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
        this.status.set(status);
    }

    SleepingPlace() {

        emptyChair = new ImageView(new Image("barberChair.png"));
        emptyChair.setFitWidth(width);
        emptyChair.setFitHeight(height);

        sleeping = new ImageView(new Image("sleepingBarber.png"));
        sleeping.setFitWidth(width);
        sleeping.setFitHeight(height);

        getChildren().add(sleeping);


//        this.barber.status.addListener(new InvalidationListener() {
//            @Override
//            public void invalidated(Observable observable) {
//                switch (barber.getStatus()){
//                    case C.BARBER_IS_SHAVING:
//                    {
//                        status.setValue(C.SLEEPING_PLACE_IS_EMPTY);
//                        break;
//                    }
//                    case C.BARBER_IS_SLEEPING:{
//                        status.setValue(C.SLEEPING_PLACE_HAS_BARBER);
//                        break;
//                    }
//                    default:{
//                        break;
//                    }
//                }
//            }
//        });
    }
    public void setState(String state){
        status.setValue(state);
    }

}
