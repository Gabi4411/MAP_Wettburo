package ModelLayer;

import java.util.Date;
import java.util.List;

public class Bet {
    private int bet_id;

    private List<EventOdds> event_odds;
    private Player player;
    private int amount;
    private double total_odd;
    private Date bet_date;

    public Bet(int bet_id, List<EventOdds> event_odds, Player player, int amount, double total_odd, Date bet_date) {
        this.bet_id = bet_id;
        this.event_odds = event_odds;
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

    public List<EventOdds> getEvent_odds() {
        return event_odds;
    }

    public void setEvent_odds(List<EventOdds> event_odds) {
        this.event_odds = event_odds;
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

    public double getTotal_odd() {
        return total_odd;
    }

    public void setTotal_odd(double total_odd) {
        this.total_odd = total_odd;
    }

    public Date getBet_date() {
        return bet_date;
    }

    public void setBet_date(Date bet_date) {
        this.bet_date = bet_date;
    }
}
