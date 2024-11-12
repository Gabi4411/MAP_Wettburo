package ModelLayer;

import java.util.List;

public class PlayerBet {
    private Player player;
    private List<Bet> bet;
    private List<String> status;


    public PlayerBet(Player player, List<Bet> bet, List<String> status) {
        this.player = player;
        this.bet = bet;
        this.status = status;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Bet> getBet() {
        return bet;
    }

    public void setBet(List<Bet> bet) {
        this.bet = bet;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PlayerBet{" +
                "player=" + player +
                ", bet=" + bet +
                ", status=" + status +
                '}';
    }
}
