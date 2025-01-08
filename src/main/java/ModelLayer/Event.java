package ModelLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents an event in a sports betting system.
 * Contains details such as event ID, event name, odds, event date, and sports type.
 */
public class Event implements HasId{

    private int event_id;
    private String event_name;
    private Map<Odds, Double> oddsList;
    private String event_date;
    private String sports_type;

    /**
     * Constructs an Event object with specified details.
     *
     * @param event_id    the unique identifier for the event
     * @param event_name  the name of the event
     * @param oddsList    a list of odds for the event
     * @param event_date  the date and time of the event
     * @param sports_type the type of sport for the event
     */
    public Event(int event_id, String event_name, Map<Odds, Double> oddsList, String event_date, String sports_type) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.oddsList = oddsList;
        this.event_date = event_date;
        this.sports_type = sports_type;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Map<Odds, Double> getOddsList() {
        return oddsList;
    }

    public void setOddsList(Map<Odds, Double> oddsList) {
        this.oddsList = oddsList;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getSports_type() {
        return sports_type;
    }

    public void setSports_type(String sports_type) {
        this.sports_type = sports_type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return event_id == event.event_id &&
                Objects.equals(event_name, event.event_name) &&
                Objects.equals(oddsList, event.oddsList) &&
                Objects.equals(event_date, event.event_date) &&
                Objects.equals(sports_type, event.sports_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event_id, event_name, oddsList, event_date, sports_type);
    }

    @Override
    public int getId() {
        return event_id;
    }

    @Override
    public void setId(int id) {
        this.event_id = id;
    }
}
