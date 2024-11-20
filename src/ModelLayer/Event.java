package ModelLayer;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an event in a sports betting system.
 * Contains details such as event ID, event name, odds, event date, and sports type.
 */
public class Event {
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
}
