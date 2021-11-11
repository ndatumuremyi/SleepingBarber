/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author paterneN
 */
public class Customer extends  Thread{
    public StringProperty status = new SimpleStringProperty(C.CUSTOMER_IS_ENTERING);


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
