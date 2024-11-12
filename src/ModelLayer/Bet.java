package ModelLayer;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Bet {
    private int bet_id;
    private List<Event> event;
    private int amount;
    private LocalDateTime bet_date;

    public Bet(int bet_id, List<Event> event, int amount, LocalDateTime bet_date) {
        this.bet_id = bet_id;
        this.amount = amount;
        this.bet_date = bet_date;
        this.event = event;
    }

    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getBet_date() {
        return bet_date;
    }

    public void setBet_date(LocalDateTime bet_date) {
        this.bet_date = bet_date;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }
}
