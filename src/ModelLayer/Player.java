package ModelLayer;

import java.util.List;

public class Player extends User{

    private double balance;
    private List<Bet> activeBets;
    private int bonus_balance;
    private String account_status;

    public Player(int user_id, String user_name, String password, String email, double balance, List<Bet> activeBets,int bonus_balance, String account_status) {
        super(user_id, user_name, password, email);
        this.balance = balance;
        this.activeBets = activeBets;
        this.bonus_balance = bonus_balance;
        this.account_status = account_status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getBonus_balance() {
        return bonus_balance;
    }

    public void setBonus_balance(int bonus_balance) {
        this.bonus_balance = bonus_balance;
    }

    public String getAccount_status() {
        return account_status;
    }

    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }

    public List<Bet> getActiveBets() {
        return activeBets;
    }

    public void setActiveBets(List<Bet> activeBets) {
        this.activeBets = activeBets;
    }

    @Override
    public String toString() {
        return "Player{" +
                "balance=" + balance +
                ", activeBets=" + activeBets +
                ", bonus_balance=" + bonus_balance +
                ", account_status='" + account_status + '\'' +
                '}';
    }
}
