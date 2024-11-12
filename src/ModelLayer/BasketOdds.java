package ModelLayer;

import java.util.EnumMap;
import java.util.Map;

public class BasketOdds extends Odds {
    private int odd_draw;
    private Map<String,Double> most_3p;
    private Map<String,Double> most_reb;

    public BasketOdds(String odd_id, double odd_value, EnumMap<Team, Double> team_odds, Event event, int odd_draw, Map<String, Double> most_3p, Map<String, Double> most_reb) {
        super(odd_id, odd_value, team_odds, event);
        this.odd_draw = odd_draw;
        this.most_3p = most_3p;
        this.most_reb = most_reb;
    }

    public int getOdd_draw() {
        return odd_draw;
    }

    public Map<String, Double> getMost_3p() {
        return most_3p;
    }

    public Map<String, Double> getMost_reb() {
        return most_reb;
    }

    public void setOdd_draw(int odd_draw) {
        this.odd_draw = odd_draw;
    }

    public void setMost_3p(Map<String, Double> most_3p) {
        this.most_3p = most_3p;
    }

    public void setMost_reb(Map<String, Double> most_reb) {
        this.most_reb = most_reb;
    }
}