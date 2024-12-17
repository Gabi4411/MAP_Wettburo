package ModelLayer;

public class EventOdd {

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
}
