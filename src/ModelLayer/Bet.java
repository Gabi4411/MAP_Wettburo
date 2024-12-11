package ModelLayer;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a bet placed on one or more events.
 */
public class Bet{

    private int palyer_id;

    /** The unique identifier for the bet. */
    private int bet_id;

    /** A list of events associated with the bet. */
    private List<Event> event;

    /** The amount of money placed on the bet. */
    private int amount;

    /** The date and time when the bet was placed. */
    private LocalDateTime bet_date;

    private String betstatus;

    /**
     * Constructs a new Bet object.
     *
     * @param bet_id   The unique identifier for the bet.
     * @param event    The list of events associated with the bet.
     * @param amount   The amount of money placed on the bet.
     * @param bet_date The date and time when the bet was placed.
     */
    public Bet(int player_id,int bet_id, List<Event> event, int amount, LocalDateTime bet_date, String betstatus) {
        this.palyer_id = player_id;
        this.bet_id = bet_id;
        this.amount = amount;
        this.bet_date = bet_date;
        this.event = event;
        this.betstatus = betstatus;
    }

    public int getPlayer_id() {
        return palyer_id;
    }

    public void setPlayer_id(int palyer_id) {
        this.palyer_id = palyer_id;
    }

    public String getBetstatus() {
        return betstatus;
    }

    public void setBetstatus(String betstatus) {
        this.betstatus = betstatus;
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

    @Override
    public String toString() {
        return "Bet{" +
                "palyer_id=" + palyer_id +
                ", bet_id=" + bet_id +
                ", event=" + event +
                ", amount=" + amount +
                ", bet_date=" + bet_date +
                ", betstatus='" + betstatus + '\'' +
                '}';
    }


    //    public String toCSV() {
//        return String.join(";",
//                String.valueOf(bet_id),
//                String.join("|", event.stream().map(Event::toCSV).toArray(String[]::new)),
//                String.valueOf(amount),
//                bet_date.toString(),
//                betstatus
//        );
//    }
//
//    public static Bet fromCSV(String csvLine) {
//        String[] parts = csvLine.split(";", 5);
//        List<Event> events = Arrays.stream(parts[1].split("\\|"))
//                .map(Event::fromCSV)
//                .toList();
//        return new Bet(
//                Integer.parseInt(parts[0]),
//                events,
//                Integer.parseInt(parts[2]),
//                LocalDateTime.parse(parts[3]),
//                parts[4]
//        );
//    }
}
