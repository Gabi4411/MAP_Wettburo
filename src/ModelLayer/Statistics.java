package ModelLayer;

import java.util.List;
import java.util.ArrayList;
/**
 * Represents statistics for an event, including team statistics, head-to-head data, and recent form.
 */
public class Statistics {
    private int stat_id;
    private int event_id;
    private String eventDescription;
    private String eventPrediction;


    public Statistics(int stat_id,int event_id, String eventDescription, String eventPrediction) {
        this.stat_id = stat_id;
        this.event_id = event_id;
        this.eventDescription = eventDescription;
        this.eventPrediction = eventPrediction;
    }


    public int getStat_id() {
        return stat_id;
    }

    public void setStat_id(int stat_id) {
        this.stat_id = stat_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
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
                "stat_id=" + stat_id +
                ", event_id=" + event_id +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventPrediction='" + eventPrediction + '\'' +
                '}';
    }

    public static Statistics fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        int eventId = Integer.parseInt(fields[0].trim());
        String eventDescription = fields[1].trim();
        String eventPrediction = fields[2].trim();
        return new Statistics(eventId, eventDescription, eventPrediction);
    }

    /**
     * Converts a Statistics object to a CSV line.
     *
     * @return A CSV string representing the Statistics object.
     */
    public String toCSV() {
        return eventId + "," + eventDescription + "," + eventPrediction;
    }

    public static List<Statistics> fromCSVList(List<String> csvLines) {
        List<Statistics> statisticsList = new ArrayList<>();
        for (String line : csvLines) {
            statisticsList.add(fromCSV(line));
        }
        return statisticsList;
    }

    public static List<String> toCSVList(List<Statistics> statisticsList) {
        List<String> csvLines = new ArrayList<>();
        for (Statistics statistics : statisticsList) {
            csvLines.add(statistics.toCSV());
        }
        return csvLines;
    }
}
