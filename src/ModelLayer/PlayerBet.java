package ModelLayer;

import java.util.List;

/**
 * Represents a player's bet in a sports betting system.
 * Contains details about the player, the bets they have placed, and the status of each bet.
 */
public class PlayerBet {
    /**
     * The player who placed the bets.
     */
    private Player player;

    /**
     * A list of bets placed by the player.
     */
    private List<Bet> bet;

    /**
     * A list of statuses for each bet, indicating the outcome or progress of the bets.
     */
    private List<String> status;

    /**
     * Constructs a PlayerBet object with the specified player, list of bets, and their statuses.
     *
     * @param player the player who placed the bets
     * @param bet    the list of bets placed by the player
     * @param status the list of statuses for each bet
     */
    public PlayerBet(Player player, List<Bet> bet, List<String> status) {
        this.player = player;
        this.bet = bet;
        this.status = status;
    }

    /**
     * Gets the player who placed the bets.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player who placed the bets.
     *
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the list of bets placed by the player.
     *
     * @return the list of bets
     */
    public List<Bet> getBet() {
        return bet;
    }

    /**
     * Sets the list of bets placed by the player.
     *
     * @param bet the list of bets to set
     */
    public void setBet(List<Bet> bet) {
        this.bet = bet;
    }

    /**
     * Gets the list of statuses for each bet.
     *
     * @return the list of statuses
     */
    public List<String> getStatus() {
        return status;
    }

    /**
     * Sets the list of statuses for each bet.
     *
     * @param status the list of statuses to set
     */
    public void setStatus(List<String> status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the PlayerBet object.
     *
     * @return a string containing the player, bets, and their statuses
     */
    @Override
    public String toString() {
        return "PlayerBet{" +
                "player=" + player +
                ", bet=" + bet +
                ", status=" + status +
                '}';
    }
}
