//package ModelLayer;
//
//import java.awt.geom.QuadCurve2D;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Represents the odds related to a football event.
// * This class extends the Odds class and includes additional fields specific to football.
// */
//public class FootballOdds {
//
//    int oddID;
//    String oddName;
//    String eventName;
//
//    public FootballOdds(int oddID, String oddName, String eventName) {
//        this.oddID = oddID;
//        this.oddName = oddName;
//        this.eventName = eventName;
//    }
//
//    public int getOddID() {
//        return oddID;
//    }
//
//    public void setOddID(int oddID) {
//        this.oddID = oddID;
//    }
//
//    public String getOddName() {
//        return oddName;
//    }
//
//    public void setOddName(String oddName) {
//        this.oddName = oddName;
//    }
//
//    public String getEventName() {
//        return eventName;
//    }
//
//    public void setEventName(String eventName) {
//        this.eventName = eventName;
//    }
//
//    @Override
//    public String toString() {
//        return "FootballOdds{" +
//                "oddID=" + oddID +
//                ", oddName='" + oddName + '\'' +
//                ", eventName='" + eventName + '\'' +
//                '}';
//    }
//
//    @Override
//    public String toCSV() {
//        List<Double> oddValue = getOdd_value();
//        return String.join(";",
//                getType(),
//                String.join(",", oddValue.stream().map(String::valueOf).toArray(String[]::new)),
//                getEventType()
//        );
//    }
//
//    public static FootballOdds fromCSV(String csvLine) {
//        String[] parts = csvLine.split(";", 2);
//        List<Double> oddValue = Arrays.stream(parts[0].split(","))
//                .map(Double::parseDouble)
//                .toList();
//        return new FootballOdds(oddValue, parts[1]);
//    }
//}
