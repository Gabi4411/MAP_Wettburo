package ModelLayer;

import java.util.EnumMap;
import java.util.Map;

public class FootballOdds extends Odds {
    private int odd_draw;
    private Map<String , Double> goal_scorer;
    private Map<String , Double> correct_score ;

    public FootballOdds(String odd_id, double odd_value, EnumMap<Team, Double> team_odds, Event event, Map<String, Double> goal_scorer, Map<String, Double> correct_score, int odd_draw) {
        super(odd_id, odd_value, team_odds, event);
        this.goal_scorer = goal_scorer;
        this.correct_score = correct_score;
        this.odd_draw = odd_draw;
    }

    public int getOdd_draw() {
        return odd_draw;
    }

    public void setOdd_draw(int odd_draw) {
        this.odd_draw = odd_draw;
    }

    public Map<String, Double> getGoal_scorer() {
        return goal_scorer;
    }

    public void setGoal_scorer(Map<String, Double> goal_scorer) {
        this.goal_scorer = goal_scorer;
    }

    public Map<String, Double> getCorrect_score() {
        return correct_score;
    }

    public void setCorrect_score(Map<String, Double> correct_score) {
        this.correct_score = correct_score;
    }
}
