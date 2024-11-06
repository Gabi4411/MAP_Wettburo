package ModelLayer;

public class Player extends User{

    private int balance;
    private int bonus_balance;
    private String account_status;

    public Player(int user_id, String user_name, String password, String email, int balance, int bonus_balance, String account_status) {
        super(user_id, user_name, password, email);
        this.balance = balance;
        this.bonus_balance = bonus_balance;
        this.account_status = account_status;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
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
}
