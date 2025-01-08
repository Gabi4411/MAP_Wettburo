package ModelLayer;

import java.util.Objects;

public class BetEvent implements HasId{

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

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    @Override
    public String toString() {
        return "BetEvent{" +
                "bet_id=" + bet_id +
                ", event_id=" + event_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetEvent betEvent = (BetEvent) o;
        return bet_id == betEvent.bet_id &&
                event_id == betEvent.event_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bet_id, event_id);
    }

    @Override
    public int getId() {
        return bet_id;
    }

    @Override
    public void setId(int id) {
        this.bet_id = id;
    }
}
