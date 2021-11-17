package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    Button disableCustomerGeneration = new Button("Disable customer generation");
    StackPane house = new StackPane();
    Scene scene;

    Label shavingTime = new Label("shaving remaining time: 0");
    StackPane shavingT = new StackPane(shavingTime);


    Label peopleOutSite = new Label("people that will come back: 0");
    StackPane peopleOutSide =  new StackPane(peopleOutSite);

    @Override
    public void start(Stage primaryStage) {

        shavingTime.setFont(Font.font(20));
        peopleOutSite.setFont(Font.font(20));
        toolsBox.getChildren().addAll(disableCustomerGeneration,addNewCustomer, shavingT, peopleOutSide);
        toolsBox.setSpacing(9);
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

        Main main = new Main(waitingRoom, sleepingPlace, shavingPlace, addNewCustomer, disableCustomerGeneration, this);
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
                if(newValue < 0){
                    return;
                }
                Label shavingTime = new Label("shaving remaining time: " + newValue);

                shavingTime.setFont(Font.font(20));
                shavingT.getChildren().clear();
                shavingT.getChildren().add(shavingTime);
            }
        });

    }
    public void updatePeopleOutside(int newValue){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(newValue < 0){
                    return;
                }
                Label peopleOutSite = new Label("people that will come back: "+newValue);
                peopleOutSite.setFont(Font.font(20));
                peopleOutSide.getChildren().clear();
                peopleOutSide.getChildren().add(peopleOutSite);
            }
        });
    }

    public void ChangeTextForDisableCustomerGenetation(Boolean disabled) {
        if(!disabled){
            disableCustomerGeneration.setText("Disable customer generation");
        }
        else {
            disableCustomerGeneration.setText("Enable customer generation");
        }
    }
}
