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
//public class BasketOdds extends Odds{
//
//
//
//    public BasketOdds(String oddName,List<Double> oddValue)
//    {
//        super(oddName,oddValue, "Basket");
//    }
//
//    public String getType() {
//        return "Basket";
//    }
//
//    @Override
//    public String toString() {
//        return "BasketOdds{}";
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
//    public static BasketOdds fromCSV(String csvLine) {
//        String[] parts = csvLine.split(";", 2);
//        List<Double> oddValue = Arrays.stream(parts[0].split(","))
//                .map(Double::parseDouble)
//                .toList();
//        return new BasketOdds(oddValue, parts[1]);
//    }
//}