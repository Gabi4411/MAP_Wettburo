package ModelLayer;

import java.util.Date;

public class Bet {
    private int bet_id;
    private Event event;
    private Player player;
    private int amount;
    private int total_odd;
    private Date bet_date;

    public Bet(int bet_id, Event event, Player player, int amount, int total_odd, Date bet_date) {
        this.bet_id = bet_id;
        this.event = event;
        this.player = player;
        this.amount = amount;
        this.total_odd = total_odd;
        this.bet_date = bet_date;
    }

    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotal_odd() {
        return total_odd;
    }

    public void setTotal_odd(int total_odd) {
        this.total_odd = total_odd;
    }

    public Date getBet_date() {
        return bet_date;
    }

    public void setBet_date(Date bet_date) {
        this.bet_date = bet_date;
    }
}
