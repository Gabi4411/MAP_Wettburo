package ModelLayer;

/**
 * Represents statistics for an event, including team statistics, head-to-head data, and recent form.
 */
public class Statistics {
    private Event event;
    private String stats_team1;
    private String stats_team2;
    private String h2h;
    private String recent_form;

    /**
     * Constructs a Statistics object with specified details.
     *
     * @param event        the event for which statistics are recorded
     * @param stats_team1  the statistics for team 1
     * @param stats_team2  the statistics for team 2
     * @param h2h          the head-to-head record between the two teams
     * @param recent_form  the recent form of the teams involved
     */
    public Statistics(Event event, String stats_team1, String stats_team2, String h2h, String recent_form) {
        this.event = event;
        this.stats_team1 = stats_team1;
        this.stats_team2 = stats_team2;
        this.h2h = h2h;
        this.recent_form = recent_form;
    }

    /**
     * Gets the event associated with this statistics object.
     *
     * @return the event associated with these statistics
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Gets the statistics for team 1.
     *
     * @return the statistics for team 1
     */
    public String getStats_team1() {
        return stats_team1;
    }

    /**
     * Gets the statistics for team 2.
     *
     * @return the statistics for team 2
     */
    public String getStats_team2() {
        return stats_team2;
    }

    /**
     * Gets the head-to-head record between the two teams.
     *
     * @return the head-to-head record
     */
    public String getH2h() {
        return h2h;
    }

    /**
     * Gets the recent form of the teams involved.
     *
     * @return the recent form of the teams
     */
    public String getRecent_form() {
        return recent_form;
    }

    /**
     * Sets the event associated with this statistics object.
     *
     * @param event the event to be set
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Sets the statistics for team 1.
     *
     * @param stats_team1 the statistics for team 1 to be set
     */
    public void setStats_team1(String stats_team1) {
        this.stats_team1 = stats_team1;
    }

    /**
     * Sets the statistics for team 2.
     *
     * @param stats_team2 the statistics for team 2 to be set
     */
    public void setStats_team2(String stats_team2) {
        this.stats_team2 = stats_team2;
    }

    /**
     * Sets the head-to-head record between the two teams.
     *
     * @param h2h the head-to-head record to be set
     */
    public void setH2h(String h2h) {
        this.h2h = h2h;
    }

    /**
     * Sets the recent form of the teams involved.
     *
     * @param recent_form the recent form to be set
     */
    public void setRecent_form(String recent_form) {
        this.recent_form = recent_form;
    }

    /**
     * Returns a string representation of the Statistics object, including event, team statistics, head-to-head data, and recent form.
     *
     * @return a string representation of the Statistics object
     */
    @Override
    public String toString() {
        return "Statistics{" +
                "event=" + event +
                ", stats_team1='" + stats_team1 + '\'' +
                ", stats_team2='" + stats_team2 + '\'' +
                ", h2h='" + h2h + '\'' +
                ", recent_form='" + recent_form + '\'' +
                '}';
    }

//    public String toCSV() {
//        return String.join(";",
//                event.toCSV(),
//                stats_team1,
//                stats_team2,
//                h2h,
//                recent_form
//        );
//    }
//
//    public static Statistics fromCSV(String csvLine) {
//        String[] parts = csvLine.split(";", 5);
//        Event event = Event.fromCSV(parts[0]);
//        return new Statistics(
//                event,
//                parts[1],
//                parts[2],
//                parts[3],
//                parts[4]
//        );
//    }
}
