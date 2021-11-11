import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author paterneN
 */
public class BarberShop extends Application {
    WaitingRoom waitingRoom;
    ShavingPlace shavingPlace;
    SleepingPlace sleepingPlace;
    Barber barber;


    @Override
    public void start(Stage primaryStage) {

        waitingRoom = new WaitingRoom();




        sleepingPlace = new SleepingPlace();
         barber = new Barber(waitingRoom);
        shavingPlace = new ShavingPlace();




        VBox toolsBox = new VBox();
        barber.run();

        Button addNewCustomer = new Button("Add new customer");
        toolsBox.getChildren().add(addNewCustomer);


        HBox house = new HBox();
        house.setSpacing(200);
        house.setPadding(new Insets(80,23,80,23));
        waitingRoom.setAlignment(Pos.CENTER);
        house.getChildren().addAll(sleepingPlace,shavingPlace, waitingRoom, toolsBox);

        Scene scene = new Scene(house);

        primaryStage.setTitle("Shaving!");
        primaryStage.setScene(scene);
        primaryStage.show();



        addNewCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("BarberShop: hello, new customer need to be shaved");
                Customer customer = new Customer();
                if(barber.getStatus() == C.BARBER_IS_SLEEPING){
                    shavingPlace.setCustomer(customer);
                    System.out.println("BarberShop:" + C.BARBER_IS_SLEEPING);
                }
                else if(waitingRoom.getStatus() == C.WAITING_ROOM_IS_FULL){
                    //full
                    System.out.println("waiting room is full");
                    return;
                }
                else {
                    waitingRoom.addNewCustomer(customer);
                }
            }
        });

        shavingPlace.status.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                switch (shavingPlace.getStatus()){
                    case C.SHAVING_PLACE_HAS_CUSTOMER: {
                        barber.setCurrentShavedCustomer(shavingPlace.getCustomer());
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        });

        barber.status.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                switch (barber.status.getValue()){
                    case C.BARBER_IS_SLEEPING:
                        System.out.println("BarberShop: SLEEPING_PLACE_HAS_BARBER");
                        sleepingPlace.setStatus(C.SLEEPING_PLACE_HAS_BARBER);
                        shavingPlace.setStatus(C.SHAVING_PLACE_IS_EMPTY);

                        break;
                    case C.BARBER_IS_SHAVING:{
                        System.out.println("BarberShop: SLEEPING_PLACE_IS_EMPTY in barber state switch");
                        sleepingPlace.setStatus(C.SLEEPING_PLACE_IS_EMPTY);
                        break;
                    }
                    case C.BARBER_FINISH_SHAVING:{
                        System.out.println("BarberShop: BARBER_FINISH_SHAVING in barber state switch");
                        //shavingPlace.setStatus(C.SHAVING_PLACE_IS_EMPTY);
                    }
                    default:
                        break;
                }
            }
        });





    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
