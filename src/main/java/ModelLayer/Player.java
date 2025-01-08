package ModelLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a player in the system, inheriting from the User class.
 * A player has a balance, a list of active bets, a bonus balance, and an account status.
 */
public class Player extends User{

    /** The player's current balance. */
    private double balance;

    /** A list of the player's active bets. */
    private List<Bet> activeBets;

    private List<Bet> allBets;

    /** The player's bonus balance. */
    private int bonus_balance;

    /** The status of the player's account.*/
    private String account_status;

    /**
     * Constructs a new Player object.
     *
     * @param user_id        The unique identifier for the user.
     * @param user_name      The name of the user.
     * @param password       The password for the user's account.
     * @param email          The email address of the user.
     * @param balance        The player's current balance.
     * @param activeBets     A list of the player's active bets.
     * @param bonus_balance  The player's bonus balance.
     * @param account_status The status of the player's account.
     */
    public Player(int user_id, String user_name, String password, String email, double balance, List<Bet> activeBets, List<Bet> allBets, int bonus_balance, String account_status) {
        super(user_id, user_name, password, email);
        this.balance = balance;
        this.activeBets = activeBets;
        this.allBets = allBets;
        this.bonus_balance = bonus_balance;
        this.account_status = account_status;
    }

    public Player() {
        super(0, "", "", "");
        this.balance = 0.0;
        this.activeBets = new ArrayList<>();
        this.allBets = new ArrayList<>();
        this.bonus_balance = 0;
        this.account_status = "";
    }

    public List<Bet> getAllBets() {
        return allBets;
    }

    public void setAllBets(List<Bet> allBets) {
        this.allBets = allBets;
    }

    /**
     * Gets the player's current balance.
     *
     * @return The balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the player's current balance.
     *
     * @param balance The balance to set.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the player's bonus balance.
     *
     * @return The bonus balance.
     */
    public int getBonus_balance() {
        return bonus_balance;
    }

    /**
     * Sets the player's bonus balance.
     *
     * @param bonus_balance The bonus balance to set.
     */
    public void setBonus_balance(int bonus_balance) {
        this.bonus_balance = bonus_balance;
    }

    /**
     * Gets the status of the player's account.
     *
     * @return The account status.
     */
    public String getAccount_status() {
        return account_status;
    }

    /**
     * Sets the status of the player's account.
     *
     * @param account_status The account status to set.
     */
    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }

    /**
     * Gets the list of the player's active bets.
     *
     * @return The list of active bets.
     */
    public List<Bet> getActiveBets() {
        return activeBets;
    }

    /**
     * Sets the list of the player's active bets.
     *
     * @param activeBets The list of active bets to set.
     */
    public void setActiveBets(List<Bet> activeBets) {
        this.activeBets = activeBets;
    }

    /**
     * Returns a string representation of the Player object.
     *
     * @return A string representation of the player's details.
     */
    @Override
    public String toString() {
        return "Player{" +
                super.toString() +
                "balance=" + balance +
                ", activeBets=" + activeBets +
                ", allBets=" + allBets +
                ", bonus_balance=" + bonus_balance +
                ", account_status='" + account_status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return Double.compare(player.balance, balance) == 0 &&
                bonus_balance == player.bonus_balance &&
                Objects.equals(activeBets, player.activeBets) &&
                Objects.equals(allBets, player.allBets) &&
                Objects.equals(account_status, player.account_status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), balance, activeBets, allBets, bonus_balance, account_status);
    }
}
