package ModelLayer;
import java.util.EnumMap;

enum Team{
    TEAM1,TEAM2
}

public abstract class Odds {
    private String odd_id;
    private double odd_value;
    EnumMap<Team, Double> team_odds;
    Event event;

    public Odds(String odd_id, double odd_value, EnumMap<Team, Double> team_odds, Event event) {
        this.odd_id = odd_id;
        this.odd_value = odd_value;
        this.team_odds = team_odds;
        this.event = event;
    }

    public double getOdd_value() {
        return odd_value;
    }

    public String getOdd_id() {
        return odd_id;
    }

    public EnumMap<Team, Double> getTeam_odds() {
        return team_odds;
    }

    public Event getEvent() {
        return event;
    }

    public void setOdd_id(String odd_id) {
        this.odd_id = odd_id;
    }

    public void setOdd_value(double odd_value) {
        this.odd_value = odd_value;
    }

    public void setTeam_odds(EnumMap<Team, Double> team_odds) {
        this.team_odds = team_odds;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
