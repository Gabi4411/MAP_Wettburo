package ModelLayer;

import java.util.Date;
import java.util.List;

public class Event {
    public int event_id;
    public String event_name;
    public List<Odds> odds;
    public Date event_date;
    private String sports_type;

    public Event(int event_id, String event_name,List<Odds> odds, Date event_date,String sports_type) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.odds = odds;
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

    public Date getEvent_date() {
        return event_date;
    }

    public String getSports_type() {
        return sports_type;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public List<Odds> getOdds () {
        return odds;
    }
    public void setOdds(List<Odds> odds) {
        this.odds = odds;
    }

    public void setSports_type(String sports_type) {
        this.sports_type = sports_type;
    }
}
