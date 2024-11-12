package ModelLayer;

import java.util.Date;

public class Transactions {
    private int transaction_id;
    private User user;
    private int amount;
    private Date transcation_date;
    private String transaction_type;
    private String transaction_status;

    public Transactions(int transaction_id, User user, int amount, Date transcation_date, String transaction_type, String transaction_status) {
        this.transaction_id = transaction_id;
        this.user = user;
        this.amount = amount;
        this.transcation_date = transcation_date;
        this.transaction_type = transaction_type;
        this.transaction_status = transaction_status;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTranscation_date() {
        return transcation_date;
    }

    public void setTranscation_date(Date transcation_date) {
        this.transcation_date = transcation_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }
}
