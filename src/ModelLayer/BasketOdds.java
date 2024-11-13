package ModelLayer;

import java.util.List;
import java.util.Map;

/**
 * Represents the odds related to a basketball event.
 * This class extends the Odds class and includes additional fields specific to basketball.
 */
public class BasketOdds extends Odds {

    /** The odds for a draw in the game. */
    private int odd_draw;

    /** A map of players' names to their odds of making the most 3-point shots. */
    private Map<String, Double> most_3p;

    /** A map of players' names to their odds of collecting the most rebounds. */
    private Map<String, Double> most_reb;

    /**
     * Constructs a new BasketOdds object.
     *
     * @param event_id  The unique identifier for the event.
     * @param odd_value A list of odds values associated with the event.
     * @param eventType The type of event the odds are associated with.
     * @param odd_draw  The odds for a draw in the game.
     * @param most_3p   A map of players to their odds of making the most 3-point shots.
     * @param most_reb  A map of players to their odds of collecting the most rebounds.
     */
    public BasketOdds(int event_id, List<Double> odd_value, String eventType, int odd_draw, Map<String, Double> most_3p, Map<String, Double> most_reb) {
        super(event_id, odd_value, eventType);
        this.odd_draw = odd_draw;
        this.most_3p = most_3p;
        this.most_reb = most_reb;
    }

    /**
     * Gets the odds for a draw in the game.
     *
     * @return The draw odds.
     */
    public int getOdd_draw() {
        return odd_draw;
    }

    /**
     * Sets the odds for a draw in the game.
     *
     * @param odd_draw The draw odds to set.
     */
    public void setOdd_draw(int odd_draw) {
        this.odd_draw = odd_draw;
    }

    /**
     * Gets the map of players to their odds of making the most 3-point shots.
     *
     * @return A map of players to their 3-point odds.
     */
    public Map<String, Double> getMost_3p() {
        return most_3p;
    }

    /**
     * Sets the map of players to their odds of making the most 3-point shots.
     *
     * @param most_3p The map of players to their 3-point odds.
     */
    public void setMost_3p(Map<String, Double> most_3p) {
        this.most_3p = most_3p;
    }

    /**
     * Gets the map of players to their odds of collecting the most rebounds.
     *
     * @return A map of players to their rebound odds.
     */
    public Map<String, Double> getMost_reb() {
        return most_reb;
    }

    /**
     * Sets the map of players to their odds of collecting the most rebounds.
     *
     * @param most_reb The map of players to their rebound odds.
     */
    public void setMost_reb(Map<String, Double> most_reb) {
        this.most_reb = most_reb;
    }
}
