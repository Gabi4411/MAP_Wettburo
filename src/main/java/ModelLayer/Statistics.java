package ModelLayer;

import java.util.Objects;

/**
 * Represents statistics for an event, including team statistics, head-to-head data, and recent form.
 */
public class Statistics implements HasId{
    private int stat_id;
    private int event_id;
    private String eventDescription;
    private String eventPrediction;

    public Statistics(int stat_id, int event_id, String eventDescription, String eventPrediction) {
        this.stat_id = stat_id;
        this.event_id = event_id;
        this.eventDescription = eventDescription;
        this.eventPrediction = eventPrediction;
    }

    public int getStat_id() {
        return stat_id;
    }

    public void setStat_id(int stat_id) {
        this.stat_id = stat_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventPrediction() {
        return eventPrediction;
    }

    public void setEventPrediction(String eventPrediction) {
        this.eventPrediction = eventPrediction;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "stat_id=" + stat_id +
                ", event_id=" + event_id +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventPrediction='" + eventPrediction + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Statistics)) return false;
        Statistics that = (Statistics) o;
        return stat_id == that.stat_id &&
                event_id == that.event_id &&
                Objects.equals(eventDescription, that.eventDescription) &&
                Objects.equals(eventPrediction, that.eventPrediction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stat_id, event_id, eventDescription, eventPrediction);
    }

    @Override
    public int getId() {
        return stat_id;
    }

    @Override
    public void setId(int id) {
        this.stat_id = id;
    }
}
