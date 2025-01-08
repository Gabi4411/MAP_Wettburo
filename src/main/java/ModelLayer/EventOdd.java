package ModelLayer;

import java.util.Objects;

public class EventOdd implements HasId{

    private int event_id;
    private String odd_id;

    public EventOdd(int event_id, String odd_id) {
        this.event_id = event_id;
        this.odd_id = odd_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getOdd_id() {
        return odd_id;
    }

    public void setOdd_id(String odd_id) {
        this.odd_id = odd_id;
    }

    @Override
    public String toString() {
        return "EventOdd{" +
                "event_id=" + event_id +
                ", odd_id='" + odd_id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventOdd)) return false;
        EventOdd eventOdd = (EventOdd) o;
        return event_id == eventOdd.event_id &&
                Objects.equals(odd_id, eventOdd.odd_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event_id, odd_id);
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
