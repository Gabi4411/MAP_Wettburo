package ModelLayer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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
                "player id=" + palyer_id +
                ", bet_id=" + bet_id +
                ", event=" + event +
                ", amount=" + amount +
                ", bet_date=" + bet_date +
                ", betstatus='" + betstatus + '\'' +
                '}';
    }

    // toCSV method
    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        // Serialize events as strings in a specific format
        String eventDetails = event.stream()
                .map(e -> e.getEvent_id() + ":" + e.getEvent_name() + ":" +
                        e.getOddsList().entrySet().stream()
                                .map(odds -> odds.getKey().toCSV() + ":" + odds.getValue())
                                .collect(Collectors.joining(";")) +
                        ":" + e.getEvent_date() + ":" + e.getSports_type())
                .collect(Collectors.joining("|"));
        return palyer_id + "," + bet_id + "," + eventDetails + "," + amount + "," + bet_date.format(formatter) + "," + betstatus;
    }

    // fromCSV method
    public static Bet fromCSV(String csvLine) {
        String[] parts = csvLine.split(",", -1);
        int player_id = Integer.parseInt(parts[0]);
        int bet_id = Integer.parseInt(parts[1]);

        // Deserialize event details
        List<Event> events = Arrays.stream(parts[2].split("\\|"))
                .map(eventStr -> {
                    String[] eventParts = eventStr.split(":");
                    int event_id = Integer.parseInt(eventParts[0]);
                    String event_name = eventParts[1];
                    Map<Odds, Double> oddsList = Arrays.stream(eventParts[2].split(";"))
                            .map(oddsStr -> {
                                String[] oddsParts = oddsStr.split(",");
                                Odds odds = Odds.fromCSV(oddsParts[0]); // Assuming Odds has a fromCSV method
                                double value = Double.parseDouble(oddsParts[1]);
                                return Map.entry(odds, value);
                            })
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    String event_date = eventParts[3];
                    String sports_type = eventParts[4];
                    return new Event(event_id, event_name, oddsList, event_date, sports_type);
                })
                .toList();

        int amount = Integer.parseInt(parts[3]);
        LocalDateTime bet_date = LocalDateTime.parse(parts[4], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String betstatus = parts[5];

        return new Bet(player_id, bet_id, events, amount, bet_date, betstatus);
    }
}
