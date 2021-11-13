package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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
    StackPane house = new StackPane();
    Scene scene;


    @Override
    public void start(Stage primaryStage) {


        toolsBox.getChildren().add(addNewCustomer);
        sleepingPlace.setAlignment(Pos.BOTTOM_LEFT);
        shavingPlace.setAlignment(Pos.BOTTOM_LEFT);


        int width = 1500;
        int height = 675;
        ImageView back = new ImageView(new Image("barberHouse.png"));
        back.setFitWidth(width);
        back.setFitHeight(height);
        house.getChildren().add(back);
//        house.setMinSize(width,height);
//        house.setMaxSize(width,height);

        sleepingPlace.setAlignment(Pos.BOTTOM_LEFT);
        shavingPlace.setAlignment(Pos.BOTTOM_CENTER);
        waitingRoom.setAlignment(Pos.BOTTOM_RIGHT);
        toolsBox.setAlignment(Pos.BASELINE_RIGHT);
        house.getChildren().addAll(sleepingPlace,shavingPlace,waitingRoom, toolsBox);
//        whole.setBackground(new Background(new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(1500,height,false,false,true,true))));

//        whole.setBackground(new Background(new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));

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
