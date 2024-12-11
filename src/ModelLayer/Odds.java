package ModelLayer;

import java.io.Serializable;
import java.util.List;

/**
 * Represents the odds associated with a specific event in a sports betting system.
 * This is an abstract class that can be extended by specific types of odds implementations.
 */
public class Odds{

    private String oddName;
    private int odd_id;
    private String eventType;



    public Odds(int odd_id, String oddName, String eventType) {
        this.odd_id = odd_id;
        this.oddName = oddName;
        this.eventType = eventType;
    }


    public String getOddName() {
        return oddName;
    }

    public void setOddName(String oddName) {
        this.oddName = oddName;
    }

    public int getOdd_id() {
        return odd_id;
    }

    public void setOdd_id(int odd_id) {
        this.odd_id = odd_id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


    @Override
    public String toString() {
        return "Odds{" +
                "oddID='" + odd_id + '\'' +
                ", oddName=" + oddName +
                ", eventType='" + eventType + '\'' +
                '}';
    }

//    public abstract String toCSV();
//
//    public static Odds fromCSV(String csvLine) {
//        String[] parts = csvLine.split(";", 2);
//        String type = parts[0];
//        switch (type) {
//            case "Football":
//                return FootballOdds.fromCSV(parts[1]);
//            case "Tennis":
//                return TennisOdds.fromCSV(parts[1]);
//            case "Basket":
//                return BasketOdds.fromCSV(parts[1]);
//            default:
//                throw new IllegalArgumentException("Unknown Odds type: " + type);
//        }
//    }
}
