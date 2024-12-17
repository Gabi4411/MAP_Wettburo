package ModelLayer;

public class BetOdd {

    private int bet_id;
    private int odd_id;

    public BetOdd(int bet_id, int odd_id) {
        this.bet_id = bet_id;
        this.odd_id = odd_id;
    }

    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {}

    public int getOdd_id() {
        return odd_id;
    }

    public void setOdd_id(int odd_id) {}

    @Override
    public String toString() {
        return "BetOdd{" +
                "bet_id=" + bet_id +
                ", odd_id=" + odd_id +
                '}';
    }
}
