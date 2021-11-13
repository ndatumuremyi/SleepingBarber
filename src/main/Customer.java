package main;/*
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
public class Customer{
    private static int idGenerator = 0;
    private int id = 0;
    public Customer(){
        id = ++idGenerator;
    }

    public int getId() {
        return id;
    }
    public String status = C.CUSTOMER_IS_ENTERING;


    public String getStatus() {
        return status;
    }



    public void setStatus(String status) {
        this.status=status;
    }

    public void startLeaving() {
        setStatus(C.CUSTOMER_IS_LEAVING);
    }

    public void startBeingShaved() {
        setStatus(C.CUSTOMER_IS_BEING_SHAVED);
    }
}
