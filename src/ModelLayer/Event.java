package ModelLayer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an event in a sports betting system.
 * Contains details such as event ID, event name, odds, event date, and sports type.
 */
public class Event{
    /**
     * Unique identifier for the event.
     */
    public int event_id;

    /**
     * Name of the event.
     */
    public String event_name;

    /**
     * List of odds for the event.
     */
    public List<Double> odds;

    /**
     * Date and time of the event.
     */
    public LocalDateTime event_date;

    /**
     * Type of sport for the event.
     */
    private String sports_type;

    /**
     * Constructs an Event object with specified details.
     *
     * @param event_id    the unique identifier for the event
     * @param event_name  the name of the event
     * @param odds        a list of odds for the event
     * @param event_date  the date and time of the event
     * @param sports_type the type of sport for the event
     */
    public Event(int event_id, String event_name, List<Double> odds, LocalDateTime event_date, String sports_type) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.odds = odds;
        this.event_date = event_date;
        this.sports_type = sports_type;
    }

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
    public LocalDateTime getEvent_date() {
        return event_date;
    }

    /**
     * Sets the date and time of the event.
     *
     * @param event_date the event date and time to set
     */
    public void setEvent_date(LocalDateTime event_date) {
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
    public List<Double> getOdds() {
        return odds;
    }

    /**
     * Sets the list of odds for the event.
     *
     * @param odds the list of odds to set
     */
    public void setOdds(List<Double> odds) {
        this.odds = odds;
    }


    @Override
    public String toString() {
        return "Event{" +
                "event_id=" + event_id +
                ", event_name='" + event_name + '\'' +
                ", odds=" + odds +
                ", event_date=" + event_date +
                ", sports_type='" + sports_type + '\'' +
                '}';
    }
    public String toCSV() {
        return String.join(";",
                String.valueOf(event_id),
                event_name,
                String.join(",", odds.stream().map(String::valueOf).toArray(String[]::new)),
                event_date.toString(),
                sports_type
        );
    }

    public static Event fromCSV(String csvLine) {
        try {
            // Split into parts, limiting to 6 because odds may contain commas
            String[] parts = csvLine.split(",", 6);

            if (parts.length < 6) {
                throw new IllegalArgumentException("Invalid CSV line for Event: " + csvLine);
            }

            // Parse event_id
            int eventId = Integer.parseInt(parts[0].trim());

            // Parse event_name
            String eventName = parts[2].trim();

            // Parse odds: Remove square brackets and split by commas
            String oddsString = parts[3].trim();
            oddsString = oddsString.substring(1, oddsString.length() - 1); // Remove '[' and ']'
            List<Double> odds = Arrays.stream(oddsString.split(","))
                    .map(String::trim)
                    .map(Double::parseDouble)
                    .toList();

            // Parse event_date
            LocalDateTime eventDate = LocalDateTime.parse(parts[4].trim());

            // Parse sports_type
            String sportsType = parts[5].trim();

            // Return a new Event object
            return new Event(eventId, eventName, odds, eventDate, sportsType);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing from CSV: " + csvLine, e);
        }
    }
}
