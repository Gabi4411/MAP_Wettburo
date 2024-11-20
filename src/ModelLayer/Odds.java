package ModelLayer;

import java.util.List;

/**
 * Represents the odds associated with a specific event in a sports betting system.
 * This is an abstract class that can be extended by specific types of odds implementations.
 */
public abstract class Odds {
    /**
     * List of odd values for the event.
     */
    private List<Double> oddValue;

    /**
     * Type of the event (e.g., sport type or event category).
     */
    private String eventType;

    /**
     * Constructs an Odds object with the specified event ID, list of odd values, and event type.
     *
     * @param oddValue  the list of odd values for the event
     * @param eventType  the type of event (e.g., sport type or category)
     */
    public Odds(List<Double> oddValue, String eventType) {
        this.oddValue = oddValue;
        this.eventType = eventType;
    }

    /**
     * Gets the list of odd values for the event.
     *
     * @return the list of odd values
     */
    public List<Double> getOdd_value() {
        return oddValue;
    }

    /**
     * Sets the list of odd values for the event.
     *
     * @param oddValue the list of odd values to set
     */
    public void setOdd_value(List<Double> oddValue) {
        this.oddValue = oddValue;
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

    @Override
    public String toString() {
        return "Odds{" +
                "oddValue=" + oddValue +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
