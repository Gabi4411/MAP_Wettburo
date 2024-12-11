package ModelLayer;

/**
 * Represents statistics for an event, including team statistics, head-to-head data, and recent form.
 */
public class Statistics {
    private int eventId;
    private String eventDescription;
    private String eventPrediction;


    public Statistics(int eventId, String eventDescription, String eventPrediction) {
        this.eventId = eventId;
        this.eventDescription = eventDescription;
        this.eventPrediction = eventPrediction;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventPrediction() {
        return eventPrediction;
    }

    public void setEventPrediction(String eventPrediction) {
        this.eventPrediction = eventPrediction;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "eventId=" + eventId +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventPrediction='" + eventPrediction + '\'' +
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
