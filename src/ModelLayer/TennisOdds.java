package ModelLayer;

import java.util.EnumMap;
import java.util.Map;

public class TennisOdds extends Odds {
    private Map<String,Double> most_aces;
    private Map<String,Double> Setcast;

    public TennisOdds(String odd_id, double odd_value, EnumMap<Team, Double> team_odds, Event event, Map<String, Double> most_aces, Map<String, Double> setcast) {
        super(odd_id, odd_value, team_odds, event);
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
