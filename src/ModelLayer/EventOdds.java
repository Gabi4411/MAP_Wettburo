package ModelLayer;

public class EventOdds {
    private Event event;
    private Odds odds;
    private boolean status;

    public EventOdds(Event event, Odds odds, boolean status) {
        this.event = event;
        this.odds = odds;
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public double getOdds() {
        return odds.getOdd_value();
    }
    public void setOdds(Odds odds) {
        this.odds = odds;
    }
}
