package ModelLayer;

import java.util.List;
import java.util.Map;

/**
 * Represents the odds related to a tennis event.
 * This class extends the Odds class and includes additional fields specific to tennis.
 */
public class TennisOdds extends Odds {

    /** A map of players' names to their odds of serving the most aces. */
    private Map<String, Double> most_aces;

    /** A map of setcast outcomes to their respective odds. */
    private Map<String, Double> Setcast;

    /**
     * Constructs a new TennisOdds object.
     *
     * @param event_id  The unique identifier for the event.
     * @param odd_value A list of odds values associated with the event.
     * @param eventType The type of event the odds are associated with.
     * @param most_aces A map of players to their odds of serving the most aces.
     * @param setcast   A map of setcast outcomes to their odds.
     */
    public TennisOdds(int event_id, List<Double> odd_value, String eventType, Map<String, Double> most_aces, Map<String, Double> setcast) {
        super(event_id, odd_value, eventType);
        this.most_aces = most_aces;
        this.Setcast = setcast;
    }

    /**
     * Gets the map of players to their odds of serving the most aces.
     *
     * @return A map of players to their ace-serving odds.
     */
    public Map<String, Double> getMost_aces() {
        return most_aces;
    }

    /**
     * Sets the map of players to their odds of serving the most aces.
     *
     * @param most_aces The map of players to their ace-serving odds.
     */
    public void setMost_aces(Map<String, Double> most_aces) {
        this.most_aces = most_aces;
    }

    /**
     * Gets the map of setcast outcomes to their respective odds.
     *
     * @return A map of setcast outcomes to their odds.
     */
    public Map<String, Double> getSetcast() {
        return Setcast;
    }

    /**
     * Sets the map of setcast outcomes to their respective odds.
     *
     * @param setcast The map of setcast outcomes to set.
     */
    public void setSetcast(Map<String, Double> setcast) {
        this.Setcast = setcast;
    }
}
