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

    StringProperty testingChange = new SimpleStringProperty("this");

    @Override
    public void start(Stage primaryStage) {

        waitingRoom = new WaitingRoom();




        sleepingPlace = new SleepingPlace();
         barber = new Barber(waitingRoom);
        shavingPlace = new ShavingPlace(barber);




        VBox toolsBox = new VBox();
        barber.run();

        Button addNewCustomer = new Button("Add new customer");
        addNewCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hello");
                Customer customer = new Customer();
                if(barber.getStatus() == C.BARBER_IS_SLEEPING){
                    shavingPlace.setCustomer(customer);
                    System.out.println(C.BARBER_IS_SLEEPING);
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

        shavingPlace.status.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                switch (shavingPlace.getStatus()){
                    case C.SHAVING_PLACE_HAS_CUSTOMER: {
                        barber.setStatus(C.BARBER_IS_SHAVING);
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
                        sleepingPlace.setState(C.SLEEPING_PLACE_HAS_BARBER);
//                        Platform.runLater(() -> {
//                                toolsBox.getChildren().add(new Button("this is it "));
//                        });

                        break;
                    case C.BARBER_IS_SHAVING:{
                        sleepingPlace.setStatus(C.SLEEPING_PLACE_IS_EMPTY);
                        break;
                    }
                    default:
                        break;
                }
            }
        });
        sleepingPlace.status.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                switch (sleepingPlace.status.getValue()){
                    case C.SLEEPING_PLACE_HAS_BARBER:
                        testingChange.setValue("testing");
//                        ImageView sleeping = new ImageView(new Image("sleepingBarber.png"));
//                        sleeping.setFitWidth(140);
//                        sleeping.setFitHeight(150);
//
//                        sleepingPlace.getChildren().removeAll();
//                        sleepingPlace.getChildren().add(sleeping);
                        break;
                    default:
//                        testingChange.setValue("testing");
                        //for empty and others
//                        ImageView emptyChair = new ImageView(new Image("barberChair.png"));
//                        emptyChair.setFitWidth(140);
//                        emptyChair.setFitHeight(250);
//                        sleepingPlace.getChildren().removeAll();
//                        sleepingPlace.getChildren().add(emptyChair);
                        break;
                }
            }
        });

        testingChange.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(testingChange.getValue() == "testing"){
                    ImageView sleeping = new ImageView(new Image("sleepingBarber.png"));
                    sleeping.setFitWidth(140);
                    sleeping.setFitHeight(150);

                    sleepingPlace.getChildren().removeAll();
                    sleepingPlace.getChildren().add(sleeping);
                    System.out.println("testng works");
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
