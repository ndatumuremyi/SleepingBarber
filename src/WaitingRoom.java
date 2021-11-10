/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author paterneN
 */
public class WaitingRoom extends VBox{
    private int height = 120;
    private int width = 120;

    private int full = 4;

    IntegerProperty waitingPeoples = new SimpleIntegerProperty(0);
    Image emptyChair = new Image("emptyChair.png");
    Image takenChair = new Image("waitingCustomer.png");
    WaitingRoom(){
        ImageView img1 = new ImageView(emptyChair);
        img1.setFitHeight(height);
        img1.setFitWidth(width);
        ImageView img2 = new ImageView(emptyChair);
        img2.setFitHeight(height);
        img2.setFitWidth(width);
        ImageView img3 = new ImageView(emptyChair);
        img3.setFitHeight(height);
        img3.setFitWidth(width);
        ImageView img4 = new ImageView(emptyChair);
        img4.setFitHeight(height);
        img4.setFitWidth(width);

        getChildren().addAll(img1,img2,img3,img4);

    }
    public boolean addNewCustomer(){
//        if(getChildren().size() == full){
//            return false;
//        }
        ImageView newCustomer = new ImageView(takenChair);
        newCustomer.setFitHeight(height);
        newCustomer.setFitWidth(width);
        getChildren().add(newCustomer);
        waitingPeoples.setValue(waitingPeoples.getValue() + 1);
        return true;
    }
    //remove customer from waiting room
    public boolean getNextCustomer(){
        if(getChildren().size() <= 3){
            return false;

        }
        getChildren().remove((getChildren().size()-1));
        waitingPeoples.setValue(waitingPeoples.getValue()-1);
        return true;

    }
    
    
}
