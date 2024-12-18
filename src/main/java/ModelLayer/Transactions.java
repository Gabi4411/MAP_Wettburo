package ModelLayer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;


/**
 * Represents a transaction performed by a player, including transaction details such as
 * transaction ID, user, amount, date, type, and status.
 */
public class Transactions {
    private int transaction_id;
    private int player_id;
    private int amount;
    private LocalDateTime transcation_date;
    private String transaction_type;
    private String transaction_status;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * Constructs a Transactions object with specified details.
     *
     * @param transaction_id   the unique ID of the transaction
     * @param player_id          the player associated with the transaction
     * @param amount           the transaction amount
     * @param transcation_date the date and time of the transaction
     * @param transaction_type the type of the transaction (e.g., deposit, withdrawal)
     * @param transaction_status the status of the transaction (e.g., pending, completed)
     */

    public Transactions(int transaction_id, int player_id, int amount, LocalDateTime transcation_date, String transaction_type, String transaction_status) {
        this.transaction_id = transaction_id;
        this.player_id = player_id;
        this.amount = amount;
        this.transcation_date = transcation_date;
        this.transaction_type = transaction_type;
        this.transaction_status = transaction_status;
    }

    /**
     * Gets the transaction ID.
     *
     * @return the transaction ID
     */
    public int getTransaction_id() {
        return transaction_id;
    }

    /**
     * Sets the transaction ID.
     *
     * @param transaction_id the transaction ID to be set
     */
    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    /**
     * Gets the player associated with the transaction.
     *
     * @return the player who made the transaction
     */
    public int getPlayerID() {
        return player_id;
    }

    /**
     * Sets the player associated with the transaction.
     *
     * @param player_id the player to be set
     */

    public void setPlayerID(int player_id) {
        this.player_id = player_id;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return the transaction amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the transaction.
     *
     * @param amount the transaction amount to be set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the date and time of the transaction.
     *
     * @return the transaction date and time
     */
    public LocalDateTime getTranscation_date() {
        return transcation_date;
    }

    /**
     * Sets the date and time of the transaction.
     *
     * @param transcation_date the transaction date and time to be set
     */
    public void setTranscation_date(LocalDateTime transcation_date) {
        this.transcation_date = transcation_date;
    }

    /**
     * Gets the type of the transaction.
     *
     * @return the transaction type (e.g., deposit, withdrawal)
     */
    public String getTransaction_type() {
        return transaction_type;
    }

    /**
     * Sets the type of the transaction.
     *
     * @param transaction_type the transaction type to be set
     */
    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    /**
     * Gets the status of the transaction.
     *
     * @return the transaction status (e.g., pending, completed)
     */

    public String getTransaction_status() {
        return transaction_status;
    }

    /**
     * Sets the status of the transaction.
     *
     * @param transaction_status the transaction status to be set
     */

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }

    /**
     * Returns a string representation of the Transactions object, including all transaction details.
     *
     * @return a string representation of the Transactions object
     */
    @Override
    public String toString() {
        return "Transactions{" +
                "transaction_id=" + transaction_id +
                ", player_id=" + player_id +
                ", amount=" + amount +
                ", transcation_date=" + transcation_date +
                ", transaction_type='" + transaction_type + '\'' +
                ", transaction_status='" + transaction_status + '\'' +
                '}';
    }

    public String toCSV() {
        return transaction_id + "," +
                player_id + "," +
                amount + "," +
                transcation_date.format(DATE_FORMATTER) + "," +
                transaction_type + "," +
                transaction_status;
    }

    /**
     * Creates a Transactions object from a CSV string. Reconstructs the Player object as well.
     *
     * @param csv the CSV string representing a transaction.
     * @return a Transactions object.
     */
    public static Transactions fromCSV(String csv) {
        String[] parts = csv.split(",");

        // Parse Transactions details
        int transaction_id = Integer.parseInt(parts[0]);
        int player_id = Integer.parseInt(parts[1]);
        int amount = Integer.parseInt(parts[2]);
        LocalDateTime transcation_date = LocalDateTime.parse(parts[3], DATE_FORMATTER);
        String transaction_type = parts[4];
        String transaction_status = parts[5];

        return new Transactions(transaction_id, player_id, amount, transcation_date, transaction_type, transaction_status);
    }
}
