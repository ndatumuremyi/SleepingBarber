import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author paterneN
 */
public class BarberShop extends Application {

    @Override
    public void start(Stage primaryStage) {
        BarberWorks barberWorks = new BarberWorks("Sleep");

        ShavingPlace shavingPlace = new ShavingPlace();
        WaitingRoom waitingRoom = new WaitingRoom();
        SleepingPlace sleepingPlace = new SleepingPlace();

        Button btn = new Button();
        btn.setText("call customer");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                shavingPlace.setState("shaving");
                sleepingPlace.setState("shaving");
                waitingRoom.addNewCustomer();
            }
        });

        HBox house = new HBox();
        house.setSpacing(200);
        house.setPadding(new Insets(80,23,80,23));
        waitingRoom.setAlignment(Pos.CENTER);
        house.getChildren().addAll(sleepingPlace,shavingPlace, waitingRoom, btn);

        Scene scene = new Scene(house);

        primaryStage.setTitle("Shaving!");
        primaryStage.setScene(scene);
        primaryStage.show();




    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
