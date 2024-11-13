package ModelLayer;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class TennisOdds extends Odds {
    private Map<String,Double> most_aces;
    private Map<String,Double> Setcast;


    public TennisOdds(int event_id, List<Double> odd_value, String eventType, Map<String, Double> most_aces, Map<String, Double> setcast) {
        super(event_id, odd_value, eventType);
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
