package ModelLayer;

public class BetEvent {

    private int bet_id;
    private int event_id;

    public BetEvent(int bet_id, int event_id) {
        this.bet_id = bet_id;
        this.event_id = event_id;
    }

    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {}

    @Override
    public String toString() {
        return "BetEvent{" +
                "bet_id=" + bet_id +
                ", event_id=" + event_id +
                '}';
    }
}
