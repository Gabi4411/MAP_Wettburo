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

    public String toCSV() {
        return odd_id + "," + oddName + "," + eventType;
    }

    /**
     * Creates an Odds object from a CSV string.
     *
     * @param csvLine the CSV string containing odds data
     * @return the Odds object
     */
    public static Odds fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        int odd_id = Integer.parseInt(parts[0]);
        String oddName = parts[1];
        String eventType = parts[2];
        return new Odds(odd_id, oddName, eventType);
    }
}
