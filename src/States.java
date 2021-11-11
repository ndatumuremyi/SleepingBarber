import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class States {
    public static StringProperty barberState = new SimpleStringProperty("sleeping");
    public static StringProperty waitingRoomState = new SimpleStringProperty("empty");
    public static StringProperty SleepingPlaceState = new SimpleStringProperty("empty");

    public static String getBarberState() {
        return barberState.get();
    }

    public static StringProperty barberStateProperty() {
        return barberState;
    }

    public static void setBarberState(String barberState) {
        States.barberState.set(barberState);
    }

    public static String getWaitingRoomState() {
        return waitingRoomState.get();
    }

    public static StringProperty waitingRoomStateProperty() {
        return waitingRoomState;
    }

    public static void setWaitingRoomState(String waitingRoomState) {
        States.waitingRoomState.set(waitingRoomState);
    }

    public static String getSleepingPlaceState() {
        return SleepingPlaceState.get();
    }

    public static StringProperty sleepingPlaceStateProperty() {
        return SleepingPlaceState;
    }

    public static void setSleepingPlaceState(String sleepingPlaceState) {
        SleepingPlaceState.set(sleepingPlaceState);
    }
}
