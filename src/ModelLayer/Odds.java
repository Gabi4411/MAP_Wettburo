package ModelLayer;
import java.util.List;
import java.util.EnumMap;


public abstract class Odds {
    private int event_id;
    private List<Double> odd_value;
    private String eventType;

    public Odds(int odd_id, List<Double> odd_value, String eventType) {
        this.event_id = odd_id;
        this.odd_value = odd_value;
        this.eventType = eventType;
    }

    public List<Double> getOdd_value() {
        return odd_value;
    }

    public int getOdd_id() {
        return event_id;
    }

    public void setOdd_id(String event_id) {
        this.event_id = this.event_id;
    }

    public void setOdd_value(List<Double> odd_value) {
        this.odd_value = odd_value;
    }

    public String getEventType() { return eventType; }

    public void setEventType(String eventType) { this.eventType = eventType; }
}
