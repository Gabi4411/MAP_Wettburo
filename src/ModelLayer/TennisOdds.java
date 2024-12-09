//package ModelLayer;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Represents the odds related to a football event.
// * This class extends the Odds class and includes additional fields specific to football.
// */
//public class TennisOdds{
//
//
//
//    int oddID;
//    String oddName;
//    String eventType;
//
//    public TennisOdds(int oddID, String oddName, String eventType) {
//        this.oddID = oddID;
//        this.oddName = oddName;
//        this.eventType = eventType;
//    }
//
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
//    public String getEventType() {
//        return eventType;
//    }
//
//    public void setEventType(String eventType) {
//        this.eventType = eventType;
//    }
//
//    @Override
//    public String toString() {
//        return "TennisOdds{" +
//                "oddID=" + oddID +
//                ", oddName='" + oddName + '\'' +
//                ", eventType='" + eventType + '\'' +
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