package Model;

import java.util.List;

/**
 * Represents the odds associated with a specific event in a sports betting system.
 * This is an abstract class that can be extended by specific types of odds implementations.
 */
public abstract class Odds {
    /**
     * Unique identifier for the event associated with the odds.
     */
    private int event_id;

    /**
     * List of odd values for the event.
     */
    private List<Double> odd_value;

    /**
     * Type of the event (e.g., sport type or event category).
     */
    private String eventType;

    /**
     * Constructs an Odds object with the specified event ID, list of odd values, and event type.
     *
     * @param event_id   the unique identifier for the event
     * @param odd_value  the list of odd values for the event
     * @param eventType  the type of event (e.g., sport type or category)
     */
    public Odds(int event_id, List<Double> odd_value, String eventType) {
        this.event_id = event_id;
        this.odd_value = odd_value;
        this.eventType = eventType;
    }

    /**
     * Gets the list of odd values for the event.
     *
     * @return the list of odd values
     */
    public List<Double> getOdd_value() {
        return odd_value;
    }

    /**
     * Sets the list of odd values for the event.
     *
     * @param odd_value the list of odd values to set
     */
    public void setOdd_value(List<Double> odd_value) {
        this.odd_value = odd_value;
    }

    /**
     * Gets the unique identifier for the event associated with the odds.
     *
     * @return the event ID
     */
    public int getEvent_id() {
        return event_id;
    }

    /**
     * Sets the unique identifier for the event associated with the odds.
     *
     * Note: There seems to be an error in the setter method for `event_id`.
     * The `event_id` parameter should be of type `int` instead of `String`.
     *
     * @param event_id the event ID to set
     */
    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    /**
     * Gets the type of event.
     *
     * @return the event type
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Sets the type of event.
     *
     * @param eventType the event type to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
