package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author paterneN
 */
public class BarberShop extends Application {
    WaitingRoom waitingRoom = new WaitingRoom();;
    ShavingPlace shavingPlace = new ShavingPlace();
    SleepingPlace sleepingPlace = new SleepingPlace();;
    VBox toolsBox  = new VBox();
    Button addNewCustomer = new Button("Add new customer");
    StackPane house = new StackPane();
    Scene scene;

    Label shavingTime = new Label("shaving remaining time: 0");
    StackPane shavingT = new StackPane(shavingTime);

    Label sleepingTime = new Label("sleeping time: 0");
    StackPane sleepingT = new StackPane(sleepingTime);

    Label peopleOutSite = new Label("people that will come back: 0");
    StackPane peopleOutSide =  new StackPane(peopleOutSite);

    @Override
    public void start(Stage primaryStage) {


        sleepingTime.setFont(Font.font(20));
        shavingTime.setFont(Font.font(20));
        peopleOutSite.setFont(Font.font(20));
        toolsBox.getChildren().addAll(addNewCustomer, sleepingT, shavingT, peopleOutSide);
        sleepingPlace.setAlignment(Pos.BOTTOM_LEFT);
        shavingPlace.setAlignment(Pos.BOTTOM_LEFT);


        int width = 1500;
        int height = 675;
        ImageView back = new ImageView(new Image("barberHouse.png"));
        back.setFitWidth(width);
        back.setFitHeight(height);
        house.getChildren().add(back);

        sleepingPlace.setAlignment(Pos.BOTTOM_LEFT);

        shavingPlace.setAlignment(Pos.BOTTOM_CENTER);
        shavingPlace.setPadding(new Insets(0,0,20,0));

        waitingRoom.setAlignment(Pos.BOTTOM_RIGHT);

        toolsBox.setAlignment(Pos.BASELINE_RIGHT);
        house.getChildren().addAll(sleepingPlace,shavingPlace,waitingRoom, toolsBox);

        scene = new Scene(house);

        primaryStage.setTitle("Shaving!");
        primaryStage.setScene(scene);
        primaryStage.show();

        Main main = new Main(waitingRoom, sleepingPlace, shavingPlace, addNewCustomer, this);
        Thread mainThread = new Thread(main);
        mainThread.start();




    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);


    }
    public void updateShavingTime(int newValue){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label shavingTime = new Label("shaving remaining time: " + newValue);

                shavingTime.setFont(Font.font(20));
                shavingT.getChildren().clear();
                shavingT.getChildren().add(shavingTime);
            }
        });

    }
    public void updateSleepingTime(int newValue){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                sleepingT.getChildren().clear();
                Label sleepingTime = new Label("sleeping time: "+ newValue);
                sleepingTime.setFont(Font.font(20));
                sleepingT.getChildren().add(sleepingTime);

            }
        });
    }
    public void updatePeopleOutside(int newValue){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Label peopleOutSite = new Label("people that will come back: "+newValue);
                peopleOutSite.setFont(Font.font(20));
                peopleOutSide.getChildren().clear();
                peopleOutSide.getChildren().add(peopleOutSite);
            }
        });
    }

}
