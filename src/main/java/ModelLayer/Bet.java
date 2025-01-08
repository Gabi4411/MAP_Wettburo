package ModelLayer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Represents a bet placed on one or more events.
 */
public class Bet implements HasId{

    private int player_id;
    private int bet_id;
    private List<Event> event;
    private int amount;
    private LocalDateTime bet_date;
    private String betstatus;

    public Bet(int bet_id, int player_id, List<Event> event, int amount, LocalDateTime bet_date, String betstatus) {
        this.bet_id = bet_id;
        this.player_id = player_id;
        this.event = event;
        this.amount = amount;
        this.bet_date = bet_date;
        this.betstatus = betstatus;
    }

    public Bet() {
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
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

    public String getBetstatus() {
        return betstatus;
    }

    public void setBetstatus(String betstatus) {
        this.betstatus = betstatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return player_id == bet.player_id &&
                bet_id == bet.bet_id &&
                amount == bet.amount &&
                Objects.equals(event, bet.event) &&
                Objects.equals(bet_date, bet.bet_date) &&
                Objects.equals(betstatus, bet.betstatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player_id, bet_id, event, amount, bet_date, betstatus);
    }

    @Override
    public String toString() {
        return "Bet{" +
                "player_id=" + player_id +
                ", bet_id=" + bet_id +
                ", event=" + event +
                ", amount=" + amount +
                ", bet_date=" + bet_date +
                ", betstatus='" + betstatus + '\'' +
                '}';
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
