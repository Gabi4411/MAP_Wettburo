package ModelLayer;

import java.util.List;
import java.util.Map;

/**
 * Represents the odds related to a football event.
 * This class extends the Odds class and includes additional fields specific to football.
 */
public class FootballOdds extends Odds {

    /** The odds for a draw in the game. */
    private int odd_draw;

    /** A map of players' names to their odds of scoring a goal. */
    private Map<String, Double> goal_scorer;

    /** A map of scores to their respective odds of being the correct final score. */
    private Map<String, Double> correct_score;

    /**
     * Constructs a new FootballOdds object.
     *
     * @param event_id      The unique identifier for the event.
     * @param odd_value     A list of odds values associated with the event.
     * @param eventType     The type of event the odds are associated with.
     * @param odd_draw      The odds for a draw in the game.
     * @param goal_scorer   A map of players to their odds of scoring a goal.
     * @param correct_score A map of scores to their odds of being the correct final score.
     */
    public FootballOdds(int event_id, List<Double> odd_value, String eventType, int odd_draw, Map<String, Double> goal_scorer, Map<String, Double> correct_score) {
        super(event_id, odd_value, eventType);
        this.odd_draw = odd_draw;
        this.goal_scorer = goal_scorer;
        this.correct_score = correct_score;
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
     * Gets the map of players to their odds of scoring a goal.
     *
     * @return A map of players to their goal-scoring odds.
     */
    public Map<String, Double> getGoal_scorer() {
        return goal_scorer;
    }

    /**
     * Sets the map of players to their odds of scoring a goal.
     *
     * @param goal_scorer The map of players to their goal-scoring odds.
     */
    public void setGoal_scorer(Map<String, Double> goal_scorer) {
        this.goal_scorer = goal_scorer;
    }

    /**
     * Gets the map of scores to their respective odds of being the correct final score.
     *
     * @return A map of scores to their correct-score odds.
     */
    public Map<String, Double> getCorrect_score() {
        return correct_score;
    }

    /**
     * Sets the map of scores to their respective odds of being the correct final score.
     *
     * @param correct_score The map of scores to their correct-score odds.
     */
    public void setCorrect_score(Map<String, Double> correct_score) {
        this.correct_score = correct_score;
    }
}
