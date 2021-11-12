package main;

import barberTasks.FinishShavingTask;
import barberTasks.ShavingTask;
import barberTasks.SleepingTask;
import customerTasks.CustomerBeShaved;
import customerTasks.CustomerEnteringTask;
import customerTasks.CustomerLeaving;
import customerTasks.CustomerWaitingTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author paterneN
 */
public class BarberShop extends Application {
    WaitingRoom waitingRoom = new WaitingRoom();;
    ShavingPlace shavingPlace = new ShavingPlace();
    SleepingPlace sleepingPlace = new SleepingPlace();;
    VBox toolsBox = toolsBox = new VBox();
    Button addNewCustomer = new Button("Add new customer");
    HBox house = new HBox();
    Scene scene;


    @Override
    public void start(Stage primaryStage) {


        toolsBox.getChildren().add(addNewCustomer);



        house.setSpacing(200);
        house.setPadding(new Insets(80,23,80,23));
        waitingRoom.setAlignment(Pos.CENTER);
        house.getChildren().addAll(sleepingPlace,shavingPlace, waitingRoom, toolsBox);

        scene = new Scene(house);

        primaryStage.setTitle("Shaving!");
        primaryStage.setScene(scene);
        primaryStage.show();

        Main main = new Main(waitingRoom, sleepingPlace, shavingPlace, addNewCustomer);
        Thread mainThread = new Thread(main);
        mainThread.start();


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);


    }

}
