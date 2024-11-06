package ModelLayer;

import java.util.Map;

public class TennisOdds extends Odds {
    private Map<String,Double> most_aces;
    private Map<String,Double> Setcast;

    public TennisOdds(int odd_tream1, int id, int odd_tream2, Event event, Map<String, Double> most_aces, Map<String, Double> setcast) {
        super(odd_tream1, id, odd_tream2, event);
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
