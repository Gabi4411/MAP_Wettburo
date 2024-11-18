package Model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a bet placed on one or more events.
 */
public class Bet {

    /** The unique identifier for the bet. */
    private int bet_id;

    /** A list of events associated with the bet. */
    private List<Event> event;

    /** The amount of money placed on the bet. */
    private int amount;

    /** The date and time when the bet was placed. */
    private LocalDateTime bet_date;

    /**
     * Constructs a new Bet object.
     *
     * @param bet_id   The unique identifier for the bet.
     * @param event    The list of events associated with the bet.
     * @param amount   The amount of money placed on the bet.
     * @param bet_date The date and time when the bet was placed.
     */
    public Bet(int bet_id, List<Event> event, int amount, LocalDateTime bet_date) {
        this.bet_id = bet_id;
        this.amount = amount;
        this.bet_date = bet_date;
        this.event = event;
    }

    /**
     * Gets the unique identifier for the bet.
     *
     * @return The bet ID.
     */
    public int getBet_id() {
        return bet_id;
    }

    /**
     * Sets the unique identifier for the bet.
     *
     * @param bet_id The bet ID to set.
     */
    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    /**
     * Gets the amount of money placed on the bet.
     *
     * @return The amount of the bet.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount of money placed on the bet.
     *
     * @param amount The amount to set.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the date and time when the bet was placed.
     *
     * @return The date and time of the bet.
     */
    public LocalDateTime getBet_date() {
        return bet_date;
    }

    /**
     * Sets the date and time when the bet was placed.
     *
     * @param bet_date The date and time to set.
     */
    public void setBet_date(LocalDateTime bet_date) {
        this.bet_date = bet_date;
    }

    /**
     * Gets the list of events associated with the bet.
     *
     * @return The list of events.
     */
    public List<Event> getEvent() {
        return event;
    }

    /**
     * Sets the list of events associated with the bet.
     *
     * @param event The list of events to set.
     */
    public void setEvent(List<Event> event) {
        this.event = event;
    }
}
