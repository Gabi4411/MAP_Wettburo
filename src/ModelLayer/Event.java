package ModelLayer;

import java.util.Date;

public class Event {
    public int event_id;
    public String event_name;
    public Date event_date;

    public Event(int event_id, String event_name, Date event_date) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_date = event_date;
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

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }
}
