package ModelLayer;

import java.util.Objects;

public class BetOdd implements HasId{

    private int bet_id;
    private int odd_id;

    public BetOdd(int bet_id, int odd_id) {
        this.bet_id = bet_id;
        this.odd_id = odd_id;
    }

    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public int getOdd_id() {
        return odd_id;
    }

    public void setOdd_id(int odd_id) {
        this.odd_id = odd_id;
    }

    @Override
    public String toString() {
        return "BetOdd{" +
                "bet_id=" + bet_id +
                ", odd_id=" + odd_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BetOdd)) return false;
        BetOdd betOdd = (BetOdd) o;
        return bet_id == betOdd.bet_id &&
                odd_id == betOdd.odd_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bet_id, odd_id);
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
