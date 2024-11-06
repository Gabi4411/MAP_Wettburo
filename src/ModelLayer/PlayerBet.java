package ModelLayer;

public class PlayerBet {
    private Player player;
    private Bet bet;
    private int total_bets;

    public PlayerBet(Player player, Bet bet, int total_bets) {
        this.player = player;
        this.bet = bet;
        this.total_bets = total_bets;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public int getTotal_bets() {
        return total_bets;
    }

    public void setTotal_bets(int total_bets) {
        this.total_bets = total_bets;
    }
}
