package ModelLayer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents an event in a sports betting system.
 * Contains details such as event ID, event name, odds, event date, and sports type.
 */
public class Event{
    /**
     * Unique identifier for the event.
     */
    private int event_id;

    /**
     * Name of the event.
     */
    private String event_name;

    /**
     * List of odds for the event.
     */
    private Map<Odds,Double> oddsList;

    /**
     * Date and time of the event.
     */
    private String event_date;

    /**
     * Type of sport for the event.
     */
    private String sports_type;


    /**
     * Constructs an Event object with specified details.
     *
     * @param event_id    the unique identifier for the event
     * @param event_name  the name of the event
     * @param oddsList        a list of odds for the event
     * @param event_date  the date and time of the event
     * @param sports_type the type of sport for the event
     */
    public Event(int event_id, String event_name, Map<Odds,Double> oddsList, String event_date, String sports_type) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.oddsList = oddsList;
        this.event_date = event_date;
        this.sports_type = sports_type;
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    /**
     * Gets the unique identifier for the event.
     *
     * @return the event ID
     */
    public int getEvent_id() {
        return event_id;
    }

    /**
     * Sets the unique identifier for the event.
     *
     * @param event_id the event ID to set
     */
    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    /**
     * Gets the name of the event.
     *
     * @return the event name
     */
    public String getEvent_name() {
        return event_name;
    }

    /**
     * Sets the name of the event.
     *
     * @param event_name the event name to set
     */
    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    /**
     * Gets the date and time of the event.
     *
     * @return the event date and time
     */
    public String getEvent_date() {
        return event_date;
    }

    /**
     * Sets the date and time of the event.
     *
     * @param event_date the event date and time to set
     */
    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    /**
     * Gets the type of sport for the event.
     *
     * @return the sports type
     */
    public String getSports_type() {
        return sports_type;
    }

    /**
     * Sets the type of sport for the event.
     *
     * @param sports_type the sports type to set
     */
    public void setSports_type(String sports_type) {
        this.sports_type = sports_type;
    }

    /**
     * Gets the list of odds for the event.
     *
     * @return the list of odds
     */
    public Map<Odds,Double> getOddsList() {
        return oddsList;
    }

    /**
     * Sets the list of odds for the event.
     *
     * @param oddsList the list of odds to set
     */
    public void setOddsList(Map<Odds,Double> oddsList) {
        this.oddsList = oddsList;
    }


    @Override
    public String toString() {
        return "Event{" +
                "event_id=" + event_id +
                ", event_name='" + event_name + '\'' +
                ", oddsList=" + oddsList +
                ", event_date=" + event_date +
                ", sports_type='" + sports_type + '\'' +
                '}';
    }

    public String toCSV() {
        // Serialize oddsList as "3=2.5;4=3.0"
        String oddsString = oddsList.entrySet().stream()
                .map(entry -> entry.getKey().getOdd_id() + "=" + entry.getValue())
                .collect(Collectors.joining(";"));
        return event_id + "," + event_name + "," + oddsString + "," + event_date + "," + sports_type;
    }

    // CSV Deserialization: Parse Event from CSV
    public static Event fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        int event_id = Integer.parseInt(parts[0]);
        String event_name = parts[1];

        // Deserialize oddsList from "3=2.5;4=3.0"
        String[] oddsPairs = parts[2].split(";");
        Map<Odds, Double> oddsList = new HashMap<>();
        for (String pair : oddsPairs) {
            String[] oddsParts = pair.split("=");
            int odds_id = Integer.parseInt(oddsParts[0]);
            double oddsValue = Double.parseDouble(oddsParts[1]);
            oddsList.put(new Odds(odds_id, "", ""), oddsValue); // Simplified Odds initialization
        }

        String event_date = parts[3];
        String sports_type = parts[4];

        return new Event(event_id, event_name, oddsList, event_date, sports_type);
    }
}
