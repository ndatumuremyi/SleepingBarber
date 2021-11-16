package main;
/**
 *
 * @author paterneN
 */
public class Customer extends Thread{
    Main main;
    public Customer(Main main){
        this.main = main;
    }
    @Override
    public void run(){
        super.run();
        System.out.println("CUSTOMER request to enter ");
        main.requestToEnter(this);
        System.out.println("CUSTOMER requestToBeShaved");
        main.requestToBeShaved();
        System.out.println("finish to be shaved");
    }
}
