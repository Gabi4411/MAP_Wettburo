package ModelLayer;

import java.util.Map;

public class TennisOdds extends Odds {
    private Map<String,Double> most_aces;
    private Map<String,Double> Setcast;

    public TennisOdds(int odd_team1, String odd_id, int odd_team2, Event event, double odd_value, Map<String, Double> most_aces, Map<String, Double> setcast) {
        super(odd_team1, odd_id, odd_team2, event, odd_value);
        this.most_aces = most_aces;
        Setcast = setcast;
    }

    public Map<String, Double> getMost_aces() {
        return most_aces;
    }

    public void setMost_aces(Map<String, Double> most_aces) {
        this.most_aces = most_aces;
    }

    public Map<String, Double> getSetcast() {
        return Setcast;
    }

    public void setSetcast(Map<String, Double> setcast) {
        Setcast = setcast;
    }
}
