package ModelLayer;

public class Statistics {
    private Event event;
    private String stats_team1;
    private String stats_team2;
    private String h2h;
    private String recent_form;

    public Statistics(Event event, String stats_team1, String stats_team2, String h2h, String recent_form) {
        this.event = event;
        this.stats_team1 = stats_team1;
        this.stats_team2 = stats_team2;
        this.h2h = h2h;
        this.recent_form = recent_form;
    }

    public Event getEvent() {
        return event;
    }

    public String getStats_team1() {
        return stats_team1;
    }

    public String getStats_team2() {
        return stats_team2;
    }

    public String getH2h() {
        return h2h;
    }

    public String getRecent_form() {
        return recent_form;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setStats_team1(String stats_team1) {
        this.stats_team1 = stats_team1;
    }

    public void setStats_team2(String stats_team2) {
        this.stats_team2 = stats_team2;
    }

    public void setH2h(String h2h) {
        this.h2h = h2h;
    }

    public void setRecent_form(String recent_form) {
        this.recent_form = recent_form;
    }
}
