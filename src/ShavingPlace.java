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
    Barber barber;

    Customer customer ;

    ShavingPlace(Barber barber) {
        this.barber = barber;
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
                    case C.SHAVING_PLACE_HAS_CUSTOMER: {
                        getChildren().removeAll();
                        getChildren().add(shaving);
                        barber.setCurrentShavedCustomer(customer);
                        System.out.println(C.SHAVING_PLACE_HAS_CUSTOMER);
                        break;
                    }
                    default:
                        //for empty and others
                        getChildren().removeAll();
                        getChildren().add(emptyChair);

                        System.out.println(C.SHAVING_PLACE_IS_EMPTY);
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
